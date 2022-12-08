package kh.study.NF.mail.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kh.study.NF.member.service.MemberService;
import kh.study.NF.member.vo.MemberVO;
// by 유빈 : 메일기능 구현
@Service
public class MailService {

    @Autowired
    public JavaMailSender javaMailSender;
    @Autowired
	private PasswordEncoder encoder;
    @Resource(name = "memberService")
	private MemberService memberService;
    
/////////////////////////////////////////////// < by 유빈: 메일 보내기 메소드 >////////////////////////////////////////////////////////////////////////////	

	public void sendMdail(String memEmail,MemberVO memberVO) {
		
		// 메일 내용
    	// 암호화 키로 임시비밀번호 랜덤 생성
    	StringBuffer key = new StringBuffer();
 	    Random rnd = new Random();
 	    for (int i = 0; i < 10; i++) {
 	        int index = rnd.nextInt(3);
 	        switch (index) {
 	            case 0:
 	                key.append((char) ((int) (rnd.nextInt(26)) + 97));
 	                break;
 	            case 1:
 	                key.append((char) ((int) (rnd.nextInt(26)) + 65));
 	                break;
 	            case 2:
 	                key.append((rnd.nextInt(10)));
 	                break;
 	        }
 	    }
 	    //출력확인
 	    System.out.println("________________임시비밀번호 생성 확인____________" + key.toString());
 	    

 	    memberVO.setMemPw( encoder.encode(key.toString()) );
 	    // 위처럼 바꾼 비밀번호를 디비에 넣어놔야한다. 
 	    // 왜냐면, 이 바꾼 비밀번호로 user가 로그인을 해야하니까.
 	    
 	    // 임시비번으로 update시키기
 	    memberService.updatePw(memberVO);
 	    
 	    //메일핸들러 불러오기
		MailHandler mailHandler;
		try {
			mailHandler = new MailHandler(javaMailSender);

			mailHandler.setTo(memEmail);
			
			// 제목
			mailHandler.setSubject("[NFU 임시 비밀번호 안내]");
			
			// html 로 작성된 내용.
			mailHandler.setText("<h1>" + "[ 임시 발급 비밀번호 안내 ]" + "</h1>"
								+"NumberFive University 학사지원팀입니다." 
								+ "<p>"
							    + "회원님의 임시비밀번호 발급되었음을 알려드리고자 메일을 송부합니다."
							    + "<p>"
								+ "아래 임시 발급된 비밀번호를 확인해 주시기바랍니다. "
								+ "<p>"
								+ "<h2>" + key.toString() + "</h2>"
								+ "<p>"
								+ "<p>" + "해당 임시비밀번호를 통해 로그인 해주시기바랍니다."
								+ "<p>" + "이후, 회원정보수정 페이지에서 비밀번호 변경을 요청드립니다."
								+ "<p>"+ "문의사항은 학과 사무실을 통해 문의주시기 바랍니다." 
								+ " 감사합니다." 
								+ "<p>"
							    + " ※ 상단의 이미지 클릭시, 해당 홈페이지 로그인화면으로 바로 이동가능합니다." 
							    +"<p>"+ "<a href=\"http://localhost:8081/member/homeLogin\"><img src=\"https://i.ibb.co/2sgGf4Z/NFU-resize.png\" alt=\"NFU-resize\" border=\"0\"></a>"
								, true);
			
			mailHandler.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	
//////////////////////////////////////////////// by 유빈 :메일핸들러 사용위해서 ////////////////////////////////////////////////////////////////////////////	
	public class MailHandler {
		private JavaMailSender sender;
		private MimeMessage message;
		private MimeMessageHelper msgHelper;

		public MailHandler(JavaMailSender sender) throws MessagingException {
			this.sender = sender;
			message = sender.createMimeMessage();
			msgHelper = new MimeMessageHelper(message, true, "UTF-8");
		}

		public void setFrom(String fromAddress) throws MessagingException {
			msgHelper.setFrom(fromAddress);
		}

		public void setTo(String email) throws MessagingException {
			msgHelper.setTo(email);
		}

		public void setSubject(String subject) throws MessagingException {
			msgHelper.setSubject(subject);
		}

		public void setText(String text, boolean useHtml) throws MessagingException {
			msgHelper.setText(text, useHtml);
		}

		public void setAttach(String displayFileName, MultipartFile file) throws MessagingException {
			msgHelper.addAttachment(displayFileName, file);
		}

		public void setInline(String contentId, MultipartFile file) throws MessagingException, IOException {
			msgHelper.addInline(contentId, new ByteArrayResource(file.getBytes()), "image/jpeg");
		}

		public void send() {
			try {
				sender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
    
    
    
    
    
}