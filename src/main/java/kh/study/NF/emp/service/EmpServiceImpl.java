package kh.study.NF.emp.service;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.emp.vo.DeptManageVO;
//by수경 학생의 복학, 휴학, 전과, 복수전공신청에 대한 관리자 영역
@Service("empService")
public class EmpServiceImpl implements EmpService{

	@Autowired
	private SqlSessionTemplate sqlSession;

	//by 수경 학생의 복학, 휴학, 전과, 복수전공 신청 내역 관리자가 목록조회
	@Override
	public List<DeptManageVO> applyList() {
		return sqlSession.selectList("deptManageMapper.applyList");
	}
	
	//by수경 학생이 복학, 휴학, 전과, 복수전공 현재 학기에 신청내역 있는지 확인(수정예정)
	@Override
	public DeptManageVO checkApply(DeptManageVO deptManageVO) {
		return sqlSession.selectOne("deptManageMapper.checkApply", deptManageVO);
	}
	
	//by수경 승인대기/승인완료 상태 변경(라디오 값)
	@Override
	public void changeStatus(DeptManageVO deptManageVO) {
		sqlSession.update("deptManageMapper.changeStatus", deptManageVO);
		
	}
	
	//by수경 복학/휴학신청 일괄승인
	@Override
	public void comebackTakeOffAllAccept(DeptManageVO deptManageVO) {
		sqlSession.update("deptManageMapper.comebackTakeOffAllAccept", deptManageVO);
		
	}

}
