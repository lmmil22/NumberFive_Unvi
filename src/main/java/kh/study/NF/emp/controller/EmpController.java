package kh.study.NF.emp.controller;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.config.Student.AcceptApply;
import kh.study.NF.config.Student.ApplyCode;
import kh.study.NF.config.Student.DeptManageCalendar;
import kh.study.NF.emp.service.EmpService;
import kh.study.NF.emp.vo.DeptManageVO;
import kh.study.NF.student.service.StudentService;
import kh.study.NF.student.vo.StudentVO;

//by수경 학생의 복학, 휴학, 전과, 복수전공신청에 대한 관리자 영역
@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Resource(name = "empService")
	private EmpService empService;
	
	@Resource(name = "studentService")
	private StudentService studentService;
	
	//by수경 휴학, 복학 신청 관리자 페이지
	@RequestMapping("/takeOffReturnUniv") 
	public String takeOffReturnUniv(Model model, @RequestParam Map<String, String> paramMap) {
		
		//휴학 신청 목록 조회
		paramMap.put("applyCode", ApplyCode.takeOff.toString());
		List<DeptManageVO> takeOffList = empService.applyList(paramMap);

		//복학 신청 목록 조회
		paramMap.put("applyCode", ApplyCode.comeback.toString());
		List<DeptManageVO> comebackList = empService.applyList(paramMap);

		//html로 데이터 넘기기
		model.addAttribute("takeOffList", takeOffList);
		model.addAttribute("comebackList", comebackList);
		
		//by수경 검색 조건에서 사용할 paramMap
		
		//휴학에서 사용할 현재 날짜
		String takeOffUniv_nowDate = DeptManageCalendar.nowDate();
		//복학에서 사용할 현재 날짜
		String comeback_nowDate = DeptManageCalendar.nowDate();
		
		//휴학에서 사용할 한달 전 날짜
		String takeOffUniv_aMonthAgo = DeptManageCalendar.aMonthAgo();
		//복학에서 사용할 한달 전 날짜
		String comeback_aMonthAgo = DeptManageCalendar.aMonthAgo();
		
		//휴학 검색창에서 넘어오는 fromDate가 없다면 한달 전 날짜로 set 하겠다
		if(paramMap.get("takeOffUniv_fromDate")==null) {
			paramMap.put("takeOffUniv_fromDate", takeOffUniv_aMonthAgo);
		}
		
		//복학 검색창에서 넘어오는 fromDate가 없다면 한달 전 날짜로 set 하겠다
		if(paramMap.get("comeback_fromDate")==null) {
			paramMap.put("comeback_fromDate", comeback_aMonthAgo);
		}
		
		//휴학 검색창에서 넘어오는 toDate가 없다면 현재 날짜로 set 하겠다
		if(paramMap.get("takeOffUniv_toDate")==null) {
			paramMap.put("takeOffUniv_toDate", takeOffUniv_nowDate);
		}
		//복학 검색창에서 넘어오는 toDate가 없다면 현재 날짜로 set 하겠다
		if(paramMap.get("comeback_toDate")==null) {
			paramMap.put("comeback_toDate", comeback_nowDate);
		}
		
		//paramMap에 담긴 데이터 html로 넘기기
		model.addAttribute("paramMap", paramMap);
		
		return "content/deptManage/takeOffReturnUniv";
	
	}
	
	//by수경 전과, 복수전공 관리자 페이지
	@RequestMapping("/changeAddMajor")
	public String changeAddMajor(Model model, @RequestParam Map<String, String> paramMap) {
		
//		System.out.println("메소드 시작과 동시에 받아오는 paramMap 데이터 출력");
//		for(String key : paramMap.keySet()) {
//			System.out.println("key = " + key + " / value = " + paramMap.get(key));
//		}
//		System.out.println("-------paramMap 데이터 출력 끝--------------------");
		
		
		//전과 신청 목록 조회
		paramMap.put("applyCode", ApplyCode.changeMajor.toString());
		List<DeptManageVO> changeMajorList = empService.applyList(paramMap);

		//복수전공 신청 목록 조회
		paramMap.put("applyCode", ApplyCode.doubleMajor.toString());
		List<DeptManageVO> doubleMajorList = empService.applyList(paramMap);
		
		//html로 데이터 넘기기
		model.addAttribute("changeMajorList", changeMajorList);
		model.addAttribute("doubleMajorList", doubleMajorList);
		
		//by수경 검색 조건에서 사용할 parameter값
		
		//전과에 사용하는 현재 날짜
		String changeMajor_nowDate = DeptManageCalendar.nowDate();
		//복수전공에 사용하는 현재 날짜
		String doubleMajor_nowDate = DeptManageCalendar.nowDate();
		
		//전과에 사용하는 한달 전 날짜
		String changeMajor_aMonthAgo = DeptManageCalendar.aMonthAgo();
		//복수전공에 사용하는 한달 전 날짜
		String doubleMajor_aMonthAgo = DeptManageCalendar.aMonthAgo();
		
		//전과 목록 검색에서 넘어오는 fromDate가 없다면 한달 전 날짜로 set 하겠다
		if(paramMap.get("changeMajor_fromDate")==null) {
			paramMap.put("changeMajor_fromDate", changeMajor_aMonthAgo);
		}
		
		//복수전공 목록 검색에서 넘어오는 fromDate가 없다면 한달 전 날짜로 set 하겠다
		if(paramMap.get("doubleMajor_fromDate")==null) {
			paramMap.put("doubleMajor_fromDate", doubleMajor_aMonthAgo);
		}
		
		
		//전과 목록 검색에서 넘어오는 toDate가 없다면 현재 날짜로 set 하겠다
		if(paramMap.get("changeMajor_toDate")==null) {
			paramMap.put("changeMajor_toDate", changeMajor_nowDate);
		}
		
		//복수전공 목록 검색에서 넘어오는 toDate가 없다면 현재 날짜로 set 하겠다
		if(paramMap.get("doubleMajor_toDate")==null) {
			paramMap.put("doubleMajor_toDate", doubleMajor_nowDate);
		}
		
		
//		System.out.println("메소드 마지막에 세팅된 paramMap 데이터 출력");
//		for(String key : paramMap.keySet()) {
//			System.out.println("key = " + key + " / value = " + paramMap.get(key));
//		}
//		System.out.println("-------paramMap 데이터 출력 끝--------------------");
		
		
		//paramMap에 담긴 데이터 html로 넘기기
		model.addAttribute("paramMap", paramMap);
		
		return "content/deptManage/changeAddMajor";
		
	}
	
	//by수경 학생이 복학, 휴학, 전과, 복수전공 해당 학기에 신청내역 있는지 확인
	@ResponseBody
	@PostMapping("/checkApplyAjax")
	public DeptManageVO checkApply(DeptManageVO deptManageVO) {
		
		return empService.checkApply(deptManageVO);
		
	}
	
	//by수경 학번 클릭 시 학생정보 확인ajax
	  @ResponseBody
	  @PostMapping("/stuInfoAjax") 
	  public StudentVO stuInfoAjax(String stuNo) {
	  
		  return studentService.studentInfo(stuNo);
	  }
	
	 //by수경 휴학신청 단일승인(라디오) 클릭 시 업데이트 ajax
	  @ResponseBody
	  @PostMapping("/changeTakeOffStatusAjax")
	  public void changeTakeOffStatus(DeptManageVO deptManageVO) {
	
	      //deptManageCalendar 메소드에서 현재 날짜 데이터 가져오기
		  String nowDate = DeptManageCalendar.nowDateToString();
		  
		  //승인날짜에 추가
		  deptManageVO.setApprovalDate(nowDate);
		  
		  //휴학 승인 쿼리 실행
		  empService.changeStatus(deptManageVO);
		  
		  //학생 상태 변경 쿼리 실행
		  studentService.takeOffStu(deptManageVO);
		 
	  }
	  
	  //by수경 복학 승인대기/승인완료(라디오) 클릭 시 업데이트 ajax(단일승인)
	  @ResponseBody
	  @PostMapping("/changeComebackStatusAjax")
	  public void changeComebackStatus(DeptManageVO deptManageVO) {
		  
		  //deptManageCalendar 메소드에서 현재 날짜 데이터 가져오기
		  String nowDate = DeptManageCalendar.nowDateToString();
		  
		  //승인날짜에 추가
		  deptManageVO.setApprovalDate(nowDate);
		  
		  //복학 승인 쿼리 실행
		  empService.changeStatus(deptManageVO);
		  //학생 상태 변경 쿼리 실행
		  studentService.returnStu(deptManageVO);
	  }
	  
	  //by수경 복학신청 일괄승인
	  @PostMapping("/comebackAllAccept")
	  public String comebackAllAccept(String applyNos, String stuNos
			  						, DeptManageVO deptManageVO, StudentVO studentVO) {
		  //applyNo를 ,로 구분하여 데이터를 가져왔음
		  String [] applyNosArr = applyNos.split(",");
		  //배열 데이터 하나하나를 담을 List 준비
		  List<String>applyNoList = Arrays.asList(applyNosArr);
		  //데이터 담기
		  deptManageVO.setApplyNoList(applyNoList);

		  //deptManageCalendar 메소드에서 현재 날짜 데이터 가져오기
		  String nowDate = DeptManageCalendar.nowDateToString();
		  
		  //현재 날짜 데이터 지정
		  deptManageVO.setApprovalDate(nowDate);
		  
		  //processStatus 값 세팅
		  deptManageVO.setProcessStatus(AcceptApply.accept.toString());
		  
		  //stuNo를 ,로 구분하여 데이터를 가져왔음
		  String [] stuNosArr = stuNos.split(",");
		  //배열 데이터 하나하나를 담을 List 준비
		  List<String>stuNoList = Arrays.asList(stuNosArr);
		  //데이터 담기
		  deptManageVO.setStuNoList(stuNoList);
		 
		  //일괄승인 쿼리 실행
		  empService.comebackTakeOffAllAccept(deptManageVO);
		  
		  //일괄승인 시 학생정보 변경 쿼리 실행
		  studentService.returnStus(deptManageVO);
		  
		  return"redirect:/emp/takeOffReturnUniv";
	  }
	  
	  //수경 휴학신청 일괄승인
	  @PostMapping("/takeOffAllAccept")
	  public String takeOffAllAccept(String applyNos, String stuNos
			  						, DeptManageVO deptManageVO, StudentVO studentVO) {
		//applyNo를 ,로 구분하여 데이터를 가져왔기에 제거
		  String [] applyNosArr = applyNos.split(",");
		  //배열 데이터 하나하나를 담을 List 준비
		  List<String> applyNoList = Arrays.asList(applyNosArr);
		  //데이터 담기
		  deptManageVO.setApplyNoList(applyNoList);
		  
		  //deptManageCalendar 메소드에서 현재 날짜 데이터 가져오기
		  String nowDate = DeptManageCalendar.nowDateToString();
		  
		  //현재 날짜 데이터 지정
		  deptManageVO.setApprovalDate(nowDate);
		  
		  //processStatus 값 세팅
		  deptManageVO.setProcessStatus(AcceptApply.accept.toString());
		  
		  //stuNo를 ,로 구분하여 데이터를 가져왔음
		  String [] stuNosArr = stuNos.split(",");
		  //배열 데이터 하나하나를 담을 List 준비
		  List<String>stuNoList = Arrays.asList(stuNosArr);
		  //데이터 담기
		  deptManageVO.setStuNoList(stuNoList);
		  
		  //일괄승인 쿼리 실행
		  empService.comebackTakeOffAllAccept(deptManageVO);
		 
		  //일괄승인 시 학생정보 변경 쿼리 실행
		  studentService.takeOffStus(deptManageVO);
		  
		  return"redirect:/emp/takeOffReturnUniv";
	  }
	  
	  //by수경 관리자에게 전과신청서 보여주기
	  @ResponseBody
	  @PostMapping("/showChangeMajorApplyAjax")
	  public DeptManageVO showChangeMajorApplyAjax(DeptManageVO deptManageVO) {
		  
		  return empService.showChangeMajor(deptManageVO);
	  }
	  
	  //by수경 관리자에게 복수전공 신청서 보여주기
	  @ResponseBody
	  @PostMapping("/showDoubleMajorAjax")
	  public DeptManageVO showDoubleMajorAjax(DeptManageVO deptManageVO) {
		  
		  return empService.showDoubleMajor(deptManageVO);
	  }
	  
	  //수경 전과신청 일괄승인
	  @PostMapping("/changeAllAccept")
	  public String changeAllAccept(String applyNos, String stuNos
			  						, DeptManageVO deptManageVO, StudentVO studentVO) {
		//applyNo를 ,로 구분하여 데이터를 가져왔기에 제거
		  String [] applyNosArr = applyNos.split(",");
		  //배열 데이터 하나하나를 담을 List 준비
		  List<String> applyNoList = Arrays.asList(applyNosArr);
		  //데이터 담기
		  deptManageVO.setApplyNoList(applyNoList);
		  
		  //deptManageCalendar 메소드에서 현재 날짜 데이터 가져오기
		  String nowDate = DeptManageCalendar.nowDateToString();
		  
		  //현재 날짜 데이터 지정
		  deptManageVO.setApprovalDate(nowDate);
		  
		  //processStatus 값 세팅
		  deptManageVO.setProcessStatus(AcceptApply.accept.toString());
		  
		  //stuNo를 ,로 구분하여 데이터를 가져왔음
		  String [] stuNosArr = stuNos.split(",");
		  //배열 데이터 하나하나를 담을 List 준비
		  List<String>stuNoList = Arrays.asList(stuNosArr);
		  //데이터 담기
		  deptManageVO.setStuNoList(stuNoList);
		  
		  //일괄승인 쿼리 실행
		  empService.changeDoubleMajorAllAccept(deptManageVO);
		 
		  //일괄승인 시 학생정보 변경 쿼리 실행
		  studentService.changeMajorStus(deptManageVO);
		  
		  return"redirect:/emp/changeAddMajor";
	  }
	  //수경 복수전공신청 일괄승인
	  @PostMapping("/doubleAllAccept")
	  public String doubleAllAccept(String applyNos, String stuNos
			  , DeptManageVO deptManageVO, StudentVO studentVO) {
		  //applyNo를 ,로 구분하여 데이터를 가져왔기에 제거
		  String [] applyNosArr = applyNos.split(",");
		  //배열 데이터 하나하나를 담을 List 준비
		  List<String> applyNoList = Arrays.asList(applyNosArr);
		  //데이터 담기
		  deptManageVO.setApplyNoList(applyNoList);
		  
		  //deptManageCalendar 메소드에서 현재 날짜 데이터 가져오기
		  String nowDate = DeptManageCalendar.nowDateToString();
		  
		  //현재 날짜 데이터 지정
		  deptManageVO.setApprovalDate(nowDate);
		  
		  //processStatus 값 세팅
		  deptManageVO.setProcessStatus(AcceptApply.accept.toString());
		  
		  //stuNo를 ,로 구분하여 데이터를 가져왔음
		  String [] stuNosArr = stuNos.split(",");
		  //배열 데이터 하나하나를 담을 List 준비
		  List<String>stuNoList = Arrays.asList(stuNosArr);
		  //데이터 담기
		  deptManageVO.setStuNoList(stuNoList);
		  
		  //일괄승인 쿼리 실행
		  empService.changeDoubleMajorAllAccept(deptManageVO);
		  
		  //일괄승인 시 복수전공 테이블에 테이터 삽입 쿼리 실행
		  studentService.insertDoubleMajors(deptManageVO);
		  
		  //일괄승인 시 학생테이블에 복수전공 코드 데이터 넣기
		  studentService.updateDoubleMajors(deptManageVO);
		  
		  return"redirect:/emp/changeAddMajor";
	  }
	  
	  //by수경 전과신청 관리자 단일승인
	  @ResponseBody
	  @PostMapping("/acceptChangeMajorAjax")
	  public void acceptChangeMajorAjax(DeptManageVO deptManageVO, String applyNo, String stuNo) {
		  
		  deptManageVO.setApplyNo(applyNo);
		  deptManageVO.setStuNo(stuNo);
		  
		  //deptManageCalendar 메소드에서 현재 날짜 데이터 가져오기
		  String nowDate = DeptManageCalendar.nowDateToString();
		  
		  //현재 날짜 데이터 지정
		  deptManageVO.setApprovalDate(nowDate);
		  
		  //processStatus 값 세팅
		  deptManageVO.setProcessStatus(AcceptApply.accept.toString());
		  //전과 단일승인 쿼리 실행
		  empService.acceptChangeDoubleMajor(deptManageVO);
		  //학생정보 변경 쿼리 실행
		  studentService.changeMajorStu(deptManageVO);
	  }
	  
	  //by수경 복수전공신청 관리자 단일승인
	  @ResponseBody
	  @PostMapping("/acceptDoubleMajorAjax")
	  public void acceptDoubleMajorAjax(DeptManageVO deptManageVO, String applyNo, String stuNo) {
		  
		  deptManageVO.setApplyNo(applyNo);
		  deptManageVO.setStuNo(stuNo);
		  
		  //deptManageCalendar 메소드에서 현재 날짜 데이터 가져오기
		  String nowDate = DeptManageCalendar.nowDateToString();
		  
		  //현재 날짜 데이터 지정
		  deptManageVO.setApprovalDate(nowDate);
		  
		  //processStatus 값 세팅
		  deptManageVO.setProcessStatus(AcceptApply.accept.toString());
		  
		  //전과 단일승인 쿼리 실행
		  empService.acceptChangeDoubleMajor(deptManageVO);
		  // 복수전공 테이블에 데이터 넣기
		  studentService.insertDoubleMajor(deptManageVO);
		  //학생 테이블에 doubleNo데이터 넣기
		  studentService.updateDoubleMajor(deptManageVO.getStuNo());
	  }
}
