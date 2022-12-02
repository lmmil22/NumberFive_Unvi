//by 유빈: 시간표기능구현
package kh.study.NF.timeTable;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import kh.study.NF.professor.vo.LecturePdfVO;
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
	
	// 학생_시간표_조회
	@GetMapping("/load")
	public String load(String memNo,Model model,Authentication authentication, String lecNo) {
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
	
	// 강의시간표 pdf 버튼클릭시 다운로드
	@GetMapping("/download")
	public ResponseEntity<Object> download(String lecNo) {
		LecturePdfVO attachedInfo = professorService.selectLecPdf(lecNo);
		// (유빈 경로)
		String path = "C:\\workSpaceSTS_TEAM\\NumberFive_Unvi\\src\\main\\resources\\static\\pdf\\" + attachedInfo.getAttachedPdfName();
		
		try {
			Path filePath = Paths.get(path);
			org.springframework.core.io.Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기
			
			File file = new File(path);
			
			HttpHeaders headers = new HttpHeaders();
			
			System.out.println(attachedInfo.getOriginPdfName()+"!!!!!!");
			
			String downloadName =  new String(attachedInfo.getOriginPdfName().getBytes("UTF-8"), "ISO-8859-1");
			
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(downloadName).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
	
	
}
