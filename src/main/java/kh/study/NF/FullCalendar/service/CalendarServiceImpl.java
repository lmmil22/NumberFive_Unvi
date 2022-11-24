package kh.study.NF.FullCalendar.service;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.FullCalendar.vo.CalendarVO;
//by 지아 캘린더에 사용되는 service입니다
@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private SqlSessionTemplate sqlSession;

	//by 지아 DB에 등록된 일정 조회
	@Override
	public List<CalendarVO> selectRegDate() {
		return sqlSession.selectList("calendarMapper.selectRegDate");

	}

	//by 지아 map에 조회된 일정 넣고 리턴
	@Override
	public List<Map<String, Object>> getEventList() {

		List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();

		List<CalendarVO> events = selectRegDate();

		// 필요한 시간양식 '2020-11-77'
		Map<String, Object> event = new HashMap<String, Object>();
//
//		event.put("start", calendarVO.getRegdate());
//		event.put("title", "받아온 데이터");
//		event.put("end", LocalDate.now());
//		eventList.add(event);
		
		 for(int i =0;  i < events.size(); i++ ) {
			
				event = new HashMap<String, Object>();
				
				event.put("start", events.get(i).getStartdate());
				
				//System.out.println("!!!!!!!!!!!!!!!"+events.get(i).getRegdate());
				event.put("title", events.get(i).getTitle());
				event.put("color", events.get(i).getCalColor());
				
				if (events.get(i).getEnddate() != null) {
					event.put("end", events.get(i).getEnddate());
					
				}
				
				//if (end 데이터값이 없으면 데이터 값 안들어가게 만들어주기
				eventList.add(event);
					 
			 
		 }
			System.out.println(eventList);

		// 캘린더에 넘겨주는 리스트
		return eventList;

	}

}
