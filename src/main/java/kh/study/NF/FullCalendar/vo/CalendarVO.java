package kh.study.NF.FullCalendar.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CalendarVO {
	//BY 지아 학사일정 캘린더 테이블 기반으로 만든 VO입니다 
	
	private String calNo;
	private String calTitle;
	private String regdate;
	private String startdate;
	private String enddate;
	private String calColor;
	private String calWriter;
	private String calContent;
}
