package kh.study.NF.professor.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LectureVO {
	//by지아 LectureVO table로 만든 VO
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
	private String lecDay;
	private String firstTime;
	private String lastTime;
	
	
	private LecturePdfVO lecturePdfVO;
	private LectureTimeVO lectureTimeVO;
	
	
}
