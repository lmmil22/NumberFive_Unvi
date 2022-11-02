package kh.study.NF.member.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.member.vo.MemberVO;
// by 유빈
@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired//어노테이션으로 객체생성
	private SqlSessionTemplate sqlSession;
	//로그인
	@Override
	public MemberVO login(MemberVO memberVO) {
		return sqlSession.selectOne("memberMapper.login",memberVO);
	}
	//목록조회
	@Override
	public List<MemberVO> selectMemberList( ) {
		return sqlSession.selectList("memberMapper.selectMemberList");
	}
	
	//상세조회
	@Override
	public MemberVO selectMemberDetail(String memNo) {
		return sqlSession.selectOne("memberMapper.selectMemberDetail",memNo);
	}
}
