package kh.study.NF.professor.vo;

import javax.validation.constraints.NotBlank;

import kh.study.NF.student.vo.StudentVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LectureVO {
	//by지아 LectureVO table로 만든 VO
	private String lecNo;
	private String lecName;
	private int lecScore;
	private String collNo;
	private String deptNo;
	private String empNo;
	private String createDate;
	private int maxNum;
	private int nowNum;
	private String semNo;
	private String lecStatus;
	
	@NotBlank(message = "요일은 필수입력입니다.")
	private String lecDay;
	@NotBlank(message = "시작 시간은 필수입력입니다.")
	private String firstTime;
	@NotBlank(message = "끝나는 시간은 필수입력입니다.")
	private String lastTime;
	//[ by 유빈 : 수강신청완료된 시간표 불러오기]
	//------------------------------------------//
	private String startRowNum;
	private String endRowNum;
	//------------------------------------------//
	private LecturePdfVO lecturePdfVO;
	private LectureTimeVO lectureTimeVO;
	private StuGradeVO stuGradeVO;
	
	
	
}
