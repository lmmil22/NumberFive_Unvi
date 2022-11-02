package kh.study.NF.dept.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class DeptManageVO {
	//by수경 Dept_Manage 테이블 기반으로 만든 VO입니다.
	String applyNo;
	String stuNo;
	String applyCode;
	String applyDate;
	String applyReason;
	String approvalDate;
	String processStatus;
	String fromColl;
	String fromDept;
	String toColl;
	String toDept;
	String doubleMajorColl;
	String doubleMajorDept;
	
	
	
}
