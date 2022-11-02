package kh.study.NF.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.NF.member.service.MemberService;
import kh.study.NF.member.vo.MemberVO;
// by 유빈
@Controller
@RequestMapping("/member")

public class MemberController {
	@Resource(name = "memberService")
	private MemberService memberService;

	// by 유 : 학생정보시스템의 첫 화면 로그인 페이지입니다.
	// --> stu컨트롤러에서 학생,교수,교직원 모두 로그인해야해서 여기로 옮겼어!!)
	// 첫 화면 경로 : http://localhost:8081/member/login
	@GetMapping("/homeLogin")
	public String homeLogin(MemberVO  memberVO , Model model ) {
		
		return "content/common/homeLogin"; //by 유빈 :로그인페이지는 공통이라 common폴더 아래 login으로 파일 만들었어!!
	}
	
	// by 유 : 첫 홈화면에서 form태그로 실제 로그인 페이지입니다.
	@PostMapping("/loginProcess")
	public String loginProcess(HttpSession session, MemberVO memberVO) {
		//로그인 쿼리 실행
		MemberVO loginInfo = memberService.login(memberVO);
		System.out.println("로그인하러 넘어왔다");
		
		if (loginInfo != null) { // by 유 :로그인 성공시, 첫홈화면에서 본인의 학사정보시스템 페이지로 넘어갑니다.
			session.setAttribute("loginInfo", loginInfo);
		}
		else {
			System.out.println("로그인실패!!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			return "redirect:/member/home_login";// by 유 : 로그인 실패시, 다시 로그인 첫 홈화면으로 돌아갑니다.

		}
		return "content/common/main"; //by 유빈 :로그인페이지는 공통이라 common폴더 아래 login으로 파일 만들었어!!
	}
}
