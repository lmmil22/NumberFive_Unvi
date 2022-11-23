package kh.study.NF.emp.vo;

import groovy.transform.ToString;
import kh.study.NF.student.vo.StudentVO;
import lombok.Getter;
import lombok.Setter;
//by수경 학생 제적 테이블 기반으로 만든 VO입니다.
@Getter
@Setter
@ToString
public class StuOutVO {

	private String stuOutNo;
	private String stuNo;
	private String stuOutDate;
	private String stuOutReason;

}
