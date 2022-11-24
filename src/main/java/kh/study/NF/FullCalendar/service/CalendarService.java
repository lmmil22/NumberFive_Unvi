package kh.study.NF.FullCalendar.service;

import java.util.List;
import java.util.Map;

import kh.study.NF.FullCalendar.vo.CalendarVO;

public interface CalendarService {

	 List<Map<String, Object>> getEventList();
	List<CalendarVO> selectRegDate();
	 
}
