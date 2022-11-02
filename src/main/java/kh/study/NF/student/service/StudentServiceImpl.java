package kh.study.NF.student.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.student.vo.StudentVO;
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	
	//by수경 휴학/복학/전과신청 시 학생정보 나타내는 테이블 데이터
	@Override
	public StudentVO studentInfo() {
		
		return sqlSession.selectOne("deptManage.studentInfo");
	}

}
