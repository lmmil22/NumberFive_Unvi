package kh.study.NF.FullCalendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/full")
public class FullCalendarController {
	
	@GetMapping("/cal")
	public String cal() {
		
		
		
		return "content/test/test11";
	}
}
