package kh.study.NF.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.config.student.ApplyCode;
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
		
		List<DeptManageVO> applyList = empService.applyList();
		model.addAttribute("applyList", applyList);
	
		//by수경 휴학과 복학 신청 개수 구하기
		int takeOff =0; int comeback= 0;
		for(DeptManageVO applyInfo : applyList) {
			//휴학
			if(applyInfo.getApplyCode().equals(ApplyCode.takeOff.toString())) {
				takeOff++;
			}
			//복학
			else if(applyInfo.getApplyCode().equals(ApplyCode.comeback.toString())) {
				comeback++;
			}
		}
		
		//map에 데이터 담아서 보내기
		Map<String, Integer> applyCodeMap = new HashMap<>();
		applyCodeMap.put("takeOff", takeOff);
		applyCodeMap.put("comeback", comeback);
		
		model.addAttribute("applyCodeMap", applyCodeMap);
		
		return "content/deptManage/takeOffReturnUniv";
	}
	
	//by수경 전과, 복수전공 관리자 페이지
	@GetMapping("/changeAddMajor")
	public String changeAddMajor(Model model) {
		
		List<DeptManageVO> applyList = empService.applyList();
		model.addAttribute("applyList", applyList);
		
		//by수경 전과, 복수전공 신청 개수 구하기
		int addMajor=0; int changeMajor = 0;
		for(DeptManageVO applyInfo :applyList) {
			if(applyInfo.getApplyCode().equals(ApplyCode.changeMajor.toString())) {
				changeMajor++;
			}
			else if(applyInfo.getApplyCode().equals(ApplyCode.doubleMajor.toString())) {
				addMajor++;
			}
			
		}
		
		//map에 데이터를 담아서 html로 보내기
		Map<String, Integer> applyCodeMap = new HashMap<>();
		applyCodeMap.put("changeMajor", changeMajor);
		applyCodeMap.put("doubleMajor", addMajor);
		
		model.addAttribute("applyCodeMap", applyCodeMap);
		
		return "content/deptManage/changeAddMajor";
		
	}
	
	//by수경 학생이 복학, 휴학, 전과, 복수전공 해당 학기에 신청내역 있는지 확인
	@ResponseBody
	@PostMapping("/checkApplyAjax")
	public DeptManageVO checkApply(DeptManageVO deptManageVO) {
		
		return empService.checkApply(deptManageVO);
		
	}
	
}
