package kh.study.NF.professor.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.study.NF.emp.vo.EmpVO;
import kh.study.NF.professor.vo.EnrollmentVO;
import kh.study.NF.professor.vo.LecturePdfVO;
import kh.study.NF.professor.vo.LectureTimeVO;
import kh.study.NF.professor.vo.LectureVO;
import kh.study.NF.professor.vo.SemesterVO;
import kh.study.NF.professor.vo.StuGradeVO;

@Service("professorService")
public class ProfessorServiceImpl implements ProfessorService{
	
	@Autowired
	private SqlSessionTemplate sqlSession; 
	
	//강의등록
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertLecture(LectureVO lectureVO, LecturePdfVO lecturePdfVO, LectureTimeVO lectureTimeVO) {
		sqlSession.insert("professorMapper.insertProLec",lectureVO);
		sqlSession.insert("professorMapper.insertLecPpf",lecturePdfVO);
		sqlSession.insert("professorMapper.insertLecTime",lectureTimeVO);
		
	}
	//학기 조회
	@Override
	public List<SemesterVO> selectSemesterList() {

		return sqlSession.selectList("professorMapper.selectSemester");
	}
	//emp 조회
	@Override
	public List<EmpVO> selectEmpList() {
		return sqlSession.selectList("professorMapper.selectEmp");
	}
	//lec 조회
	@Override
	public String getNextLecNo() {
	
		return sqlSession.selectOne("professorMapper.getNextLecNo");
	}
	//등록된 강의 조회 
	@Override
	public List<LectureVO> selectLecList() {
		return sqlSession.selectList("professorMapper.selectLecList");
	}
	//lec 메소드 생성
	@Override
	public LectureVO selectLecture(String lecNo) {
		return sqlSession.selectOne("professorMapper.selectLecture",lecNo);
	}
	//lec 변경 메소드
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateLec(LectureVO lectureVO , LectureTimeVO lectureTimeVO) {
		sqlSession.update("professorMapper.updateLec" , lectureVO);
		sqlSession.update("professorMapper.updateLecTime" , lectureTimeVO);
	}
	//lec 삭제
	@Override
	public void deleteLec(String lecNo) {
		sqlSession.delete("professorMapper.deleteLec" ,lecNo);
		
	}
	
	@Override
	public LecturePdfVO selectLecPdf(String lecNo) {
		return sqlSession.selectOne("professorMapper.selectLecPdf",lecNo);
	}
	//수강신청 강의 리스트 조회시
	@Override
	public List<LectureVO> selectLecListEnroll(EnrollmentVO enrollmentVO) {
		return sqlSession.selectList("professorMapper.selectLecLIstEnroll", enrollmentVO);
	}
	
	//수강 신청
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertEnroll(EnrollmentVO enrollmentVO , String lecNo) {
		sqlSession.insert("professorMapper.insertEnroll", enrollmentVO);
		sqlSession.update("professorMapper.updateNowNum" , lecNo);
	}
	@Override
	public List<EnrollmentVO> selectStuLectureList(EnrollmentVO enrollmentVO) {
		return sqlSession.selectList("professorMapper.selectStuLecture", enrollmentVO);
	}
	//기존에 있던 메소드 
	@Override
	public List<String> selectEnrollmentLecNoList(String stuNo) {
		return sqlSession.selectList("professorMapper.selectEnrollmentLecNoList", stuNo);
	}
	//수강 취소시
	@Override
	public void deleteStuLec(EnrollmentVO enrollmentVO) {

		sqlSession.delete("professorMapper.deleteStuLec",enrollmentVO);
	}
	//수강 신청시 점수등록도 같이 하기 위해서 
//	@Override
//	public List<String> selectEnrollmentLecNoList(String stuNo, StuGradeVO stuGradeVO) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
	
}
