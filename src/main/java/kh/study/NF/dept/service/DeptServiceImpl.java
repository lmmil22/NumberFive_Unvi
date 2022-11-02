package kh.study.NF.dept.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.dept.vo.DeptVO;

//by 지아 dept임플 기본생성했어요
@Service("deptService")
public class DeptServiceImpl implements DeptService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	//dept 목록 조회
	@Override //by 지아
	public List<DeptVO> selectDeptList() {
		return sqlSession.selectList("professorMapper.selectDept");
	}
	
	
	
	
	
}
