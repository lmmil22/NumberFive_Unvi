package kh.study.NF.mail.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
// by 유빈 : 메일기능 구현
@Service
public class MailService {

    @Autowired
    public JavaMailSender javaMailSender;
    
    public void sendMdail() {
    	
    	// 수신 대상을 담을 arrayList 생성
    	ArrayList<String> toUerList = new ArrayList<>();
    	
    	//수신 대상 추가
    	toUerList.add("numberfive.zys@gmail.com"); //내게쓰기 보안설정 한 이메일 -> 수신가능 
    	
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
 	    // 암호화 키 이메일 내용에 넣기 
    	simpleMessage.setText("[임시 비밀번호 안내]" 
    							+ "회원님의 임시 발급된 비밀번호를 안내해드립니다. "
    							+ " < "+ key.toString() + " > "
    							+ "해당 임시비밀번호로 다시 로그인해주십시오.");
    	
    	System.out.println("XXXXXXXXXXXXXXXXX 임시비밀번호 생성 " + key.toString());
    	
    	// 메일 발송
        javaMailSender.send(simpleMessage);
        System.out.println("WWWWWWWWWWWWWWWWWWWWW 메일발송 성공");
    }
 ////////////////////////////////////////////////////////////////////////

    
}