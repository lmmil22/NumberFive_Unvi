package kh.study.NF.professor.vo;

import javax.validation.constraints.AssertTrue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LectureTimeVO {
	//by지아 LectureTimeVO table로 만든 VO
	private String timeNo;
	
    @AssertTrue(message = "이미 등록된 날짜입니다") // return true면 정상 false면 비정상
	private String lecDay;
	
	private String firstTime;
	private String lastTime;
	private String lecNo;
}
