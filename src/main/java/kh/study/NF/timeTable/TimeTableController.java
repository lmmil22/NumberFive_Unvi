package kh.study.NF.timeTable;
import java.util.List;

import javax.annotation.Resource;

// by 유빈
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.professor.vo.LectureVO;
import kh.study.NF.timeTable.service.TimetableService;

@Controller
@RequestMapping("/timetable")
public class TimeTableController {

	@Resource(name = "timetableService")
	private TimetableService timetableService;
	
	// 학생_시간표_조회
	@GetMapping("/test")
	public String test() {
		return "content/timetable/timetable_test";
	}
	
	// 시간표불러오기_ajax
	@ResponseBody
	@PostMapping("/load")
	public List<LectureVO> load(){
		return timetableService.loadTimeTable();
	}
}
