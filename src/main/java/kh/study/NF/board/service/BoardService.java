//by 유빈
package kh.study.NF.board.service;
import java.util.List;

import kh.study.NF.board.vo.BoardCategoryVO;
import kh.study.NF.board.vo.BoardVO;

public interface BoardService {
	
//--------------- [ 공통 게시판 ] -----------------------------//
	// 글등록
	void insertBoard(BoardVO boardVO);
	// 글 목록조회
	List<BoardVO> selectBoard();
	// 상세조회
	BoardVO selectDetailBoard(String boardNo);
	// 수정
	void update(BoardVO boardVO);
	// 삭제
	void delete(String boardNo);
	
//--------------- [ 관리자 모드 게시판 ] -----------------------------//
	//카테고리목록조회
	List<BoardCategoryVO> selectBoardCate();
	//카테고리 사용중인 목록조회
	List<BoardCategoryVO> selectBoardCateUse();
	//카테고리등록
	void insertBoardCate(BoardCategoryVO boardCategoryVO);
}
