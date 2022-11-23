package kh.study.NF.mail.service;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.sun.mail.util.logging.MailHandler;

//by수경 학사경고 받은 학생에게 메일 알림을 보내기 위해 만든 페이지입니다.(첨부파일 포함하여 보내기)

public class ProbationMailHandler {
	@Autowired
	private JavaMailSender javaMailSender;
	
	private MimeMessage mimeMessage;
	//메일 커스텀하기 위하여 추가
	private MimeMessageHelper mimeMessageHelper;

	
	public ProbationMailHandler(JavaMailSender javaMailSender) throws MessagingException{
		this.javaMailSender = javaMailSender;
		this.mimeMessage = javaMailSender.createMimeMessage();
		this.mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	
	}
	
	public void setFrom(String fromAddress) throws MessagingException{
		mimeMessageHelper.setFrom(fromAddress);
	}
	
	public void setTo(String email) throws MessagingException{
		mimeMessageHelper.setTo(email);
	}
	
	public void setSubject(String subject) throws MessagingException{
		mimeMessageHelper.setSubject(subject);
	}
	
	
	public void setText(String text, boolean useHtml) throws MessagingException{
		mimeMessageHelper.setText(text, useHtml);
	}
	
	//파일 첨부하기 위하여 추가
	 public void setAttach(String fileName, String path) throws MessagingException, IOException {
	    File file = new ClassPathResource(path).getFile();
	    FileSystemResource fsr = new FileSystemResource(file);

	    mimeMessageHelper.addAttachment(fileName, fsr);
	 }
	
	// 메일 안에 이미지 삽입하기 위하여 추가 
    public void setInline(String fileName, String path) throws MessagingException, IOException {
        File file = new ClassPathResource(path).getFile();
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        mimeMessageHelper.addInline(fileName, fileSystemResource);
    }

    public void probationMailSend() {
    	javaMailSender.send(mimeMessage);
    	
    }
	
}
