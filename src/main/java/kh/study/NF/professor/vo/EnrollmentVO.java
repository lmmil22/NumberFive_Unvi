package kh.study.NF.professor.vo;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnrollmentVO {
//By 지아 enrollment테이블로 만든 vo입니다
	
	private String	enrollmentNo;
	private String	lecNo;
	private String	stuNo;
	private String	semNo;
	private LectureVO lectureVO;
	
	//이미 수강신청한 lec_no 목록 정보를 담기 위한 변수
	List<String> emrolledList;
	
	//조건 검색을 위한 변수
	private Map<String , String> paramMap;
	
}
