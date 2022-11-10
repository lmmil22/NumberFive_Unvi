package kh.study.NF.board.controller;
// by 유빈
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	//boardService 가져오기
	
	//게시판 목록조회
	@GetMapping("/list")
	public String list() {
		return "content/common/board_list";
	}
	
}
