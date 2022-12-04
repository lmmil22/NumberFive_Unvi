package kh.study.NF.mail.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kh.study.NF.emp.vo.AcademicProbationVO;
import kh.study.NF.member.vo.MemberVO;

//by수경 학사경고 승인 받은 학생에게 보낼 메일 내역입니다.
@Service
public class StuOutMailService {
	 @Autowired
	 private JavaMailSender javaMailSender;
	
	public void sendStuOutMail(MemberVO memberVO, List<AcademicProbationVO> probationList)throws MessagingException, IOException{
		ProbationMailHandler mailHandler =  new ProbationMailHandler(javaMailSender);
		
		mailHandler.setTo(memberVO.getMemEmail());
		mailHandler.setSubject("[NFU]제적안내 안내");
		
		String htmlContent = "<p>" + "안녕하세요." + memberVO.getMemName() +"님"
							+"<p>"+"<b>"+" NumberFive University 학사지원팀입니다."+"<b>"+"<p>"+" 아래와 같은 사유로 NFU에서 제적처리 되었음을 알려드립니다.";
							
							for(AcademicProbationVO prob : probationList) {
								htmlContent += "<p style='color:blue; font-size:24px; font-weight:700;'>"+"학사경고내역 : " + prob.getProbReason() +"("+ prob.getProbDate() +")" + "<p>";
							}
		
				htmlContent += "<p>"
							+"<p>"+"함께하지 못하여 아쉽지만,  NFU가 당신의 행복을 응원하겠습니다."+"<p>"+" 감사합니다."
							+"<p>"+ "<a href=\"https://imgbb.com/\"><img src=\"https://i.ibb.co/2sgGf4Z/NFU-resize.png\" alt=\"NFU-resize\" border=\"0\"></a>";
		mailHandler.setText(htmlContent, true);
		//mailHandler.setAttach("학사경고 안내.pdf","static/images/학사경고 안내.pdf" );
		mailHandler.probationMailSend();
		
		
		
		
		
		
		// 자바에서 백틱 비슷한 애..
		//String.format("학사경고내역 : %s , %d일", "결석많아!", 50);
	}

}
