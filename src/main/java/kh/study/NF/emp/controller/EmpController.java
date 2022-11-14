package kh.study.NF.emp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

import kh.study.NF.config.Student.AcceptApply;
import kh.study.NF.config.Student.ApplyCode;
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
	
	//by수경 휴학,복학 관리자 페이지
	@GetMapping("/takeOffReturnUniv")
	public String takeOffReturnUniv(Model model) {
		//휴학, 복학, 전과, 복전 신청 목록 조회
		List<DeptManageVO> applyList = empService.applyList();
		
		//by수경 휴학과 복학 신청 개수 구하기
		int takeOff =0; int comeback= 0;
		//휴학 목록을 저장할 리스트
		List<DeptManageVO> takeOffList = new ArrayList<>();
		//복학 목록을 저장할 리스트
		List<DeptManageVO> comebackList = new ArrayList<>();
		
		for(DeptManageVO applyInfo : applyList) {
			//휴학
			if(applyInfo.getApplyCode().equals(ApplyCode.takeOff.toString())) {
				//휴학 데이터 수  +  1
				takeOff++;
				//휴학 신청 목록에 데이터 추가
				takeOffList.add(applyInfo);
			}
			//복학
			else if(applyInfo.getApplyCode().equals(ApplyCode.comeback.toString())) {
				//복학 데이터 수  +  1
				comeback++;
				//복학 신청 목록에 데이터 추가
				comebackList.add(applyInfo);
			}
		}
		//html로 데이터 넘기기
		model.addAttribute("takeOffList", takeOffList);
		model.addAttribute("comebackList", comebackList);
		
		return "content/deptManage/takeOffReturnUniv";
	}
	
	//by수경 전과, 복수전공 관리자 페이지
	@GetMapping("/changeAddMajor")
	public String changeAddMajor(Model model) {
		//휴학, 복학, 전과, 복전 신청 목록 조회
		List<DeptManageVO> applyList = empService.applyList();
	
		//by수경 전과, 복수전공 신청 개수 구하기
		int doubleMajor=0; int changeMajor = 0;

		//전과 목록을 담을 리스트
		List<DeptManageVO> changeMajorList = new ArrayList<>();
		//복수전공 목록을 담을 리스트
		List<DeptManageVO> doubleMajorList = new ArrayList<>();
		
		for(DeptManageVO applyInfo :applyList) {
			//전과
			if(applyInfo.getApplyCode().equals(ApplyCode.changeMajor.toString())) {
				//전과 데이터 수 
				changeMajor++;
				//전과 신청 리스트에 담기
				changeMajorList.add(applyInfo);
			}
			//복수전공
			else if(applyInfo.getApplyCode().equals(ApplyCode.doubleMajor.toString())) {
				//복수전공 데이터 수 
				doubleMajor++;
				//복수전공 신청 리스트에 담기
				doubleMajorList.add(applyInfo);
			}
		}
		//html로 데이터 넘기기
		model.addAttribute("changeMajorList", changeMajorList);
		model.addAttribute("doubleMajorList", doubleMajorList);
		
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
		  
	  	  //현재 날짜 구하기
	      LocalDate date = LocalDate.now();
	    
	      int year = date.getYear();
	      int month = date.getMonthValue();
	      int day = date.getDayOfMonth(); 
	    
	      String nowDate = year +"년" + month + "월" + day +"일";
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
		  
		  //현재 날짜 구하기
		  LocalDate date = LocalDate.now();
		  
		  int year = date.getYear();
		  int month = date.getMonthValue();
		  int day = date.getDayOfMonth(); 
		  
		  String nowDate = year +"년" + month + "월" + day +"일";
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

		  //현재 날짜 구하기
		  LocalDate date = LocalDate.now();
		    
		  int year = date.getYear();
		  int month = date.getMonthValue();
		  int day = date.getDayOfMonth(); 
		    
		  String nowDate = year +"년" + month + "월" + day +"일";
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
		  
		  //현재 날짜 구하기
		  LocalDate date = LocalDate.now();
		    
		  int year = date.getYear();
		  int month = date.getMonthValue();
		  int day = date.getDayOfMonth(); 
		    
		  String nowDate = year +"년" + month + "월" + day +"일";
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
		  
		  //현재 날짜 구하기
		  LocalDate date = LocalDate.now();
		    
		  int year = date.getYear();
		  int month = date.getMonthValue();
		  int day = date.getDayOfMonth(); 
		    
		  String nowDate = year +"년" + month + "월" + day +"일";
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
		  
		  //현재 날짜 구하기
		  LocalDate date = LocalDate.now();
		  
		  int year = date.getYear();
		  int month = date.getMonthValue();
		  int day = date.getDayOfMonth(); 
		  
		  String nowDate = year +"년" + month + "월" + day +"일";
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
		//현재 날짜 구하기
		  LocalDate date = LocalDate.now();
		  
		  int year = date.getYear();
		  int month = date.getMonthValue();
		  int day = date.getDayOfMonth(); 
		  
		  String nowDate = year +"년" + month + "월" + day +"일";
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
		  
		  //현재 날짜 구하기
		  LocalDate date = LocalDate.now();
		  
		  int year = date.getYear();
		  int month = date.getMonthValue();
		  int day = date.getDayOfMonth(); 
		  
		  String nowDate = year +"년" + month + "월" + day +"일";
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
