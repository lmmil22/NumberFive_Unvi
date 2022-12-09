package kh.study.NF.student.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.config.Student.AcceptApply;
import kh.study.NF.config.Student.ApplyCode;
import kh.study.NF.dept.service.DeptService;
import kh.study.NF.dept.vo.DeptVO;
import kh.study.NF.emp.vo.DeptManageVO;
import kh.study.NF.student.service.StudentService;


@Controller
@RequestMapping("/stu")
public class StudentController {

	@Resource(name = "studentService")
	private StudentService studentService;
	
	@Resource(name = "deptService")
	private DeptService deptService;
	
	//by수경 학생이 전공학과를 변경(전과)하는 페이지로 이동 메소드
	@GetMapping("/changeMajor")
	public String changeMajor(Model model, Authentication authentication) {
		//security를 사용하여 로그인한 정보 가져오는 방법
		User user = (User) authentication.getPrincipal();
		//memNO와 stuNO 동일하기 때문에 학번을 stuNo로 담아 쿼리 매개변수로 넣어 준다
		String stuNo = user.getUsername();
		
		//by수경 학생정보 쿼리문
		model.addAttribute("stuInfo", studentService.studentInfo(stuNo));
		
		//by수경 변경할 전공대학, 전공학과 선택 쿼리문
		 model.addAttribute("deptList",deptService.selectDeptList());
		 model.addAttribute("collList",deptService.selectCollList());
		
		return "content/student/changeMajor";
	}
	
	//by수경 학생이 복수전공 신청하는 페이지로 이동 메소드
	@GetMapping("/addMajor")
	public String addMajor(Model model, Authentication authentication) {
		
		//security를 사용하여 로그인한 정보 가져오는 방법
		User user = (User) authentication.getPrincipal();
		//memNO와 stuNO 동일하기 때문에 학번을 stuNo로 담아 쿼리 매개변수로 넣어 준다
		String stuNo = user.getUsername();
		
		//by수경 학생정보 쿼리문
		model.addAttribute("stuInfo", studentService.studentInfo(stuNo));
		
		//by수경 변경할 전공대학, 전공학과 선택 쿼리문
		model.addAttribute("deptList",deptService.selectDeptList());
		model.addAttribute("collList",deptService.selectCollList());
		
		return "content/student/addMajor";
	}
	
	//by수경 변경할 전공대학 선택 시, 전공학과 선택 ajax (본인 현재 소속학과 제외)
	//추후 매개변수 DeptVO deptVO(collNo, stuNo)
	@ResponseBody
	@PostMapping("/getMajorAjax")
	public List<DeptVO> getMajorAjax(DeptVO deptVO) {
		
		return studentService.DeptList(deptVO);
	}
	
	//by수경 학생이 학교를 휴학신청하는 페이지로 이동
	@GetMapping("/takeOffUniv")
	public String takeOffUniv(Model model, Authentication authentication) {
		
		//security를 사용하여 로그인한 정보 가져오는 방법
		User user = (User) authentication.getPrincipal();
		//memNO와 stuNO 동일하기 때문에 학번을 stuNo로 담아 쿼리 매개변수로 넣어 준다
		String stuNo = user.getUsername();
		
		//by수경 학생정보 쿼리문
		model.addAttribute("stuInfo", studentService.studentInfo(stuNo));
		
		return  "content/student/takeOffUniv";
	}
	
	//by수경 학생이 학교를 복학신청하는 페이지로 이동
	@GetMapping("/returnUniv")
	public String returnUniv(Model model, Authentication authentication) {
		
		//security를 사용하여 로그인한 정보 가져오는 방법
		User user = (User) authentication.getPrincipal();
		//memNO와 stuNO 동일하기 때문에 학번을 stuNo로 담아 쿼리 매개변수로 넣어 준다
		String stuNo = user.getUsername();
		
		//by수경 학생정보 쿼리문
		model.addAttribute("stuInfo", studentService.studentInfo(stuNo));
		
		return  "content/student/returnUniv";
	}
	
