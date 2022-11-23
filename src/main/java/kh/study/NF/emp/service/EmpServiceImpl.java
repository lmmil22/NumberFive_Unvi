package kh.study.NF.emp.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.dept.vo.ColleageVO;
import kh.study.NF.dept.vo.DeptVO;
import kh.study.NF.emp.vo.AcademicProbationVO;
import kh.study.NF.emp.vo.DeptManageVO;
import kh.study.NF.emp.vo.StatusInfoVO;
import kh.study.NF.emp.vo.StuOutVO;
import kh.study.NF.student.vo.StudentVO;
//by수경 학생의 복학, 휴학, 전과, 복수전공신청에 대한 관리자 영역
@Service("empService")
public class EmpServiceImpl implements EmpService{

	@Autowired
	private SqlSessionTemplate sqlSession;

	//by 수경 학생의 복학, 휴학, 전과, 복수전공 신청 내역 관리자가 목록조회
	@Override
	public List<DeptManageVO> applyList(Map<String, String> map) {
		return sqlSession.selectList("deptManageMapper.applyList", map);
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
	
	//by수경 관리자에게 전과신청서 보여주기
	@Override
	public DeptManageVO showChangeMajor(DeptManageVO deptManageVO) {
		return sqlSession.selectOne("deptManageMapper.showChangeMajor", deptManageVO);
	}

	//by수경 관리자에게 복수전공 신청서 보여주기
	@Override
	public DeptManageVO showDoubleMajor(DeptManageVO deptManageVO) {
		return sqlSession.selectOne("deptManageMapper.showDoubleMajor", deptManageVO);
	}
	
	//by수경 전과/복수전공신청 일괄 승인
	@Override
	public void changeDoubleMajorAllAccept(DeptManageVO deptManageVO) {
		sqlSession.update("deptManageMapper.changeDoubleMajorAllAccept", deptManageVO);
	}
	
	//by수경 전과/복수전공신청 단일 승인
	@Override
	public void acceptChangeDoubleMajor(DeptManageVO deptManageVO) {
		sqlSession.update("deptManageMapper.acceptChangeDoubleMajor", deptManageVO);
		
	}
	
	//by수경 학사경고 페이지에서 전체 학생 조회하기
	@Override
	public List<StudentVO> selectAllStu(Map<String, String> paramMap) {
		
		return sqlSession.selectList("statusInfoMapper.selectAllStu", paramMap);
	}
	
	//by수경 전공학과 목록 ajax
	@Override
	public List<DeptVO> getDeptList(Map<String, String> paramMap) {
		
		return sqlSession.selectList("statusInfoMapper.getDeptList", paramMap);
	}
	
	//by수경 제적학생 목록조회
	@Override
	public List<StuOutVO> selectStuOutList(Map<String, String> paramMap) {
		
		return sqlSession.selectList("statusInfoMapper.selectStuOutList",paramMap );
	}
	
	//by수경 학사경고 모달창 학생 기본정보 데이터 출력 
	@Override
	public StudentVO probationStuInfo(String stuNo) {
		
		return sqlSession.selectOne("statusInfoMapper.probationStuInfo", stuNo);
	}
	
	//by수경 학사경고 승인하기
	@Override
	public void insertProbation(AcademicProbationVO probationVO) {
		sqlSession.insert("statusInfoMapper.insertProbation", probationVO);
		
	}
	
	//by수경 statusInfo에 데이터 삽입하기(학사경고/제적 모두 넣기)
	@Override
	public void insertStatusInfo(StatusInfoVO statusInfoVO) {

		sqlSession.insert("statusInfoMapper.insertStatusInfo", statusInfoVO);
	}
	
	//by수경 stu-out 제적 데이터 삽입하기
	@Override
	public void insertStuOut(StuOutVO stuOutVO) {
		sqlSession.insert("statusInfoMapper.insertStuOut", stuOutVO);
	}

	//by수경 학사경고 일자/사유 목록 
	@Override
	public List<AcademicProbationVO> probationReason(String stuNo) {

		return sqlSession.selectList("statusInfoMapper.probationReason",stuNo);
	}
	
	//by수경 제적 시 stuStatus update 쿼리 실행
	@Override
	public void changeStuStatus(String stuNo) {
		
		sqlSession.update("statusInfoMapper.changeStuStatus", stuNo);
		
	}
	

}
