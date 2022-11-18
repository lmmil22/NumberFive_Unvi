// by 유빈
package kh.study.NF.board.controller;
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

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource(name = "boardService")
	private BoardService boardService;
///////////////////////////////////////////////////////////////////////////////////////////////////
	//--------------------------- [공통 권한 게시판 영역]  --------------------------------------//
	
	//게시글 목록페이지
	@RequestMapping("/list")
	public String select(Model model,BoardVO boardVO) {
		model.addAttribute("boardList",boardService.selectBoard());
		return "content/common/board/board_list";
	}
	
	// 글쓰러가기-양식페이지로이동
	@GetMapping("/reg")
	public String reg(BoardVO boardVO) {
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
	public String selectDetail(@RequestParam(required = false) String boardNo,Model model) {
		model.addAttribute("board", boardService.selectDetailBoard(boardNo));
		System.out.println("게시판상세조회");
		return "content/common/board/board_detail";
	}
	
	
	// 글수정하러가기
	@GetMapping("/update")
	public String update(BoardVO boardVO , String boardNo) {//매개변수는 커맨드객체 or model 값 둘 중 하나만 사용가능하다
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
	
}
