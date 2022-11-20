package kh.study.NF.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//by 지아 채팅사용 컨트롤러 입니다
@Controller
@RequestMapping("/stuchat")
public class ChatController {

	
	@GetMapping("/chattingList")
	public String chattingList() {
		
		
		return "content/chat/chatting";
	}
}
