package kh.study.NF.board.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class BoardVO {
//디비 실제 컬럼명	
//	BOARD_NO
//	BOARD_TITLE
//	BOARD_CONTENT
//	BOARD_WRITER
//	BOARD_CREATE_DATE
//	BOARD_READ_CNT
//	CATE_NO
//	REPLY_CNT
//	IS_SECRERT
//	IS_NOTICE
	
	private String boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;//작성자 학번및교번으로 함.(mem_no-member테이블) 
	private String boardCreateDate;
	private int boardReadCnt;
	private String cateNo;
	private int replyCnt;
	private String isSecrert;
	private String isNotice;
	
}
