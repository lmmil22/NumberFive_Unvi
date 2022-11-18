package kh.study.NF.mail.service;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    
    public void sendMdail(String memEmail,MemberVO memberVO) {
    	memberVO.setMemEmail(memEmail);
    	
    	// 수신 대상을 담을 arrayList 생성
    	ArrayList<String> toUerList = new ArrayList<>();
    	
    	//수신 대상 추가
    	toUerList.add(memEmail); //내게쓰기 보안설정 한 이메일 -> 수신가능 
    	
    	//수신 대상 개수
    	int toUserSize = toUerList.size();
    	
    	//SimpleMailMessage(단순 텍스트 구성 메일 메시지 생성할 때 이용)
    	SimpleMailMessage simpleMessage = new SimpleMailMessage();
    	
    	//수신자 설정.
    	simpleMessage.setTo((String[]) toUerList.toArray(new String[toUserSize]));
    	
    	// 메일제목
    	simpleMessage.setSubject("[NFU 임시 비밀번호 안내]");
    	
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
 	   
 	    // 암호화 키 이메일 내용에 넣기 
    	simpleMessage.setText("[임시 비밀번호 안내]" 
    							+ "회원님의 임시 발급된 비밀번호를 안내해드립니다. "
    							+ key.toString() 
    							+ "해당 임시비밀번호로 다시 로그인해주십시오.");
    	
    	
    	// 메일 발송
        javaMailSender.send(simpleMessage);
        System.out.println("__________________메일발송 성공__________________");
    }
 ////////////////////////////////////////////////////////////////////////

    
}