package kh.study.NF.chat.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@RequestMapping("/stuChat")
	public String popup(){
		return "content/chat/chatting";
	}
}
