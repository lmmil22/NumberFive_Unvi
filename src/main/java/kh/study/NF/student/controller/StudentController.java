package kh.study.NF.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stu")
public class StudentController {
	//by수경 학생정보시스템의 첫페이지 로그인 페이지입니다.
	// ->by 유빈 : 첫화면은 교직원교수학생모두 로그인가능하니까 헷갈리지않게  member로 할게요!
	/*
	 * @GetMapping("/main") public String stuMain() { return
	 * "content/student/stuMain"; }
	 */
	
	//by수경 학생이 전공학과를 변경(전과)하는 페이지로 이동 메소드(테스트 용)
	@GetMapping("/changeMajor")
	public String changeMajor() {
		return "content/student/changeMajor";
	}
	
	//by수경 학생이 학교를 휴학신청하는 페이지로 이동
	@GetMapping("/takeOffUniv")
	public String takeOffUniv() {
		
		return  "content/student/takeOffUniv";
	}
	
	//by수경 학생이 학교를 복학신청하는 페이지로 이동
	@GetMapping("/returnUniv")
	public String returnUniv() {
		
		return  "content/student/returnUniv";
	}
	
	//by수경 학생의 전공학과 변경(전과), 휴학신청, 복학신청, 복수전공(전공 하나 더) 신청 현황을 모아 둔 페이지
	@GetMapping("/stuApplyList")
	public String stuApplyList() {
		return  "content/student/stuApplyList";
	}
	
}
