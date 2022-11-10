package kh.study.NF.professor.service;

import java.util.List;

import kh.study.NF.emp.vo.EmpVO;
import kh.study.NF.professor.vo.EnrollmentVO;
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
	
	//강의 목록 조회
	List<LectureVO> selectLecList();
	
	//선택한 강의조회
	 LectureVO selectLecture(String lecNo);
	 
	 //수정
	 void updateLec(LectureVO lectureVO , LectureTimeVO lectureTimeVO);
	 
	 //삭제
	 void deleteLec(String lecNo);
	 
	 //등록된 pdf 조회
	 LecturePdfVO selectLecPdf(String lecNo);
	 
	 //강의 수강 신청시 조회 
	 List<LectureVO> selectLecLIstEnroll();
	 
	 //수강 신청
	 void insertEnroll(EnrollmentVO enrollmentVO , String lecNo); 
	 
	 
}
