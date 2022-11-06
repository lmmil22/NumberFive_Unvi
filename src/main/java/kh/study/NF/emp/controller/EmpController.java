package kh.study.NF.emp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//by수경 학생의 복학, 휴학, 전과, 복수전공신청에 대한 관리자 영역

import kh.study.NF.emp.service.EmpService;


@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Resource(name = "empService")
	private EmpService empService;
	
	//by수경 휴학,복학 관리 페이지
	@GetMapping("/takeOffReturnUniv")
	public String takeOffReturnUniv(Model model) {
		
		model.addAttribute("applyList", empService.applyList());
	
		return "content/deptManage/takeOffReturnUniv";
	}
	
	//by수경 전과, 복수전공 관리 페이지
	@GetMapping("/changeAddMajor")
	public String changeAddMajor(Model model) {
		
		model.addAttribute("applyList", empService.applyList());
		
		return "content/deptManage/changeAddMajor";
	}
}