	//by수경 학생이 복학 신청버튼 클릭 시 실행되는 메소드
	@ResponseBody
	@PostMapping("/applyReturnUnivAjax")
	public void applyReturnUniv(Model model, DeptManageVO deptManageVO) {
		//by수경 enum을 이용하여 value set
		deptManageVO.setApplyCode(ApplyCode.comeback.toString());
		deptManageVO.setProcessStatus(AcceptApply.waiting.toString());
		studentService.applyReturnUniv(deptManageVO);
	
	}
	
	
	//by수경 학생이 휴학신청버튼 클릭 시 실행되는 메소드
	@ResponseBody
	@PostMapping("/applyTakeOffUnivAjax")
	public void applyTakeOffUniv(Model model, DeptManageVO deptManageVO) {
		//by수경 enum을 이용하여 value set
		deptManageVO.setApplyCode(ApplyCode.takeOff.toString());
		deptManageVO.setProcessStatus(AcceptApply.waiting.toString());
		studentService.applyTakeOffUniv(deptManageVO);
		
	}
	
	
	//by수경 학생이 전과 신청버튼 클릭 시 실행되는 메소드
	@ResponseBody
	@PostMapping("/applyChangeMajorAjax")
	public void applyChangeMajor(Model model, DeptManageVO deptManageVO) {
		//by수경 enum을 이용하여 value set
		deptManageVO.setApplyCode(ApplyCode.changeMajor.toString());
		deptManageVO.setProcessStatus(AcceptApply.waiting.toString());
		studentService.applyChangeMajor(deptManageVO);
		

	}
	//by수경 학생이 복수전공 신청버튼 클릭 시 실행되는 메소드
	@ResponseBody
	@PostMapping("/applyAddMajorAjax")
	public void applyAddMajor(Model model, DeptManageVO deptManageVO) {
		//by수경 enum을 이용하여 value set
		deptManageVO.setApplyCode(ApplyCode.doubleMajor.toString());
		deptManageVO.setProcessStatus(AcceptApply.waiting.toString());
		studentService.applyAddMajor(deptManageVO);
		
		
	}
	
	//by수경 학생의 전공학과 변경(전과), 휴학신청, 복학신청, 복수전공 신청 현황
	@GetMapping("/stuApplyList")
	public String stuApplyList(Model model, Authentication authentication) {
		
		//security를 사용하여 로그인한 정보 가져오는 방법
		User user = (User) authentication.getPrincipal();
		//memNO와 stuNO 동일하기 때문에 학번을 stuNo로 담아 쿼리 매개변수로 넣어 준다
		String stuNo = user.getUsername();
		
		//신청 내역 목록
		List<DeptManageVO> applyList =  studentService.stuApplyList(stuNo);

		//by수경 신청 분류별 개수 구하기
		int changeMajor = 0, takeOff = 0, comeback = 0, doubleMajor = 0;
		
		//전과 목록 담을 리스트
		List<DeptManageVO> changeMajorList = new ArrayList<>();
		//휴학 목록 담을 리스트
		List<DeptManageVO> takeOffList = new ArrayList<>();
		//복학 목록을 담을 리스트
		List<DeptManageVO> comebackList = new ArrayList<>();
		//복수전공 목록을 담을 리스트
		List<DeptManageVO> doubleMajorList = new ArrayList<>();
		
		for(DeptManageVO applyInfo : applyList) {
			//전과
			if(applyInfo.getApplyCode().equals(ApplyCode.changeMajor.toString()) ) {
				//전과 데이터 수 
				changeMajor++;
				//전과 목록 데이터 담기
				changeMajorList.add(applyInfo);
			}
			//휴학
			else if(applyInfo.getApplyCode().equals(ApplyCode.takeOff.toString()) ) {
				//휴학 데이터 수 
				takeOff++;		
				//휴학 목록 데이터 담기
				takeOffList.add(applyInfo);
			}
			//복학
			else if(applyInfo.getApplyCode().equals(ApplyCode.comeback.toString()) ) {
				//복학 데이터 수
				comeback++;
				//복학 목록 데이터 담기
				comebackList.add(applyInfo);
			}
			//복수전공
			else if(applyInfo.getApplyCode().equals(ApplyCode.doubleMajor.toString()) ) {
				//복수전공 데이터 수 
				doubleMajor++;
				//복수전공 목록 데이터 담기
				doubleMajorList.add(applyInfo);
			}
		}
		//html로 데이터 보내기
		model.addAttribute("changeMajorList", changeMajorList);
		model.addAttribute("takeOffList", takeOffList);
		model.addAttribute("comebackList", comebackList);
		model.addAttribute("doubleMajorList", doubleMajorList);
		
		return  "content/student/stuApplyList";
	}
	
	//by수경 학생이 학적신청(휴학, 복학, 전과, 복수전공) 관리자 승인 전에 신청 철회
	@ResponseBody
	@PostMapping("/deleteApplyAjax")
	public void deleteApplyAjax(Authentication authentication, DeptManageVO deptManageVO, String applyCode) {
		//security를 사용하여 로그인한 정보 가져오는 방법
		User user = (User) authentication.getPrincipal();
		//memNO와 stuNO 동일하기 때문에 학번을 stuNo로 담아 쿼리 매개변수로 넣어 준다
		String stuNo = user.getUsername();
		
		//학번 추가
		deptManageVO.setStuNo(stuNo);
		//넘어오는 신청코드 담기
		deptManageVO.setApplyCode(applyCode);
		//쿼리 실행
		studentService.deleteApply(deptManageVO);
	}
	
}
