package kh.study.NF.student.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.dept.service.DeptService;
import kh.study.NF.dept.vo.DeptVO;
import kh.study.NF.student.service.StudentService;

@Controller
@RequestMapping("/stu")
public class StudentController {

	@Resource(name = "studentService")
	private StudentService studentService;
	
	@Resource(name = "deptService")
	private DeptService deptService;
	
	
	//by수경 학생정보시스템의 첫페이지 로그인 페이지입니다.
	@GetMapping("/main")
	public String stuMain() {
		return "content/student/stuMain";
	}
	
	//by수경 학생이 전공학과를 변경(전과)하는 페이지로 이동 메소드(테스트 용)
	@GetMapping("/changeMajor")
	public String changeMajor(Model model) {
		//by수경 학생정보 쿼리문
		model.addAttribute("stuInfo", studentService.studentInfo());
		
		//by수경 변경할 전공대학, 전공학과 선택 쿼리문
		 model.addAttribute("deptList",deptService.selectDeptList());
		 model.addAttribute("collList",deptService.selectCollList());
		
		return "content/student/changeMajor";
	}
	
	//by수경 학생이 전공학과를 변경(전과)하는 페이지로 이동 메소드(테스트 용)
	@GetMapping("/addMajor")
	public String addMajor(Model model) {
		//by수경 학생정보 쿼리문
		model.addAttribute("stuInfo", studentService.studentInfo());
		
		//by수경 변경할 전공대학, 전공학과 선택 쿼리문
		model.addAttribute("deptList",deptService.selectDeptList());
		model.addAttribute("collList",deptService.selectCollList());
		
		return "content/student/addMajor";
	}
	
	//by수경 변경할 전공대학 선택 시, 전공학과 선택 ajax
	@ResponseBody
	@PostMapping("/getMajorAjax")
	public List<DeptVO> getMajorAjax(String collNo) {
		
		return deptService.getDeptList(collNo);
	}
	
	//by수경 학생이 학교를 휴학신청하는 페이지로 이동
	@GetMapping("/takeOffUniv")
	public String takeOffUniv(Model model) {
		//by수경 학생정보 쿼리문
		model.addAttribute("stuInfo", studentService.studentInfo());
		return  "content/student/takeOffUniv";
	}
	
	//by수경 학생이 학교를 복학신청하는 페이지로 이동
	@GetMapping("/returnUniv")
	public String returnUniv(Model model) {
		//by수경 학생정보 쿼리문
		model.addAttribute("stuInfo", studentService.studentInfo());
		
		return  "content/student/returnUniv";
	}
	
	//by수경 학생의 전공학과 변경(전과), 휴학신청, 복학신청, 복수전공(전공 하나 더) 신청 현황을 모아 둔 페이지
	@GetMapping("/stuApplyList")
	public String stuApplyList() {
		return  "content/student/stuApplyList";
	}
	
}
