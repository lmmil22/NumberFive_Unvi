package kh.study.NF.board.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.NF.board.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired//어노테이션으로 객체생성
	private SqlSessionTemplate sqlSession;
	
	//게시글등록
	@Override
	public void insertBoard(BoardVO boardVO) {
		sqlSession.insert("boardMapper.insertBoard",boardVO);
		
	}
	//게시글목록조회
	@Override
	public List<BoardVO> selectBoard() {
		return sqlSession.selectList("boardMapper.selectBoard");
	}
	//게시글상세조회
	@Override
	public BoardVO selectDetailBoard(int boardNum) {
		return sqlSession.selectOne("boardMapper.selectDetailBoard", boardNum);
	}
	//글수정
	@Override
	public void update(BoardVO boardVO) {
		 sqlSession.update("boardMapper.update", boardVO);
	}
	//글삭제
	@Override
	public void delete(int boardNum) {
		 sqlSession.delete("boardMapper.delete",boardNum);
	}
}
