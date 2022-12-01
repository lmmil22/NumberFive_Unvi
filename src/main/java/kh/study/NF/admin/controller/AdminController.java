package kh.study.NF.admin.controller;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.admin.service.AdminService;
import kh.study.NF.member.service.MemberService;
import kh.study.NF.member.vo.MemberVO;

//by 지아 내 정보 조회하는 컨트롤러 입니다  
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Resource(name = "adminService")
	private AdminService adminService;
	
	
	
	//by 지아 상세조회페이지이동
	@GetMapping("/memDetail")
	public String memDetail(Model model , String memNo ,Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		memNo = user.getUsername();
		model.addAttribute("member",adminService.selectMemDetail(memNo));
		
		//학생 이미지 등록이 된다면 html 고쳐야함!!!!! //수정필요
		return "content/admin/memberDetail";
	}
	
	@PostMapping("/updateForm")
	public String updateFormAjax(MemberVO memberVO , Model model , Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		memberVO.setMemNo(user.getUsername());
		adminService.updateMemDetail(memberVO);
	
		return "redirect:/admin/memDetail?memNo=" + user.getUsername();
	}
	
//---------------------------------------------------------------------//	
	//by 유빈
	
}
