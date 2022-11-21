package kh.study.NF.FullCalendar.service;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	

	@Override
	public List<Map<String, Object>> getEventList() {
		 Map<String, Object> event = new HashMap<String, Object>();
	        List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
	        event.put("start", LocalDate.now());
	        event.put("title", "test");
	        event.put("end",LocalDate.now());
	        eventList.add(event);
	        event = new HashMap<String, Object>();
	        event.put("start", LocalDate.now().plusDays(3));
	        event.put("title", "test2");
	        event.put("end",LocalDate.now().plusDays(4));
	        eventList.add(event);
	        return eventList;
	}
}
