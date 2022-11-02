package kh.study.NF.professor.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.NF.dept.service.DeptService;

@Controller
@RequestMapping("/proF")
public class ProfessorController {

	@Resource(name = "deptService")
	private DeptService deptService;
	
	
	//강의 등록 페이지로 이동 
	@GetMapping("/regProfLec")
	public String regProfLecture(Model model) {
		//by지아
		
		
		 model.addAttribute("deptList",deptService.selectDeptList());
		
		
		//return "content/professor/professorRegLecture";
		return "content/professor/regproFLecture";
	}
}
