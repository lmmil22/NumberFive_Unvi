package kh.study.NF.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.NF.admin.service.AdminService;
import kh.study.NF.member.service.MemberService;

//by 지아 내 정보 조회하는 컨트롤러 입니다  
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Resource(name = "adminService")
	private AdminService adminService;
	
	
	
	//
	@GetMapping("/memDetail")
	public String memDetail(Model model , String memNo) {
		
		
		
		return "";
	}
	
	
}