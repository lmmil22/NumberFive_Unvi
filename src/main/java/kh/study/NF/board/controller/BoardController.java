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
import kh.study.NF.board.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource(name = "boardService")
	private BoardService boardService;
////////////////////////////////////////////////////////////////	
	//게시글 목록페이지
	@RequestMapping("/list")
	public String select(Model model,BoardVO boardVO) {
		model.addAttribute("boardList",boardService.selectBoard());
		return "content/common/board_list";
	}
	
	// 글쓰러가기-양식페이지로이동
	@GetMapping("/reg")
	public String reg(BoardVO boardVO) {
		return"content/common/reg_board";
	}
	
	// 실제 글 등록
	@PostMapping("/reg")
	public String reg(@Valid BoardVO boardVO, BindingResult bindingResult, Model model
						,Authentication authentication){
		
		User user = (User) authentication.getPrincipal();
		
		boardVO.setMemNo(user.getUsername());//시큐리티 로그인 아이디(학번,교번)을 memNo으로 넣어주기
		
		boardService.insertBoard(boardVO);
		System.out.println("___________매퍼 게시글등록 실행 됐다___________");
		
		// 주의! 순서중요하다. 유효성체크 먼저 한 후, 로그인정보값 boardVO에 넣어주기 !!
		if (bindingResult.hasErrors()) {
			System.out.println("에러발생!!!!");

			//주의!!! 컨트롤러(redirect)가 아닌 html페이지로 가야 데이터가 남아있는다
			return"content/board/reg_board";
		}
		
		return "content/board/reg_result";
	}

	//게시글 상세조회
	@GetMapping("/detail")
	public String selectDetail(@RequestParam(required = false) int boardNum,Model model) {
		model.addAttribute("board", boardService.selectDetailBoard(boardNum));
		
		System.out.println("게시판상세조회");
		return "content/common/board_detail";
		
	}
	
	
	// 글수정하러가기
	@GetMapping("/update")
	public String update(BoardVO boardVO , int boardNum) {//매개변수는 커맨드객체 or model 값 둘 중 하나만 사용가능하다
		//(주의)수정 전에는 어떤 글을 수정할 것인지 "상세조회" 반드시 먼저해준다!!!
		
		//-----(방법1) 커맨드 객체 사용할 때---------------------------------//
		// 커맨드객체를 사용하기때문에 update_form html파일에서 th:object와 th:field 사용가능하다
		// 그리고 id,name,value 속성값이 자동생성 가능하다는 장점이 있다.
		BoardVO result =  boardService.selectDetailBoard(boardNum);
		boardVO.setBoardNo(result.getBoardNo());
		boardVO.setBoardContent(result.getBoardContent());
		boardVO.setBoardTitle(result.getBoardTitle());
		boardVO.setBoardCreateDate(result.getBoardCreateDate());
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX//
		// 일단 주석처리함 위에 USER UTIL완료되면 넣기 
		boardVO.setMemNo(result.getMemNo());
		
		
		//-----(방법2) model사용할 때-------------------------------------------------//
		// 하지만, model을 사용할때는 커맨드객체사용이 불가하기때문에 달러+{객체명.컬럼명}으로 데이터불러와 사용가능하다.
		// 또 id,name,value 속성값이 자동생성되지않는다
		//model.addAttribute("board",boardService.selectDetailBoard(boardNum) );
	
		return "content/board/update_board_form";
	}
	
	// 글수정 등록
	@PostMapping("/update")
	public String update(BoardVO boardVO) {
		 boardService.update(boardVO);
		 return "content/board/update_result";//수정확인 후,alert창 띄워보기
//		 return "redirect:/board/detail?boardNum=" + boardVO.getBoardNum();//수정확인 후, 다시 상세보기로
	}

	// 글삭제
	@GetMapping("/delete")
	public String delete(int boardNum) {
		boardService.delete(boardNum);
		return "redirect:/board/list";
	}
}
