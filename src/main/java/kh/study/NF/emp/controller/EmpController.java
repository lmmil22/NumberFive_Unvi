package kh.study.NF.emp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.emp.service.EmpService;
import kh.study.NF.emp.vo.DeptManageVO;

//by수경 학생의 복학, 휴학, 전과, 복수전공신청에 대한 관리자 영역
@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Resource(name = "empService")
	private EmpService empService;
	
	//by수경 휴학,복학 관리자 페이지
	@GetMapping("/takeOffReturnUniv")
	public String takeOffReturnUniv(Model model) {
		
		model.addAttribute("applyList", empService.applyList());
	
		return "content/deptManage/takeOffReturnUniv";
	}
	
	//by수경 전과, 복수전공 관리자 페이지
	@GetMapping("/changeAddMajor")
	public String changeAddMajor(Model model) {
		
		model.addAttribute("applyList", empService.applyList());
		
		return "content/deptManage/changeAddMajor";
		
	}
	
	//by수경 학생이 복학, 휴학, 전과, 복수전공 해당 학기에 신청내역 있는지 확인(수정하기)
	@ResponseBody
	@PostMapping("/checkApplyAjax")
	public DeptManageVO checkApply(DeptManageVO deptManageVO) {
		
		return empService.checkApply(deptManageVO);
		
	}
	
}
