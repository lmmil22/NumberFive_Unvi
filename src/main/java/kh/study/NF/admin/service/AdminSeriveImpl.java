package kh.study.NF.admin.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.member.vo.MemberVO;
import kh.study.NF.student.vo.StudentVO;

@Service("adminService")
public class AdminSeriveImpl implements AdminService{

	@Autowired
	private SqlSessionTemplate sqlSession; 
	
	
	//회원 본인 정보 조회 
	@Override
	public StudentVO selectMemDetail(String memNo) {
		return sqlSession.selectOne("adminMapper.selectMemDetail", memNo);
	}

}
