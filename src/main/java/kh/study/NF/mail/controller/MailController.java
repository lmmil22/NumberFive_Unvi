package kh.study.NF.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.NF.mail.service.MailService;
// by 유빈 : 이메일 기능 
@Controller
@RequestMapping("/mail")
public class MailController {
	//mail기능 서비스 객체 
	@Autowired
	private MailService mailService;
	// 깃 테스트 
	// 이메일 인증 
	@GetMapping("/sendMail")
	public String sendMail(Model model) {
		mailService.sendMdail();
		
		return "content/test/test11";
	}
	
	
		
	
}
