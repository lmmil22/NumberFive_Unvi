package kh.study.NF.chat.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//by 지아 chat 기능관련 controller입니다
@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@RequestMapping("/stuChat")
	public String popup(){
		return "content/chat/chatting";
	}
}
