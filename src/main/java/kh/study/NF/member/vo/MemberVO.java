package kh.study.NF.member.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

//by 유빈

@Getter
@Setter
@ToString
public class MemberVO {
//	MEM_NO
//	MEM_PW
//	MEM_NAME
//	MEM_EMAIL
//	MEM_ADDR
//	MEM_ADDR_DETAIL
//	MEM_TELL
//	MEM_IMAGE
//	MEM_GENDER
//	MEM_BIRTH
//	MEM_ROLE

	
	//by수경 member table로 만든 VO
	//@NotBlank(message = "학번 및 교직원 번호는 필수입력입니다.")
	private String memNo;//아이디이자 학번,교번
	
	//  by 유빈 : 정규식 코드 사용해야한다.(복사붙여넣기)
	// 8-16자리, 영어대소문자,특수문자포함(#은 포함시키지않음! 주의!)
	
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$" 
    //		, message = "비밀번호 형식에 맞지 않습니다")
	//@NotBlank(message = "비밀번호는 필수입력입니다.")
	private String memPw;
   
    // by 유빈 :  max : 영어숫자 상관없이 최대문자길이 뜻함
    //@NotBlank(message = "이름은 필수입력입니다.")
	//@Size(max = 5,message = "제한된 길이를 초과했습니다.")
	private String memName;
    
   // @Pattern(regexp = "/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i"
    //		, message = "이메일 형식에 맞지 않습니다")
   // @NotBlank(message = "이메일은 필수입력입니다.")
   // @Email
    private String memEmail;
   
    //@NotBlank(message = "주소입력은 필수입니다.")
	private String memAddr;
    
    //@NotBlank(message = "상세주소는 필수입력입니다.")
	private String memAddrDetail;
	
	private String memTell; 
	
	private String memImage;
	
	private String memGender;
	private String memBirth;
	
	private String memRole;
	
	
	
	
	
}
