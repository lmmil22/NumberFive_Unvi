package kh.study.NF.timeTable;
// by 유빈
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timetable")
public class TimeTableController {
	
	
	// 학생_시간표_조회
	@GetMapping("/test")
	public String test() {
		return "content/timetable/timetable_test";
	}
}
