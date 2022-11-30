package kh.study.NF.FullCalendar.service;

import java.util.List;
import java.util.Map;

import kh.study.NF.FullCalendar.vo.CalendarVO;
//by 지아 캘린더에 사용되는 메소드 입니다
public interface CalendarService {

	//이벤트 리스트
	 List<Map<String, Object>> getEventList();
	 
	 //등록된 목록 조회
	List<CalendarVO> selectRegDate();

	//캘린더 등록
	void insertCal(CalendarVO calendarVO); 
	
}
