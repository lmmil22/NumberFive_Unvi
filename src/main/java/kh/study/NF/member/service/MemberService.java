package kh.study.NF.member.service;

import java.util.List;
// by 유빈
import kh.study.NF.member.vo.MemberVO;
//by 유빈
public interface MemberService {
	//회원가입
	void join(MemberVO memberVO);
	//로그인
	MemberVO login(MemberVO memberVO);
	
	//비밀번호찾기
	MemberVO findPw(MemberVO memberVO);
	
	//목록조회
	List<MemberVO> selectMemberList( );
	
	//상세조회
	MemberVO selectMemberDetail(String memNo);
	
	//이매일로 전송된 비밀번호로 업데이트
	void updatePw(MemberVO memberVO);
}
