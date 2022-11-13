package kh.study.NF.emp.service;

import java.util.List;

import kh.study.NF.emp.vo.DeptManageVO;

//by수경 학생의 복학, 휴학, 전과, 복수전공신청에 대한 관리자 영역
public interface EmpService {
	
	//by 수경 관리자가 학생의 복학, 휴학, 전과, 복수전공 신청 목록조회
	List<DeptManageVO> applyList();

	//by수경 학생이 복학, 휴학, 전과, 복수전공 해당 학기에 신청내역 있는지 확인
	DeptManageVO checkApply(DeptManageVO deptManageVO);
	
	//by수경 승인대기/승인완료 단일 승인(라디오 값)
	void changeStatus(DeptManageVO deptManageVO);
	
	//by수경 복학/휴학신청 일괄승인
	void comebackTakeOffAllAccept(DeptManageVO deptManageVO);
	
	//by수경 관리자에게 전과신청서 보여주기
	DeptManageVO showChangeMajor(String stuNo);
	
	//by수경 관리자에게 복수전공 신청서 보여주기
	DeptManageVO showDoubleMajor(String stuNo);
}
