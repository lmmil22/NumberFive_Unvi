package kh.study.NF.admin.service;

import kh.study.NF.member.vo.MemberVO;
import kh.study.NF.student.vo.StudentVO;

public interface AdminService{
//by 지아
	//회원 정보 조회 
	StudentVO selectMemDetail(String memNo);
	
	//회원 정보 수정
	void updateMemDetail(MemberVO memberVO);
}
