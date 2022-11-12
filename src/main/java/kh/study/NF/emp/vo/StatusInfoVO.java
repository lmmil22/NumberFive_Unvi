package kh.study.NF.emp.vo;

import java.util.List;

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
	
	//학생의 복학, 휴학, 전과, 복수전공 신청 리스트
	private List<DeptManageVO> DeptManageList;
}
