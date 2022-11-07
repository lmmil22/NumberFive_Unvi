package kh.study.NF.professor.service;

import java.util.List;

import kh.study.NF.emp.vo.EmpVO;
import kh.study.NF.professor.vo.LecturePdfVO;
import kh.study.NF.professor.vo.LectureTimeVO;
import kh.study.NF.professor.vo.LectureVO;
import kh.study.NF.professor.vo.SemesterVO;

public interface ProfessorService {
	
	//BY 지아
	//강의 등록
	void insertLecture(LectureVO lectureVO , LecturePdfVO lecturePdfVO , LectureTimeVO lectureTimeVO);
	
	List<SemesterVO> selectSemesterList();
	List<EmpVO> selectEmpList();
	String getNextLecNo();
	
	List<LectureVO> selectLecList();
	
	 LectureVO selectLecture(String lecNo);
}
