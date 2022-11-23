
package kh.study.NF.professor.vo;

import kh.study.NF.student.vo.StudentVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StuGradeVO {

	//by 지아 Stu_Grade 테이블 기반으로 만든 vo입니다 
	private	String stuGradeNo;
	private	String lecNo;
	private	String stuNo;
	private	String grade;
	private	String semNo;
	
	
	private GradeVO gradeVO;
	private StudentVO studentVO;
	private String lecName;	
	
}
