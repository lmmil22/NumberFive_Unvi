package kh.study.NF.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stu")
public class StudentController {

	@GetMapping("/main")
	public String stuMain() {
		return "content/student/stuMain";
	}
	
	//by수경 학생이 전공학과를 변경(전과)하는 페이지로 이동 메소드(테스트 용)
	@GetMapping("/changeMajor")
	public String changeMajor() {
		return "content/student/changeMajor";
	}
}
