package kh.study.NF.professor.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LectureTimeVO {
	//by지아 LectureTimeVO table로 만든 VO
	private String timeNo;
	private String lecDay;
	private int firstTime;
	private int lastTime;
	private String lecNo;
}
