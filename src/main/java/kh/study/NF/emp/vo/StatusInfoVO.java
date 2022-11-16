package kh.study.NF.emp.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
//by수경 statusInfo 테이블 기반VO
public class StatusInfoVO {

	private String statusNo;
	private String stuNo;
	private String nowStatus;
	private String afterStatus;
	private String applyDate;
	private String approvalDate;
	private String ingStatus;
	
}
