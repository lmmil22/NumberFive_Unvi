// by 유빈
package kh.study.NF.board.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kh.study.NF.board.service.BoardService;
import kh.study.NF.board.vo.BoardCategoryVO;
import kh.study.NF.board.vo.BoardVO;
import kh.study.NF.board.vo.ImgVO;
import kh.study.NF.board.vo.ReplyVO;
import kh.study.NF.board.vo.SearchVO;
import kh.study.NF.config.BoardUploadFileUtil;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource(name = "boardService")
	private BoardService boardService;
///////////////////////////////////////////////////////////////////////////////////////////////////
	//--------------------------- [공통 권한 게시판 영역]  --------------------------------------//
	//게시글 목록페이지
	@RequestMapping("/list")
	public String select(Model model,BoardVO boardVO,String boardNo,SearchVO searchVO) {
		System.out.println("SearchKeyword=" + boardVO.getSearchKeyword());
		System.out.println("searchValue=" + boardVO.getSearchValue());
		//페이징처리때문에[3.4.5번]
		//3.전체 데이커(게시글) 수를 먼저 가져오기 
		//4.db에서 쿼리문 메소드기능 실행한 값(totalCnt)을 totalDataCnt에 넣어주기
		boardVO.setTotalDataCnt(boardService.selectBoardCnt(searchVO));
		System.out.println("_________________게시판 총 갯수 조회 쿼리문 실행성공_______________");
		//5.페이지 정보 세팅(목록조회전)
		boardVO.setPageInfo();
		System.out.println("_________________게시판 페이징 정보 실행 성공_______________");
		System.out.println("_____boardVO 추출_____" + boardVO);
		//2.게시글 목록 조회(한줄요약)
		model.addAttribute("boardList",boardService.selectBoardList(boardVO));
		System.out.println("_________________게시판 목록 조회 성공_______________");
		
		return "content/common/board/board_list";
	}
	
	// 글쓰러가기-양식페이지로이동
	@GetMapping("/reg")
	public String reg(@Valid BoardVO boardVO, BindingResult bindingResult, Model model
						,Authentication authentication,BoardCategoryVO boardCategoryVO) {
		//카테고리 목록조회(사용중인!)
		model.addAttribute("cateUsedList", boardService.selectBoardCateUse());
		return"content/common/board/reg_board";
	}
	
	// 실제 게시글 등록
	// -> shop - admincontroller - /regItem 경로 소스참고
	@PostMapping("/reg")
	public String reg(@Valid BoardVO boardVO, BindingResult bindingResult, Model model,Authentication authentication
						,ImgVO imgVO, MultipartFile mainImg ,List<MultipartFile> subImgs){// 서브파일은 여러 파일이 들어가있으니 리스트!
		
		// 학번/교번 유저 
		User user = (User) authentication.getPrincipal();
		boardVO.setBoardWriter(user.getUsername());//시큐리티 로그인 아이디(학번,교번)을 작성자로 넣어주기
		
		// 단일 이미지 파일 첨부 - mainImg
		ImgVO  uploadInfo = BoardUploadFileUtil.uploadFile(mainImg);
		// 다중 이미지 파일 첨부 - subImgs
		List<ImgVO> uploadList = BoardUploadFileUtil.MultiUploadFile(subImgs);
		//다중서브이미지 리스트에 단일메인이미지까지 넣어준다.
		uploadList.add(uploadInfo);
		
		String nextBoardNo = boardService.getNextBoardNo();
		boardVO.setBoardNo(nextBoardNo);//
		for(ImgVO vo : uploadList) {
			vo.setBoardNo(nextBoardNo);
		}
		
		boardVO.setImgList(uploadList);
		System.out.println("______________글등록하러간다.______________");
		boardService.insertBoard(boardVO);
		
		System.out.println("___________매퍼 게시글등록 실행 됐다___________");
		
		// 주의! 순서중요하다. 유효성체크 먼저 한 후, 로그인정보값 boardVO에 넣어주기 !!
		if (bindingResult.hasErrors()) {
			System.out.println("에러발생!!!!");
			
			//주의!!! 컨트롤러(redirect)가 아닌 html페이지로 가야 데이터가 남아있는다
			return"content/common/board/reg_board";
		}
		return "content/common/board/reg_result";
	}

	//게시글(+이미지) 상세조회
	@GetMapping("/detail")
	public String selectDetail(@RequestParam(required = false) String boardNo,Model model,ReplyVO  replyVO,BoardVO boardVO) {
		System.out.println("null값이니??????????/들고왔니???????"+boardNo);
		System.out.println("___________게시판상세조회 페이지로 이동 !!! _______________");
		//게시글 상세조회
		model.addAttribute("board", boardService.selectDetailBoard(boardNo));
		System.out.println("게시판 상세조회 했을 때----"+boardVO);
		//사용중인 카테고리 목록조회
		model.addAttribute("cateUsedList", boardService.selectBoardCateUse());
		System.out.println("--------------------- 사용중 카테고리 목록 조회완료 -------------------------");
		//댓글목록조회
		model.addAttribute("replyList",boardService.selectReplyList(boardNo));
		System.out.println("--------------------- 댓글 목록 조회하러 완료-------------------------");
		//조회수증가-이클립스
		boardService.updateReadCnt(boardNo);
		System.out.println("______________________ 조회수증가 쿼리문 모두 실행 완료______________________________");
		System.out.println(boardService.selectDetailBoard(boardNo));
		System.out.println("______________________//baordNo가지고 상세조회 쿼리문 모두 실행 완료 했을 때//______________________________");
		return "content/common/board/board_detail";
	}
	
	
	// 글수정하러가기
	@GetMapping("/update")
	public String update(BoardVO boardVO , String boardNo,Model model) {//매개변수는 커맨드객체 or model 값 둘 중 하나만 사용가능하다
		BoardVO result =  boardService.selectDetailBoard(boardNo);
		boardVO.setBoardNo(result.getBoardNo());
		boardVO.setBoardContent(result.getBoardContent());
		boardVO.setBoardTitle(result.getBoardTitle());
		boardVO.setBoardCreateDate(result.getBoardCreateDate());
		boardVO.setBoardWriter(result.getBoardWriter());
		//사용중인 카테고리 목록조회
		model.addAttribute("cateUsedList", boardService.selectBoardCateUse());
		
		return "content/common/board/update_board_form";
	}
	
	// 게시글 수정 실제 등록
	@PostMapping("/update")
	public String update(BoardVO boardVO) {
		 boardService.update(boardVO);
		 return "content/common/board/update_result";//수정확인 후,alert창 띄워보기
//		 return "redirect:/board/detail?boardNum=" + boardVO.getBoardNum();//수정확인 후, 다시 상세보기로
	}

	// 글삭제
	@GetMapping("/delete")
	public String delete1(String boardNo) {
		boardService.delete(boardNo);
		System.out.println("___게시글 상세조회 후 삭제버튼 클릭하고 스왈다음 여기로 왔다___");
		return "redirect:/board/list";
	}
	// 글삭제
	@ResponseBody
	@PostMapping("/delete")
	public void delete2(String boardNo) {
		System.out.println("_________게시글 상세조회 후 삭제버튼 클릭하고 스왈 컨펌 ajax 실행 __________");
		boardService.delete(boardNo);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//---------------------------- [관리자모드 게시판 영역] -------------------------------------//
	//관리자 게시판 페이지이동(카테고리등록 +  카테고리목록조회)
	@GetMapping("/boardAdmin")
	public String boardAdmin(Model model, BoardCategoryVO boardCategoryVO) {
		//카테고리목록조회
		model.addAttribute("cateList", boardService.selectBoardCate());
		return "content/admin/board_admin";
	}
	//카테고리등록
	@PostMapping("/regCate")
	public String regCate(BoardCategoryVO boardCategoryVO) {
		boardService.insertBoardCate(boardCategoryVO);
		return"redirect:/board/boardAdmin";
	}
	//카테고리 삭제
	@PostMapping("/deleteCate")
	public String deleteCarts(String cateNos) {//"cart_001,cart_002,cart_003"
		System.out.println("!!!!!!!!!!!!" + cateNos +"!!!!!!!!!!!!!!!!!!!!!!!");
		String[] cateNoArr = cateNos.split(",");// 위처럼 넘어오는 cartCodes를 쉼표',' 로 분리한다. 그럼 문자열이 여러개나와서 배열로 받아야한다.
		List<String> cateNoList= Arrays.asList(cateNoArr);
		BoardCategoryVO boardCategoryVO = new BoardCategoryVO();//커맨드객체로 넘겨주려면 미리 cartVO에 cartCodes가 있으면 가능하다
		boardCategoryVO.setCateNoList(cateNoList);
		
		boardService.deleteCates(boardCategoryVO);
		
		return"redirect:/board/boardAdmin";
	}
	
	//카테고리목록조회
	@GetMapping("/cateList")
	public String cateList(Model model) {
		//카테고리목록조회
		model.addAttribute("cateList", boardService.selectBoardCate());
		return "content/admin/board_admin";
	}
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------[게시판 댓글 영역]------------------------------------------------------//
	//댓글 등록
	@PostMapping("/insertReply")
	public String insertReply( @Valid ReplyVO replyVO,BindingResult bindingResult,Model model,
			 					Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		replyVO.setReplyWriter(user.getUsername());
		replyVO.setBoardNo(replyVO.getBoardNo());

		if(bindingResult.hasErrors()) {
			System.out.println("1111");
			return "redirect:/board/detail?boardNo=" + replyVO.getBoardNo();
		}
		
		
		boardService.insertReply(replyVO);
		
		System.out.println("댓글등록쿼리실행완료_______________________");
		
		return"content/common/board/reply_result";
	}
	//댓글 삭제 
	@GetMapping("/deleteReply")
	public String deleteReply(int replyNo) {
		boardService.deleteReply(replyNo);
		System.out.println("___게시글 상세조회 후 댓글 삭제버튼 클릭함___");
		return "redirect:/board/list"; 
	}

	// 댓글수정하러가기(양식페에지이동)
	@GetMapping("/updateReply")
	public String updateReply(ReplyVO replyVO,int replyNo,Model model
							  ,@RequestParam(required = false) String boardNo) {
		System.out.println("___________게시판상세조회 댓글수정 양식 페이지로 이동 !!! _______________");
		//게시글 목록조회
		model.addAttribute("board", boardService.selectDetailBoard(boardNo));
		System.out.println("___________게시판 게시글 총 목록조회 쿼리문 완료 _______________");
		//사용중인 카테고리 목록조회
		model.addAttribute("cateUsedList", boardService.selectBoardCateUse());
		System.out.println("___________사용중 카테고리 목록조회 쿼리문 완료 _______________");
		//댓글목록조회
		model.addAttribute("replyList",boardService.selectReplyList(boardNo));
		System.out.println("___________댓글 전체 목록조회 쿼리문 완료 _______________");
		//수정전 선택된 댓글 상세조회
		model.addAttribute("replyUpdate", boardService.selectDetailReply(replyNo));
		System.out.println("___________댓글상세조회 쿼리문 완료 _______________");

		System.out.println("___________이제 댓글 수정 post매핑으로 이동 _______________");
		
		return "content/common/board/baord_detail_form";//댓글수정페이지
	}
	// 게시글 수정 실제 등록
	@PostMapping("/updateReply")
	public String updateReply(ReplyVO replyVO) {
		
		System.out.println(replyVO.getReplyNo());
		System.out.println("@@@@@@@@@@@@@@@"+replyVO.getReplyContent());

		boardService.updateReply(replyVO);
		
		 return "content/common/board/update_result";//수정확인 후,alert창 띄워보기
	}
}
