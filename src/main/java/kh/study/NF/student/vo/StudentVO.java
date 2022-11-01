package kh.study.NF.student.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class StudentVO {
	//by수경 STU table로 만든 VO입니다.
	String stuNo;
	String collNo;
	String majorCode;
	String doubleCode;
	String stuYear;
	String stuSem;
	String stuStatus;
	String memNo ;

}
