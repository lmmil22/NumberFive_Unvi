package kh.study.NF.admin.service;

import kh.study.NF.student.vo.StudentVO;

public interface AdminService{

	//회원 정보 조회 
	StudentVO selectMemDetail(String memNo);
	
}
