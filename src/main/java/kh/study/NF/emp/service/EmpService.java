package kh.study.NF.emp.service;

import java.util.List;

import kh.study.NF.emp.vo.DeptManageVO;

//by수경 학생의 복학, 휴학, 전과, 복수전공신청에 대한 관리자 영역
public interface EmpService {
	
	//by 수경 학생의 복학, 휴학, 전과, 복수전공 신청 내역 목록조회
	List<DeptManageVO> applyList();

}
