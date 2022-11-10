package kh.study.NF.dept.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class DeptVO {
	//by 지아 dept 테이블 기반으로 만들었습니다
	private String deptNo; 
	private String deptName;
	private String collNo;
	
	//by수경 학과 선택 시 재학중인 학과 제외하기 위해 추가
	private String stuNo;
}
