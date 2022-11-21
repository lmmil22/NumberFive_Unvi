package kh.study.NF.board.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.board.vo.BoardCategoryVO;
import kh.study.NF.board.vo.BoardVO;
import kh.study.NF.board.vo.ReplyVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired//어노테이션으로 객체생성
	private SqlSessionTemplate sqlSession;
	
//--------------------------- [ 공통 게시판 ] ------------------------------------//	
	//게시글 등록
	@Override
	public void insertBoard(BoardVO boardVO) {
		sqlSession.insert("boardMapper.insertBoard",boardVO);
	}
	//게시글 목록 조회
	@Override
	public List<BoardVO> selectBoard() {
		return sqlSession.selectList("boardMapper.selectBoard");
	}
	//게시글 목록 조회 + 키워드검색기능 (board 프로젝트 참고)
	@Override
	public List<BoardVO> selectBoardList(BoardVO boardVO) {
		return sqlSession.selectList("boardMapper.selectBoardList",boardVO);
	}
	
	//게시글상세조회(미사용)
	@Override
	public BoardVO selectDetailBoard(String boardNo) {
		return sqlSession.selectOne("boardMapper.selectDetailBoard", boardNo);
	}
	//글수정
	@Override
	public void update(BoardVO boardVO) {
		 sqlSession.update("boardMapper.update", boardVO);
	}
	//글삭제
	@Override
	public void delete(String boardNo) {
		 sqlSession.delete("boardMapper.delete",boardNo);
	}
	//총 게시글  개수 조회
	@Override
	public int selectBoardCnt() {
		return sqlSession.selectOne("boardMapper.selectBoardCnt");
	}
	//각 게시글 조회수 +1씩 증가
	@Override
	public void updateReadCnt(String boardNo) {
		sqlSession.update("boardMapper.updateReadCnt",boardNo);
	}
	
//--------------- [ 게시판 댓글  ] -----------------------------//
	//댓글등록
	@Override
	public void insertReply(ReplyVO replyVO) {
		sqlSession.insert("replyMapper.insertReply",replyVO);
	}
	//댓글 목록조회
	@Override
	public List<ReplyVO> selectReplyList(String boardNo) {
		return sqlSession.selectList("replyMapper.selectReplyList",boardNo);
	}
	//댓글삭제
	@Override
	public void deleteReply(int replyNo) {
		sqlSession.delete("replyMapper.deleteReply",replyNo);
	}
	
	//???댓글상세조회???
	@Override
	public ReplyVO selectReply(int replyNo) {
		return sqlSession.selectOne("replyMapper.selectReply",replyNo);
	}
	//???댓글조회수증가????
	@Override
	public void updateReadCnt(int replyNo) {
		sqlSession.update("replyMapper.updateReadCnt",replyNo);
	}	
	
	
	
//------------------------------ [ 관리자 모드 게시판 ] ------------------------------------//	
	//카테고리 목록조회
	@Override
	public List<BoardCategoryVO> selectBoardCate() {
		return sqlSession.selectList("boardMapper.selectBoardCate");
	}
	//카테고리 등록 
	@Override
	public void insertBoardCate(BoardCategoryVO boardCategoryVO) {
		sqlSession.insert("boardMapper.insertBoardCate",boardCategoryVO);
	}
	//사용중인 카테고리 목록조회
	@Override
	public List<BoardCategoryVO> selectBoardCateUse() {
		return sqlSession.selectList("boardMapper.selectBoardCateUse");
	}
	
	
}
