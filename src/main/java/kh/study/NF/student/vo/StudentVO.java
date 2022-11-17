package kh.study.NF.student.vo;

import groovy.transform.ToString;
import kh.study.NF.dept.vo.ColleageVO;
import kh.study.NF.dept.vo.DeptVO;
import kh.study.NF.emp.vo.StatusInfoVO;
import kh.study.NF.member.vo.MemberVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class StudentVO {
	//by수경 STU table로 만든 VO입니다.
	private String stuNo;
	private String collNo;
	private String deptNo;
	private String doubleNo;
	private String stuYear;
	private int stuSem;
	private String stuStatus;
	private String memNo ;
	
	//by수경 association 1:1
	private MemberVO memberVO;
	//by수경 association 1:1 (전공대학, 전공학과, 복수전공)
	private DeptVO deptVO;
	private ColleageVO collVO;
	private DoubleMajorVO doubleMajorVO;
	//by수경 학사경고/제적을 위한 1:1
	private StatusInfoVO statusInfoVO;
	
	


}
