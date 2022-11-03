package kh.study.NF.emp.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class EmpServiceImpl implements EmpService{

	//by수경 학생의 복학, 휴학, 전과, 복수전공신청에 대한 관리자 영역
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
}
