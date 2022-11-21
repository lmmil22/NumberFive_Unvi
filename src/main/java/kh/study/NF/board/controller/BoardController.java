// by 유빈
package kh.study.NF.board.controller;
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

import kh.study.NF.board.service.BoardService;
import kh.study.NF.board.vo.BoardCategoryVO;
import kh.study.NF.board.vo.BoardVO;
import kh.study.NF.board.vo.ReplyVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource(name = "boardService")
	private BoardService boardService;
///////////////////////////////////////////////////////////////////////////////////////////////////
	//--------------------------- [공통 권한 게시판 영역]  --------------------------------------//
	
	//게시글 목록페이지
	@RequestMapping("/list")
	public String select(Model model,BoardVO boardVO,String boardNo) {
		
		System.out.println("SearchKeyword=" + boardVO.getSearchKeyword());
		System.out.println("searchValue=" + boardVO.getSearchValue());
		
		//페이징처리때문에[3.4.5번]
		//3.전체 데이커(게시글) 수를 먼저 가져오기 
		//이때문에 mapper에서 또 조회쿼리문 생성한것
		int totalCnt = boardService.selectBoardCnt();

		//4.db에서 쿼리문 메소드기능 실행한 값(totalCnt)을 totalDataCnt에 넣어주기
		boardVO.setTotalDataCnt(totalCnt);
		
		//5.페이지 정보 세팅(목록조회전)
		boardVO.setPageInfo();
		
		//2.게시글 목록 조회(한줄요약)
		model.addAttribute("boardList",boardService.selectBoardList(boardVO));
		
		//내가만든 게시판목록조회
		//model.addAttribute("boardList",boardService.selectBoard());
		
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
	
	// 실제 글 등록
	@PostMapping("/reg")
	public String reg(@Valid BoardVO boardVO, BindingResult bindingResult, Model model
						,Authentication authentication){
		
		User user = (User) authentication.getPrincipal();
		
		boardVO.setBoardWriter(user.getUsername());//시큐리티 로그인 아이디(학번,교번)을 작성자로 넣어주기
		
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

	//게시글 상세조회
	@GetMapping("/detail")
	public String selectDetail(@RequestParam(required = false) String boardNo,Model model,ReplyVO  replyVO) {
		model.addAttribute("board", boardService.selectDetailBoard(boardNo));
			//사용중인 카테고리 목록조회
			model.addAttribute("cateUsedList", boardService.selectBoardCateUse());
			//댓글목록조회
			boardService.selectReplyList(boardNo);
			
			//---------------[게시글 댓글 기능]--------------------------//
			//조회수증가-이클립스
			boardService.updateReadCnt(boardNo);
			//댓글목록조회-이클립스
			List<ReplyVO> replyList = boardService.selectReplyList(boardNo);
			model.addAttribute("replyList", replyList);
			//------------------------------------------------------------//
			
		System.out.println("___________게시판상세조회이동_______________");
		return "content/common/board/board_detail";
	}
	
	
	// 글수정하러가기
	@GetMapping("/update")
	public String update(BoardVO boardVO , String boardNo,Model model) {//매개변수는 커맨드객체 or model 값 둘 중 하나만 사용가능하다
		//(주의)수정 전에는 어떤 글을 수정할 것인지 "상세조회" 반드시 먼저해준다!!!
		
		//-----(방법1) 커맨드 객체 사용할 때---------------------------------//
		// 커맨드객체를 사용하기때문에 update_form html파일에서 th:object와 th:field 사용가능하다
		// 그리고 id,name,value 속성값이 자동생성 가능하다는 장점이 있다.
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
	
	// 글수정 등록
	@PostMapping("/update")
	public String update(BoardVO boardVO) {
		 boardService.update(boardVO);
		 return "content/common/board/update_result";//수정확인 후,alert창 띄워보기
//		 return "redirect:/board/detail?boardNum=" + boardVO.getBoardNum();//수정확인 후, 다시 상세보기로
	}

	// 글삭제
	@GetMapping("/delete")
	public String delete(String boardNo) {
		boardService.delete(boardNo);
		System.out.println("___게시글 상세조회 후 삭제버튼 클릭하고 스왈다음 여기로 왔다___");
		return "redirect:/board/list";
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
	public String insertReply(ReplyVO replyVO) {
		boardService.insertReply(replyVO);
		return"content/common/board/reply_result";
	}
	//댓글 삭제 
	@GetMapping("/deleteReply")
	public String deleteReply(int replyNo) {
		boardService.deleteReply(replyNo);
		System.out.println("___게시글 상세조회 후 댓글 삭제버튼 클릭함___");
		return "redirect:/board/detail";
	}
	// 댓글 목록조회 페이지--> 게시글 상세조회 페이지에서 
	//댓글 수정 양식페이지이동--> 게시글 수정양식페이지와 같은페이지
	
	// 댓글수정 실제 등록--> 게시글 실제 등록 페이지와 같은 페이지

}
