package kh.study.NF.member.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class MemberVO {
	//by수경 member table로 만든 VO
	String memNo;
	String memPw;
	String memName;
	String memEmail;
	String memAddr;
	String memAddrDetail;
	String memTell;
	String memImage;
	String memGender;
	String memBirth;
	String memRole;
	
	
}
