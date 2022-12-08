//by 유빈: 시간표기능구현
package kh.study.NF.timeTable.Controller;
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

import kh.study.NF.member.service.MemberService;
import kh.study.NF.professor.service.ProfessorService;
import kh.study.NF.professor.vo.LectureVO;
import kh.study.NF.timeTable.service.TimetableService;

@Controller
@RequestMapping("/timetable")
public class TimeTableController {

	@Resource(name = "timetableService")
	private TimetableService timetableService;
	@Resource(name = "memberService")
	private MemberService memberService;
	@Resource(name = "professorService")
	private ProfessorService professorService;
/////////////////////////////////////////////////////////////////////////////////////////////////	
//----[  학생 시간표  ]-----------------------------------------------------------------------------------//
	// 학생_시간표_조회
	@GetMapping("/load")
	public String load(String memNo,Model model,Authentication authentication, String lecNo) {
		User user = (User)authentication.getPrincipal();
		memNo = user.getUsername();
		
		model.addAttribute("memberVO",memberService.selectMemberDetail(memNo));
		
		return "content/timetable/timetable";
	}
	
	// 학생 시간표불러오기_ajax
	@ResponseBody
	@PostMapping("/load")
	public List<LectureVO> load(){
		return timetableService.loadTimeTable();
	}
//-------[  교수 시간표  ]-------------------------------------------------------------------------------------//
	// 교수_시간표_조회
	@GetMapping("/loadProf")
	public String loadProf(String memNo,Model model,Authentication authentication, String lecNo) {
		User user = (User)authentication.getPrincipal();
		memNo = user.getUsername();
		model.addAttribute("memberVO",memberService.selectMemberDetail(memNo));
		
		//가져온소스 미사용시 삭제//////////////////////////////////////
		//List<LectureVO> list = professorService.selectLecList();
		//model.addAttribute( "lecList", list);
		//for(LectureVO vo : list) {
		//	System.out.println(vo.getLecturePdfVO().getAttachedPdfName());
		//}
		//////////////////////////////////////////////////////////////////
		return "content/timetable/timetable_prof";
	}
	
	// 교수 시간표불러오기_ajax
	@ResponseBody
	@PostMapping("/loadProf")
	public List<LectureVO> loadProf(String empNo ,Authentication authentication){
		User user = (User)authentication.getPrincipal();
		empNo = user.getUsername();
		
		return timetableService.loadTimeTableProf(empNo);
	}
	
//-------[  테스트  ]-------------------------------------------------------------------------------------//
	
	// 강의시간표 pdf 버튼클릭시 다운로드
	@GetMapping("/test")
	public String test() {
		return"test";
	}
	
}
