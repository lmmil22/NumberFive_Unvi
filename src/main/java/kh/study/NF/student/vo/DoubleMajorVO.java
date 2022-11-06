package kh.study.NF.student.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

//by수경 복수전공 테이블 VO입니다
@Getter
@Setter
@ToString
public class DoubleMajorVO {

	private String doubleNo;
	private String doubleStatus;
	private String insertDate;
	private String updateDate;
	private String reason;
	private String stuNo;
	private String collNo;
	private String deptNo;
	private String doubleColl;
	private String doubleDept;
	
}
