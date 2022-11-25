package kh.study.NF.FullCalendar.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.FullCalendar.service.CalendarService;
import kh.study.NF.FullCalendar.vo.CalendarVO;
//by 지아 학사일정 관련 controller입니다
@Controller
@RequestMapping("/calendar")
public class FullCalendarController {
	
   @Autowired
    CalendarService calendarService;

	@GetMapping("/cal")
	public String cal() {
		
		return "content/academicSchedule/calList";
	}
	
	@GetMapping("/event") //ajax 데이터 전송 URL
    public @ResponseBody List<Map<String, Object>> getEvent(){
		//List<Map<String, Object>>의 자료형으로 보낼시 자동으로 JSON으로 변경이 된다.
	  calendarService.selectRegDate();
		return calendarService.getEventList();
	 }
	
	@PostMapping("/insertCal")
	public String insertCal(Authentication authentication , CalendarVO calendarVO) {
		User user = (User)authentication.getPrincipal();
		calendarVO.setCalWriter(user.getUsername());
		
		calendarService.insertCal(calendarVO);
		
		return "redirect:/calendar/cal";
	}
	
}
