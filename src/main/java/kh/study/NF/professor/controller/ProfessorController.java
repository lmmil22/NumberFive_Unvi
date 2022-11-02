package kh.study.NF.professor.controller;

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
		 model.addAttribute("collList",deptService.selectCollList());
		
		
		//return "content/professor/professorRegLecture";
		return "content/professor/regproFLecture";
	}
	
	//coll 목록을 누르면 dept 가 변경된다 
	@ResponseBody
	@PostMapping("/getDeptListAjax")
	public List<DeptVO> getDeptListAjax(String collNo, Model model) {
		System.out.println("!!!!!!");
		List<DeptVO> list = deptService.getDeptList(collNo);
		
		
		return list;
	}
	
	
}
