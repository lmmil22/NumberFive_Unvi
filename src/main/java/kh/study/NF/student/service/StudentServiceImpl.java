package kh.study.NF.student.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.dept.vo.DeptVO;
import kh.study.NF.emp.vo.DeptManageVO;
import kh.study.NF.student.vo.StudentVO;
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//by수경 휴학/복학/전과신청 시 학생정보 나타내는 테이블 데이터
	@Override
	public StudentVO studentInfo() {
		
		return sqlSession.selectOne("studentMapper.studentInfo");
	}
	
	//by수경 학생이 복학신청 접수 
	@Override
	public void applyReturnUniv(DeptManageVO deptManageVO) {
		sqlSession.insert("studentMapper.applyReturnUniv");
		
	}

	//by수경 학생이 휴학신청 접수
	@Override
	public void applyTakeOffUniv(DeptManageVO deptManageVO) {
		sqlSession.insert("studentMapper.applyTakeOffUniv");
	}

	///by수경 학생이 전과신청 접수
	@Override
	public void applyChangeMajor(DeptManageVO deptManageVO) {
		sqlSession.insert("studentMapper.applyChangeMajor");
	}

	//by수경 학생이 복수전공신청 접수
	@Override
	public void applyAddMajor(DeptManageVO deptManageVO) {
		sqlSession.insert("studentMapper.applyAddMajor");
	}
	
	//by 수경 학생의 복학, 휴학, 전과, 복수전공 신청 내역 목록조회
	//매개변수 String stuNo 추후에 넣을 것
	@Override
	public List<DeptManageVO> stuApplyList() {
		
		return sqlSession.selectList("deptManageMapper.stuApplyList");
	}

	//by수경 전공대학 전공학과 선택 시 재학중인 학과 제외
	//추후 매개변수 DeptVO deptVO
	@Override
	public List<DeptVO> DeptList() {
		
		return sqlSession.selectList("studentMapper.DeptList");
	}


}
