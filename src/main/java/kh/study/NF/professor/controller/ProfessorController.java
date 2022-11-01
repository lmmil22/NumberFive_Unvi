package kh.study.NF.professor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proF")
public class ProfessorController {

	//강의 등록 페이지로 이동 
	@GetMapping("/regProfLec")
	public String regProfLecture() {
		//by지아
		
		return "content/professor/professorRegLecture";
	}
}
