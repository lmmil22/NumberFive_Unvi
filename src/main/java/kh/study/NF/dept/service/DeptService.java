package kh.study.NF.dept.service;

import java.util.List;

import kh.study.NF.dept.vo.ColleageVO;
import kh.study.NF.dept.vo.DeptVO;

public interface DeptService {

	List<DeptVO> selectDeptList();
	List<ColleageVO> selectCollList();
	
	List<DeptVO> getDeptList(String collNo);
	
}
