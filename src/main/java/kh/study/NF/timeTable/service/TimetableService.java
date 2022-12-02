package kh.study.NF.timeTable.service;


import java.util.List;

import kh.study.NF.dept.vo.DeptVO;
import kh.study.NF.emp.vo.DeptManageVO;
import kh.study.NF.professor.vo.LectureVO;
import kh.study.NF.student.vo.StudentVO;

public interface TimetableService {
	List<LectureVO> loadTimeTable();
	}
