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
	
	//회원정보 상세조회
	@Override
	public MemberVO selectMemberDetail(String memNo) {
		return sqlSession.selectOne("memberMapper.selectMemberDetail",memNo);
	}
	
	// 이메일로 비밀번호찾기
	@Override
	public MemberVO findPw(MemberVO memberVO) {
		return sqlSession.selectOne("memberMapper.findPw",memberVO);
	}
	//회원가입
	@Override
	public void join(MemberVO memberVO) {
		 sqlSession.insert("memberMapper.join", memberVO);
	}
	//이메일 임시비밀번호 업데이트
	@Override
	public void updatePw(MemberVO memberVO) {
		 sqlSession.update("memberMapper.updatePw",memberVO);
	}
	// 이메일 및 학번/교번 유효성 검사
	@Override
	public String selectIsValidMem(MemberVO memberVO) {
		String loginInfo = sqlSession.selectOne("memberMapper.selectIsValidMem",memberVO);
		
		return loginInfo;//존재하면 null값이 아님
	}
}
