package kh.study.NF.timeTable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
// by 유빈
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.admin.service.AdminService;
import kh.study.NF.member.service.MemberService;
import kh.study.NF.member.vo.MemberVO;
import kh.study.NF.professor.vo.LectureVO;
import kh.study.NF.timeTable.service.TimetableService;
//by 유빈: 시간표기능구현

@Controller
@RequestMapping("/timetable")
public class TimeTableController {

	@Resource(name = "timetableService")
	private TimetableService timetableService;
	@Resource(name = "memberService")
	private MemberService memberService;
	
	
	// 학생_시간표_조회
	@GetMapping("/load")
	public String load(String memNo,Model model,Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		memNo = user.getUsername();
		model.addAttribute("memberVO",memberService.selectMemberDetail(memNo));
		
		return "content/timetable/timetable";
	}
	
	// 시간표불러오기_ajax
	@ResponseBody
	@PostMapping("/load")
	public List<LectureVO> load(){
		return timetableService.loadTimeTable();
	}
}
