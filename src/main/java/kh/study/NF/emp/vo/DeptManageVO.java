package kh.study.NF.emp.vo;

import java.util.List;

import groovy.transform.ToString;
import kh.study.NF.student.vo.StudentVO;
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
	
	//by수경 applyNo를 여러개 담을 List
	private List<String> applyNoList;
	
	//by수경 화면에 학생이름 표시를 위하여 추가
	private String memName;
	
	//by수경 전과/복전 신청서 관리자 페이지 출력을 위하여 추가
	private StudentVO studentVO;


}
