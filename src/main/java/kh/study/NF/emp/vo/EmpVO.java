package kh.study.NF.emp.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
//by수경 emp테이블을 기반한 VO
public class EmpVO {
	private String empNo;
	private String empType;
	private String deptNo;
	private String memNo;
	private String collNo;
}
