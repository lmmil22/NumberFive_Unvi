package kh.study.NF.mail.controller;
// 미사용? 파일
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.emp.service.EmpService;
import kh.study.NF.emp.vo.AcademicProbationVO;
import kh.study.NF.emp.vo.StuinfoAndProbationListVO;
import kh.study.NF.mail.service.MailService;
import kh.study.NF.mail.service.ProbationMailService;
import kh.study.NF.mail.service.StuOutMailService;
import kh.study.NF.member.vo.MemberVO;
// by 유빈 : 이메일 기능 
@Controller
@RequestMapping("/mail")
public class MailController {
	//mail기능 서비스 객체 
	@Autowired
	private MailService mailService;
	
	//by수경 학사경고 메일을 보내기 위하여 사용
	@Autowired
	private ProbationMailService probationMailService;
	
	//by수경 학사경고내역 목록 쿼리 가져오기 위하여 사용
	@Resource(name = "empService")
	private EmpService empService;
	
	//by수경 학생에게 제적 메일을 보내기 위하여 사용
	@Autowired
	private StuOutMailService stuOutMailService;
	
//------------------------------------------------------------------------//

	// 이메일 인증 
	@GetMapping("/sendMail")
	public String sendMail(String memEmail,Model model,MemberVO memberVO) {
		mailService.sendMdail(memEmail, memberVO);
		
		return "content/test/test11";
	}
//------------------------------------------------------------------------//

	// 스프링부트 이메일로 비밀번호 찾기 : 구글링으로 가져온 소스
	// 1번
	//Email과 name의 일치여부를 check하는 컨트롤러
//	@GetMapping("/check/findPw")
//	@ResponseBody
//    public  Map<String, Boolean> pw_find(String userEmail, String userName){
//        Map<String,Boolean> json = new HashMap<>();
//        boolean pwFindCheck = userService.userEmailCheck(userEmail,userName);
//
//        System.out.println(pwFindCheck);
//        json.put("check", pwFindCheck);
//        return json;
//    }
//	// 2번
//	//등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
//    @PostMapping("/check/findPw/sendEmail")
//    @ResponseBody 
//    public void sendEmail(String userEmail, String userName){
//       // MailDto dto = sendEmailService.createMailAndChangePassword(userEmail, userName);
//        //sendEmailService.mailSend(dto);
//
//    }
//-----------------------------------------------------------------------//
	//by수경 학사경고 메일 보내기 위하여 사용
	@ResponseBody
	@PostMapping("sendProbationMailAjax")
	public void sendProbationMailAjax(MemberVO memberVO, String probReason) throws MessagingException, IOException{
		probationMailService.sendMailWithFiles(memberVO, probReason);
	}

	//by수경 학생에게 제적 안내 메일 보내기 위하여 사용
	@ResponseBody
	@PostMapping("/sendStuOutMailAjax")
	public void sendStuOutMailAjax(MemberVO memberVO, String stuNo) throws MessagingException, IOException{
		//제적된 학생이 받았던 학사경고 내역 목록 쿼리
		List<AcademicProbationVO> probationList = empService.probationReason(stuNo);
		
		stuOutMailService.sendStuOutMail(memberVO, probationList);
	}
	
}
