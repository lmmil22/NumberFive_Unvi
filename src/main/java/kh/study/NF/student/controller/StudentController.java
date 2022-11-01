package kh.study.NF.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stu")
public class StudentController {
	//by수경 학생정보시스템의 첫페이지이자 로그인 페이지입니다.
	@GetMapping("/main")
	public String stuMain() {
		return "content/student/stuMain";
	}
	
	//by수경 학생이 전공학과를 변경(전과)하는 페이지로 이동 메소드(테스트 용)
	@GetMapping("/changeMajor")
	public String changeMajor() {
		return "content/student/changeMajor";
	}
	
	//by수경 학생의 전공학과 변경(전과), 휴학신청, 복학신청, 복수전공(전공 하나 더) 신청 현황을 모아 둔 페이지
	@GetMapping("/stuApplyList")
	public String stuApplyList() {
		return  "content/student/stuApplyList";
	}
	
}
