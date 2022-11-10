package kh.study.NF.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.config.MemRole;
import kh.study.NF.member.service.MemberService;
import kh.study.NF.member.vo.MemberVO;

// by 유빈
@Controller
@RequestMapping("/member")
public class MemberController {
	@Resource(name = "memberService")
	private MemberService memberService;
	@Autowired
	private PasswordEncoder encoder;
//-------------------------------------------------------------------------------------------///	
	
	//회원가입(shop에서 복붙 -우린아직 회원가입은 없음)
	@PostMapping("/join")
	public String join(MemberVO memberVO) {
		// 쿼리문이기때문에 통상적으로는 컨트롤러에 작성하나
		// serviceImpl에 한번에 작성하기도 한다. (문제없음)
		// memberVO값에 status값 세팅해주기
		// null값들어가지않도록 Enum파일에 있는 'ACTIVE' 값넣어주기
		//(우리파일엔 없어서 )memberVO.setMemberStatus(MemberStatus.ACTIVE.toString());
		memberVO.setMemRole(MemRole.STUDENT.toString());
		
		// 위에서 불러온 암호화 객체를 사용해서 암호화한 비밀번호값 넣어 디비저장해준다.
		memberVO.setMemPw(encoder.encode(memberVO.getMemPw()));
		
		//회원가입
		memberService.join(memberVO);
		
		return "redirect:/member/afterLogin";
	}
//-------------------------------------------------------------------------------------------///	
		
	// by 유빈 : 학생정보시스템의 첫 화면 로그인 페이지입니다.
	// --> stu컨트롤러에서 학생,교수,교직원 모두 로그인해야해서 공통사항은 (common폴더)여기로 옮겼어!!)
	// 첫 화면 경로 : http://localhost:8081/member/homeLogin
	@GetMapping("/homeLogin")
	public String homeLogin( ) {
		
		return "content/common/homeLogin"; //by 유빈 :로그인페이지는 공통이라 common폴더 아래 login으로 파일 만들었어!!
	}
//-------------------------------------------------------------------------------------------///	
	
	// 이메일로 비밀번호 찾기 (ajax.ver) -> 모달 ajax사용 (ajaxlogin이라는 버튼을 클릭시)
	@ResponseBody //ajax사용할때(단,리턴값은 필요한 데이터만! html페이지가 아님!)
	@PostMapping("/ajaxLogin")
	public boolean ajaxLogin(HttpSession session, MemberVO memberVO) {
		MemberVO loginInfo = memberService.findPw(memberVO);
		// 로그인 정보 세션 저장
		if(loginInfo != null) {
			session.setAttribute("loginInfo", loginInfo);
		} 
		// 바로 loginInfo를 주지않고 삼항연산자 사용한다
		return loginInfo == null? false :true;//자료형 boolean
	}
//-------------------------------------------------------------------------------------------///	
	
	// ajax로 이메일 임시비밀번호 발급 후 이동 페이지 
	// 이전 shop에서 이름만->ajaxlogin 메소드 가져온것임.
	@GetMapping("/afterLogin")
	public String afterLogin1(boolean isLoginFail, Model model) {
		//-----로그인 성공 및 실패 여부를 html에 데이터 전달하기-------//
		System.out.println("@@@@@@@@@@@@@@@@@@@" + isLoginFail);
		model.addAttribute("isLoginFail",isLoginFail);
		
		return "content/common/afterLogin";
	}
	
	//로그인
	@PostMapping("/stuLogin")
	public String afterLogin2(boolean isLoginFail, Model model) {
		//-----로그인 성공 및 실패 여부를 html에 데이터 전달하기-------//
		//false면 로그인성공-fail이면 로그인 실패
		System.out.println("@@@@@@@@@@@@@@@@@@@" + isLoginFail);
		model.addAttribute("isLoginFail",isLoginFail);
		
		return "content/common/afterLogin";
	}
//-------------------------------------------------------------------------------------------///	
	// 나의 정보관리 클릭시,
	@GetMapping("/myinfo")
	public String myinfo(MemberVO memberVO) {
		//아직 아무것도...없다..세션?시큐리티? 고민중
		return "content/common/my_info";
	}
}
