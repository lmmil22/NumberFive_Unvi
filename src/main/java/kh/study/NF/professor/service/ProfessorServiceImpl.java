package kh.study.NF.professor.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.study.NF.emp.vo.EmpVO;
import kh.study.NF.professor.vo.LecturePdfVO;
import kh.study.NF.professor.vo.LectureTimeVO;
import kh.study.NF.professor.vo.LectureVO;
import kh.study.NF.professor.vo.SemesterVO;

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
	
	
	
	
}
