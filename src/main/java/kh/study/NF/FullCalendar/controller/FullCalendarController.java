package kh.study.NF.FullCalendar.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.FullCalendar.service.CalendarService;
//by 지아 학사일정 관련 controller입니다
@Controller
@RequestMapping("/calendar")
public class FullCalendarController {
	
   @Autowired
    CalendarService calendarService;

	@GetMapping("/cal")
	public String cal() {
		
		return "content/test/calList";
	}
	
	@GetMapping("/event") //ajax 데이터 전송 URL
    public @ResponseBody List<Map<String, Object>> getEvent(){
	  
		return calendarService.getEventList();
	 }

}
