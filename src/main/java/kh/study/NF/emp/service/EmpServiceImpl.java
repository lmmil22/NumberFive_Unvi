package kh.study.NF.emp.service;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.emp.vo.DeptManageVO;
@Service("empService")
public class EmpServiceImpl implements EmpService{

	//by수경 학생의 복학, 휴학, 전과, 복수전공신청에 대한 관리자 영역
	@Autowired
	private SqlSessionTemplate sqlSession;

	//by 수경 학생의 복학, 휴학, 전과, 복수전공 신청 내역 관리자가 목록조회
	@Override
	public List<DeptManageVO> applyList() {
		return sqlSession.selectList("deptManageMapper.applyList");
	}
	
}
