package kh.study.NF.student.service;


import java.util.List;

import kh.study.NF.dept.vo.DeptVO;
import kh.study.NF.emp.vo.DeptManageVO;
import kh.study.NF.student.vo.StudentVO;

public interface StudentService {

	//by수경 휴학/복학/전과, 복수전공 신청 시 학생정보 나타내는 테이블 데이터
	StudentVO studentInfo();
	
	//by수경 복학신청 접수 
	void applyReturnUniv(DeptManageVO deptManageVO);
	
	//by수경 휴학신청 접수
	void applyTakeOffUniv(DeptManageVO deptManageVO);
	
	///by수경 전과신청 접수
	void applyChangeMajor(DeptManageVO deptManageVO);
	
	//by수경 복수전공신청 접수
	void applyAddMajor(DeptManageVO deptManageVO);
	
	//by 수경 학생의 복학, 휴학, 전과, 복수전공 신청 내역 목록조회
	//매개변수 String stuNo 추후에 넣을 것
	List<DeptManageVO> stuApplyList();
	
	//by수경 전공대학 전공학과 선택 시 재학중인 학과 제외
	//추후 매개변수 DeptVO deptVO
	List<DeptVO> DeptList(String collNo);
	

}
