package kh.study.NF.mail.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kh.study.NF.member.vo.MemberVO;

//by수경 학사경고 승인 받은 학생에게 보낼 메일 내역입니다.
@Service
public class ProbationMailService {
	 @Autowired
	 private JavaMailSender javaMailSender;
	
	public void sendMailWithFiles(MemberVO memberVO, String probReason)throws MessagingException, IOException{
		ProbationMailHandler mailHandler =  new ProbationMailHandler(javaMailSender);
		
		mailHandler.setTo(memberVO.getMemEmail());
		mailHandler.setSubject("[NFU]학사경고 안내");
		
		String htmlContent = "<p>" + "안녕하세요." + memberVO.getMemName() +"님"
							+"<p>"+"<b>"+" NumberFive University 학사지원팀입니다."+"<b>"+"<p>"+" 학사경고 처리되었음을 알려드리고자 메일을 송부합니다."
							+"<p style='color:blue; font-size:24px; font-weight:700;'>"+ "학사경고내역 : " + probReason 
							+"<p>"+"자세한 사항은 첨부파일을 확인하여 주시고, 학과 사무실에 문의하여 주십시오."+"<p>"+" 감사합니다."
							+"<p>"+ "<a href=\"http://localhost:8081/member/homeLogin\"><img src=\"https://i.ibb.co/2sgGf4Z/NFU-resize.png\" alt=\"NFU-resize\" border=\"0\"></a>";
		mailHandler.setText(htmlContent, true);
		mailHandler.setAttach("학사경고 안내.pdf","static/images/학사경고 안내.pdf" );
		//mailHandler.setInline("nf logo", "static/images/nf logo.png");
		mailHandler.probationMailSend();
	}

}
