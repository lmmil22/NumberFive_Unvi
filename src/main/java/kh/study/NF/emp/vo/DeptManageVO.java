package kh.study.NF.emp.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
//by수경 deptNamage테이블의 VO입니다.
public class DeptManageVO {

	private String applyNo;
	private String stuNo;
	private String applyCode;
	private String applyDate;
	private String applyReason;
	private String approvalDate;
	private String processStatus;
	private String fromColl;
	private String fromDept;
	private String toColl;
	private String toDept;
	private String doubleMajorColl;
	private String doubleMajorDept;
	private String stuYear;
	private int stuSem;
}
