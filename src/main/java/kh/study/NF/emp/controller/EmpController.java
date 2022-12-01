package kh.study.NF.emp.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.NF.config.Student.AcceptApply;
import kh.study.NF.config.Student.ApplyCode;
import kh.study.NF.config.Student.DeptManageCalendar;
import kh.study.NF.config.Student.Probation;
import kh.study.NF.dept.service.DeptService;
import kh.study.NF.dept.vo.DeptVO;
import kh.study.NF.emp.service.EmpService;
import kh.study.NF.emp.vo.AcademicProbationVO;
import kh.study.NF.emp.vo.ChartVO;
import kh.study.NF.emp.vo.DeptManageVO;
import kh.study.NF.emp.vo.ProbationChartVO;
import kh.study.NF.emp.vo.StatusInfoVO;
import kh.study.NF.emp.vo.StuOutChartVO;
import kh.study.NF.emp.vo.StuOutVO;
import kh.study.NF.emp.vo.StuinfoAndProbationListVO;
import kh.study.NF.member.vo.MemberVO;
import kh.study.NF.student.service.StudentService;
import kh.study.NF.student.vo.StudentVO;

//by수경 학생의 복학, 휴학, 전과, 복수전공신청, 학사경고, 제적에 대한 관리자 영역
@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Resource(name = "empService")
	private EmpService empService;
	
	@Resource(name = "studentService")
	private StudentService studentService;
	
	@Resource(name = "deptService")
	private DeptService deptService;
	
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
	  
	  //by수경 복학신청 일괄승인 Ajax
	  @ResponseBody
	  @PostMapping("/comebackAllAccept")
	  public void comebackAllAccept(String applyNos, String stuNos
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
		 
		  //일괄승인 쿼리 실행(deptManage 상태 accept로 변경)
		  empService.comebackTakeOffAllAccept(deptManageVO);
		  
		  //일괄승인 시 학생정보 변경 쿼리 실행 (휴학-> 재학으로)
		  for(int i = 0 ; i < stuNoList.size() ; i++) {
			 // System.out.println("for문 - " + (i + 1));
			 // System.out.println("stuNo = " + stuNoList.get(i));
			 //System.out.println("applyNo = " + applyNoList.get(i));
			  DeptManageVO vo = new DeptManageVO();
			  vo.setStuNo(stuNoList.get(i));
			  vo.setApplyNo(applyNoList.get(i));
			  
			  //일괄승인 시 학생정보 변경 쿼리 실행(학생 수 만큼 실행되어 하기 때문에)
			  studentService.returnStu(vo);
		  }
	  }
	  
	  //수경 휴학신청 일괄승인 ajax
	  @ResponseBody
	  @PostMapping("/takeOffAllAccept")
	  public void takeOffAllAccept(String applyNos, String stuNos
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
		  List<String> stuNoList = Arrays.asList(stuNosArr);
		  //데이터 담기
		  deptManageVO.setStuNoList(stuNoList);
		  
		  //일괄승인 쿼리 실행 (deptManage 처리상태 accept로 변경)
		  empService.comebackTakeOffAllAccept(deptManageVO);
		 
		  
		  //System.out.println("stuNo 개수 : " + stuNoList.size());
		  //System.out.println("applyNo 개수 : " + applyNoList.size());
		  
		  //일괄승인 시 학생정보 변경 쿼리 실행 (재학-> 휴학으로)
		  for(int i = 0 ; i < stuNoList.size() ; i++) {
			  System.out.println("for문 - " + (i + 1));
			  System.out.println("stuNo = " + stuNoList.get(i));
			  System.out.println("applyNo = " + applyNoList.get(i));
			  DeptManageVO vo = new DeptManageVO();
			  vo.setStuNo(stuNoList.get(i));
			  vo.setApplyNo(applyNoList.get(i));
			  
			  //update가 신청 학생 수 만큼 적용되어야 하기 때문에 for문 안에서 실행
			  studentService.takeOffStu(vo);
		  }
		  
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
	  
	  //수경 전과신청 일괄승인(ajax)
	  @ResponseBody
	  @PostMapping("/changeAllAccept")
	  public void changeAllAccept(String applyNos, String stuNos
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
		  
		  //일괄승인 쿼리 실행 (deptManage accept로 상태 변경)
		  empService.changeDoubleMajorAllAccept(deptManageVO);
		 
		  //일괄승인 시 학생정보 변경 쿼리 실행  (전공대학, 전공학과 변경)
		  for(int i = 0 ; i < stuNoList.size() ; i++) {
			  //System.out.println("for문 - " + (i + 1));
			  //System.out.println("stuNo = " + stuNoList.get(i));
			  //System.out.println("applyNo = " + applyNoList.get(i));
			  DeptManageVO vo = new DeptManageVO();
			  vo.setStuNo(stuNoList.get(i));
			  vo.setApplyNo(applyNoList.get(i));
			  
			  //일괄승인 시 학생정보 변경 쿼리 실행 (전공대학, 전공학과 변경)
			  studentService.changeMajorStu(vo);
		  }
		  
	  }
	  //수경 복수전공신청 일괄승인(ajax)
	  @ResponseBody
	  @PostMapping("/doubleAllAccept")
	  public void doubleAllAccept(String applyNos, String stuNos
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
		  
		  //일괄승인 쿼리 실행 deptManage accept로 변경
		  empService.changeDoubleMajorAllAccept(deptManageVO);
		  
		  //일괄승인 시 학생정보 변경 쿼리 실행 
		  for(int i = 0 ; i < stuNoList.size() ; i++) {
			  //System.out.println("for문 - " + (i + 1));
			  //System.out.println("stuNo = " + stuNoList.get(i));
			  //System.out.println("applyNo = " + applyNoList.get(i));
			  DeptManageVO vo = new DeptManageVO();
			  vo.setStuNo(stuNoList.get(i));
			  vo.setApplyNo(applyNoList.get(i));
			  
			  //일괄승인 시 복수전공 테이블에 테이터 삽입 쿼리 실행
			  studentService.insertDoubleMajor(vo);
			  
			  //일괄승인 시 학생테이블에 복수전공 코드 데이터 넣기
			  studentService.updateDoubleMajor(vo.getStuNo());
		  }
	
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
	  
	  //by수경 학사경고 페이지로 이동
	  @RequestMapping("/probation")
	  public String academicProbation(Model model, @RequestParam Map<String, String> paramMap) {
		  		  
		  //소속학과, 소속대학 select box 데이터 
		  model.addAttribute("collList", deptService.selectCollList());
		  model.addAttribute("deptList", deptService.selectDeptList());
		  
		  //전체 학생 목록
		  model.addAttribute("stuList", empService.selectAllStu(paramMap));
		  
		  //검색 조건 paramMap 넘기기
		  model.addAttribute("paramMap", paramMap);

		  
		  return "content/statusInfo/AcademicProbation";
	  }
	  
	  //by수경 학사경고 페이지 전공대학 클릭 시 전공학과 변경되도록 ajax
	  @ResponseBody
	  @PostMapping("/deptListAjax")
	  public List<DeptVO> deptListAjax(@RequestParam Map<String, String> paramMap){
		  
		  return empService.getDeptList(paramMap);
	  }
	  
	  //by수경 학사경고 학생 신상정보 데이터 뽑기 ajax
	  @ResponseBody
	  @PostMapping("/probationStuInfoAjax")
	  public StuinfoAndProbationListVO probationStuInfoAjax(String stuNo) {
		  StudentVO studentVO = empService.probationStuInfo(stuNo);
		  List<AcademicProbationVO> probationList = empService.probationReason(stuNo);
		  //두가지 데이터를 담을 VO를 새로 만들어서 두개의 데이터를 가져간다.
		  StuinfoAndProbationListVO vo = new StuinfoAndProbationListVO();
		  vo.setStudentVO(studentVO);
		  vo.setProbationList(probationList);
		  
		  return vo; 
	  }
	  //by수경 제적처리 학생 신상정보 데이터 뽑기 ajax
	  @ResponseBody
	  @PostMapping("/stuOutStuInfoAjax")
	  public StuinfoAndProbationListVO stuOutStuInfoAjax(String stuNo) {
		  StudentVO studentVO = empService.probationStuInfo(stuNo);
		  List<AcademicProbationVO> probationList = empService.probationReason(stuNo);
		  //두가지 데이터를 담을 VO를 새로 만들어서 두개의 데이터를 가져간다.
		  StuinfoAndProbationListVO vo = new StuinfoAndProbationListVO();
		  vo.setStudentVO(studentVO);
		  vo.setProbationList(probationList);
		  
		  return vo; 
	  }
	  
	  //by수경 제적페이지로 이동
	  @RequestMapping("/stuOut")
	  public String stuOut(Model model, @RequestParam Map<String, String> paramMap) {
		  //제적학생 목록 불러오기
		  model.addAttribute("stuOutList", empService.selectStuOutList(paramMap));
		  
		  //소속학과, 소속대학 select box 데이터 
		  model.addAttribute("collList", deptService.selectCollList());
		  model.addAttribute("deptList", deptService.selectDeptList());
		  
		  //검색 조건 paramMap 넘기기
		  model.addAttribute("paramMap", paramMap);
		  
		  return "content/statusInfo/stuOut";
	  }
	  
	  //by수경 학사경고 모달창에서 승인하기 ajax
	  @ResponseBody
	  @PostMapping("/acceptProbationAjax")
		public void acceptProbationAjax(AcademicProbationVO probationVO) {
		  
		  //학사경고 테이블에 데이터 insert
		  empService.insertProbation(probationVO);
	  }
	  
	  //by수경 제적처리 ajax
	  @ResponseBody
	  @PostMapping("/acceptStuOutAjax")
	  public void acceptStuOutAjax(StuOutVO stuOutVO, StatusInfoVO statusInfoVO) {
		  //제적 테이블에 데이터 insert
		  empService.insertStuOut(stuOutVO);
		  
		  //statusInfo 테이블에 데이터 insert 
		  //deptManageCalendar 메소드에서 현재 날짜 데이터 가져오기
		  String nowDate = DeptManageCalendar.nowDateToString();
		  statusInfoVO.setApprovalDate(nowDate);
		  statusInfoVO.setNowStatus(Probation.PROBATION.toString());
		  statusInfoVO.setAfterStatus(Probation.STU_OUT.toString());
		  statusInfoVO.setIngStatus(AcceptApply.accept.toString());
		  statusInfoVO.setStuNo(stuOutVO.getStuNo());
		  empService.insertStatusInfo(statusInfoVO);
		  
		  //stu 테이블에 stuStatus 상태 update
		  empService.changeStuStatus(stuOutVO.getStuNo());
		  
	  }
	  
	  //by수경 관리자 학적승인 실적(KPI) 차트와 학사경고 및 제적 차트 보여주기 위한 페이지
	  @GetMapping("/showKPI")
	  public String showKPIChart() {
		  return "content/deptManage/showKPIChart";
	  }
	  
	  //by수경 학적 신청과 승인 KPI 차트 데이터 추가를 위한 Ajax
	  @ResponseBody
	  @PostMapping("/showKPIAjax")
	  public Map<String, Object> showKPIAjax(){
		  //차트 그릴 데이터 조회하여 가져오기
		  List<ChartVO> applyChartData = empService.showApplyChart();
		  List<ChartVO> approvalChartData = empService.showApprovalChart();
		  
		  //Map 데이터 담을 통 만들기
		  Map<String, Object> KPIchartDataMap = new HashMap<>();
		  
		  //쿼리 데이터를 필요한 형태로 가공해서 담을 통 만들기 (applyDate 기준으로 데이터 담을 통)
		  List<Integer> applyNoList1 = new ArrayList<>();
		  List<Integer> approvalDateNoList1 = new ArrayList<>();
		  List<String> applyDateList = new ArrayList<>();
		  
		  //approvalDate 기준으로 데이터 담을 통
		  List<Integer> applyNoList2 = new ArrayList<>();
		  List<Integer> approvalDateNoList2 = new ArrayList<>();
		  List<String> approvalDateList = new ArrayList<>();
		  
		  //쿼리에서 넘어온 데이터를 List에 담아준다.
		  for(ChartVO e : applyChartData) {
			  applyNoList1.add(e.getApplyNoCnt());
			  approvalDateNoList1.add(e.getApprovalDateCnt());
			  applyDateList.add(e.getDay());
		  }
		  
		  //쿼리에서 넘어온 데이터를 List에 담아준다.
		  for(ChartVO e : approvalChartData) {
			  applyNoList2.add(e.getApplyNoCnt());
			  approvalDateNoList2.add(e.getApprovalDateCnt());
			  approvalDateList.add(e.getDay());
		  }
		  
		  //List에 담은 데이터를 map에 저장한다
		  KPIchartDataMap.put("applyNoList1", applyNoList1);
		  KPIchartDataMap.put("approvalDateNoList1", approvalDateNoList1);
		  KPIchartDataMap.put("applyDateList", applyDateList);
		  
		  KPIchartDataMap.put("applyNoList2", applyNoList2);
		  KPIchartDataMap.put("approvalDateNoList2", approvalDateNoList2);
		  KPIchartDataMap.put("approvalDateList", approvalDateList);
		  
		  //ajax return에 map데이터 보내기
		  return KPIchartDataMap;
	  }
	  
	  //by수경 학사경고와 제적 차트를 위한 데이터Ajax
	  @ResponseBody
	  @PostMapping("/showProbationStuOutChartAjax")
	  public Map<String,Object> showProbationStuOutChartAjax(){
		  
		  //차트에 넣을 데이터 조회하여 가져오기
		  List<ProbationChartVO> probationData = empService.showProbationChart();
		  List<StuOutChartVO> stuOutData = empService.showStuOutChart();
		  
		  //Map 데이터 담을 통 만들기
		  Map<String,Object> probationStuOutChartMap = new HashMap<>();
		  
		  //쿼리 데이터를 필요한 형태로 가공해서 담을 통 만들기
		  //학사경고 데이터
		  List<Integer> probNoList = new ArrayList<>();
		  List<Integer> probDateList = new ArrayList<>();
		  List<String> dateList1 = new ArrayList<>();
		  //제적 데이터
		  List<Integer> stuOutNoList = new ArrayList<>();
		  List<Integer> stuOutDateList = new ArrayList<>();
		  List<String> dateList2 = new ArrayList<>();
		  
		  //학사경고 데이터를 List에 담아준다.
		  for(ProbationChartVO e : probationData) {
			  probNoList.add(e.getProbNoCnt());
			  probDateList.add(e.getProbDateCnt());
			  dateList1.add(e.getDay());
		  }
		  
		  //제적 데이터를 List에 담아준다.
		  for(StuOutChartVO e : stuOutData) {
			  stuOutNoList.add(e.getStuOutCnt());
			  stuOutDateList.add(e.getStuOutDateCnt());
			  dateList2.add(e.getDay());
		  }
		  
		  //List에 담은 데이터를 map에 저장한다
		  probationStuOutChartMap.put("probNoList", probNoList);
		  probationStuOutChartMap.put("probDateList", probDateList);
		  probationStuOutChartMap.put("dateList1", dateList1);
		  probationStuOutChartMap.put("stuOutNoList", stuOutNoList);
		  probationStuOutChartMap.put("stuOutDateList", stuOutDateList);
		  probationStuOutChartMap.put("dateList2", dateList2);
		  
		  //ajax return에 map데이터 보내기
		  return probationStuOutChartMap;
	  }
}
