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
	public StudentVO studentInfo(String stuNo) {
		
		return sqlSession.selectOne("studentMapper.studentInfo",stuNo);
	}
	
	//by수경 학생이 복학신청 접수 
	@Override
	public void applyReturnUniv(DeptManageVO deptManageVO) {
		sqlSession.insert("deptManageMapper.applyReturnUniv",deptManageVO);
		
	}

	//by수경 학생이 휴학신청 접수
	@Override
	public void applyTakeOffUniv(DeptManageVO deptManageVO) {
		sqlSession.insert("deptManageMapper.applyTakeOffUniv",deptManageVO);
	}

	///by수경 학생이 전과신청 접수
	@Override
	public void applyChangeMajor(DeptManageVO deptManageVO) {
		sqlSession.insert("deptManageMapper.applyChangeMajor",deptManageVO);
	}

	//by수경 학생이 복수전공신청 접수
	@Override
	public void applyAddMajor(DeptManageVO deptManageVO) {
		sqlSession.insert("deptManageMapper.applyAddMajor",deptManageVO);
	}
	
	//by 수경 학생의 복학, 휴학, 전과, 복수전공 신청 내역 목록조회
	//매개변수 String stuNo 추후에 넣을 것
	@Override 
	public List<DeptManageVO> stuApplyList(String stuNo) {
		
		return sqlSession.selectList("deptManageMapper.stuApplyList", stuNo);
	}

	//by수경 전공대학 전공학과 선택 시 재학중인 학과 제외
	//추후 매개변수 DeptVO deptVO
	@Override
	public List<DeptVO> DeptList(DeptVO deptVO) {
		
		return sqlSession.selectList("studentMapper.DeptList",deptVO);
	}
	
	//by수경 학생 휴학신청 확정 시 상태 변경(단일 승인/일괄 승인)
	@Override
	public void takeOffStu(DeptManageVO deptManageVO) {
		
		sqlSession.update("studentMapper.takeOffStu",deptManageVO);
		
	}
	
	//by수경 학생 복학신청 확정 시 상태 변경(단일 승인/ 일괄승인)
	@Override
	public void returnStu(DeptManageVO deptManageVO) {

		sqlSession.update("studentMapper.returnStu", deptManageVO);
		
	}
	
	//by수경 학생 전과신청 확정(단일승인/일괄승인) 시 학생 상태 변경
	@Override
	public void changeMajorStu(DeptManageVO deptManageVO) {
		sqlSession.update("studentMapper.changeMajorStu", deptManageVO);
		
	}
	
	//by수경 학생 복수전공 신청 확정(단일승인/일괄승인) 시 학생 상태 변경
	@Override
	public void insertDoubleMajor(DeptManageVO deptManageVO) {
		sqlSession.insert("studentMapper.insertDoubleMajor", deptManageVO);
		
	}

	//by수경 복수전공 단일신청승인(단일승인/일괄승인) 후 학생테이블에 doubleNo 데이터 넣기
	@Override
	public void updateDoubleMajor(String stuNo) {
		sqlSession.update("studentMapper.updateDoubleMajor", stuNo);
		
	}
	//by수경 학생이 학적신청(휴학, 복학, 전과, 복수전공) 관리자 승인 전에 신청 철회
	@Override
	public void deleteApply(DeptManageVO deptManageVO) {
		sqlSession.delete("studentMapper.deleteApply", deptManageVO);
		
	}

}
