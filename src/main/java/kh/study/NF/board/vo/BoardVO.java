package kh.study.NF.board.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;
//by 유빈
@Getter
@Setter
@ToString
public class BoardVO extends PageVO{
// 테이블명:  BOARD 
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
	
	@NotBlank(message = "제목은 필수 입력사항입니다.")
	private String boardTitle;
	
	@NotBlank(message = "내용은 필수 입력사항입니다")
	private String boardContent;
	
	private String boardWriter;//memNo FK걸려있다.
	private String boardCreateDate;
	
	private int boardReadCnt;
	private String cateNo;
	private int replyCnt;
	private String isSecrert;
	private String isNotice;
	
	// [ DB 컬럼 이외 별도추가 ]
	private int rowNum;//행번호로 게시글목록조회위해
	//private int boardPw;//(추가사항)비밀글 비밀번호 이용위해
	private List<ImgVO> imgList;//다중 이미지업로드위해

}
