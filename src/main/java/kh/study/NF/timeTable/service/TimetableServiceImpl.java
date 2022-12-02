package kh.study.NF.timeTable.service;
//by 유빈: 시간표기능구현
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.dept.vo.DeptVO;
import kh.study.NF.emp.vo.DeptManageVO;
import kh.study.NF.professor.vo.LectureVO;
import kh.study.NF.student.vo.StudentVO;
@Service("timetableService")
public class TimetableServiceImpl implements TimetableService {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<LectureVO> loadTimeTable() {
		return sqlSession.selectList("professorMapper.loadTimeTable");
	}
	
	

}
