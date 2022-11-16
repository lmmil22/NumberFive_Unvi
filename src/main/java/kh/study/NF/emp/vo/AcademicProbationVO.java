package kh.study.NF.emp.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

//by수경 학사경고테이블을 기반으로 만든 VO입니다.
@Getter
@Setter
@ToString
public class AcademicProbationVO {

	private String probNo;
	private String stuNo;
	private String probDate;
	private String probReason;
	private String semNo;
	private String memNo;
}
