package kh.study.NF.professor.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LectureVO {
	private String lecNo;
	private String lecName;
	private int lecScore;
	private String collNo;
	private String deptNo;
	private String empNo;
	private String createDate;
	private int maxNum;
	private int nowNum;
	private String semNo;
	private String lecStatus;
}
