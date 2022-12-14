package kh.study.NF.professor.service;

import java.util.List;

import kh.study.NF.emp.vo.EmpVO;
import kh.study.NF.professor.vo.EnrollmentVO;
import kh.study.NF.professor.vo.GradeVO;
import kh.study.NF.professor.vo.LecturePdfVO;
import kh.study.NF.professor.vo.LectureTimeVO;
import kh.study.NF.professor.vo.LectureVO;
import kh.study.NF.professor.vo.SemesterVO;
import kh.study.NF.professor.vo.StuGradeVO;

public interface ProfessorService {
	
	//BY 지아
	//강의 등록
	void insertLecture(LectureVO lectureVO , LecturePdfVO lecturePdfVO , LectureTimeVO lectureTimeVO);
	
	List<SemesterVO> selectSemesterList();
	List<EmpVO> selectEmpList();
	String getNextLecNo();
	
	//강의 목록 조회
	List<LectureVO> selectLecList(String empNo);
	
	//선택한 강의조회
	 LectureVO selectLecture(String lecNo);
	 
	 //수정
	 void updateLec(LectureVO lectureVO , LectureTimeVO lectureTimeVO);
	 
	 //삭제
	 void deleteLec(String lecNo);
	 
	 //등록된 pdf 조회
	 LecturePdfVO selectLecPdf(String lecNo);
	 
	 //강의 수강 신청시 조회 
	 List<LectureVO> selectLecListEnroll(EnrollmentVO enrollmentVO);
	 
	 //수강 신청
//	 void insertEnroll(EnrollmentVO enrollmentVO , String lecNo); 
	 // //점수 까지 같이 셋팅 
	 void insertEnroll(EnrollmentVO enrollmentVO , StuGradeVO stuGradeVO,  String lecNo); 
	 
	 
	 //학생이 신청한 수강목록조회 
	 List<EnrollmentVO> selectStuLectureList(EnrollmentVO enrollmentVO);
	 
	 //수강 신청 가능한 목록을 띄울 때, 이미 수간 신청한 강의는 
	 //목록에서 제외하기 위해 신청한 lec_no 목록 데이터를 조회
	 List<String> selectEnrollmentLecNoList(String stuNo);
	
//	 List<String> selectEnrollmentLecNoList(String stuNo , StuGradeVO stuGradeVO);
	 
	 //수강 취소시 진행 
	 void deleteStuLec(EnrollmentVO enrollmentVO);
	 
	 // 점수등록할 강의의 학생 목록 조회
	 List<StuGradeVO> selectLecEnrollStuList(String lecNo);
	 
	 //교수가 등록한 강의 목록만 조회 
	 List<LectureVO> selectProFLecList(LectureVO lectureVO);
	 
	 //점수입력을 위한 점수조회
	 List<GradeVO> selectGrade();
	 
	 //학생 점수 업데이트
	 void updateGrade(StuGradeVO stuGradeVO);

	 //중복체크를 위한 시간 검색
	List<LectureTimeVO> selectTime(LectureTimeVO lectureTimeVO); 
	
	//학생이 본인 점수 조회
	List<StuGradeVO> selectStuGrade(String stuNo);
	
}
