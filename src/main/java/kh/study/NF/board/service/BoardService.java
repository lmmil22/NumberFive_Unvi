package kh.study.NF.board.service;

import java.util.List;

import kh.study.NF.board.vo.BoardVO;

public interface BoardService {
	void insertBoard(BoardVO boardVO);
	
	List<BoardVO> selectBoard();
	BoardVO selectDetailBoard(String boardNo);
	void update(BoardVO boardVO);
	void delete(String boardNo);
}
