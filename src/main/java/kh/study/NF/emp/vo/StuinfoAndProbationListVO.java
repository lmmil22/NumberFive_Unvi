package kh.study.NF.emp.vo;

import java.util.List;

import kh.study.NF.student.vo.StudentVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//by수경 학사경고 모달창에 데이터를 가져오기 위해 만든 VO입니다.
@Setter
@Getter
@ToString
public class StuinfoAndProbationListVO {
	private StudentVO stuInfo;
	private List<AcademicProbationVO> probationList;
}
