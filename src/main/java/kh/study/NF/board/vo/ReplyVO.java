package kh.study.NF.board.vo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//by 유빈 :게시판 댓글 기능

@Getter
@Setter
@ToString
public class ReplyVO {
//	테이블명: [ BOARD_REPLY ]
//	REPLY_NO
//	BOARD_NO
//	REPLY_CONTENT
//	REPLY_WRITER
//	IS_SECRET
//	REPLY_CREATE_DATE
	
	private int replyNo;//
	private String boardNo;// 주의) 사용할땐 to_number로!! 
    private String replyContent;
	private String replyWriter; //--mem_no 학번/교번 
	private String isSecret;//--디폴트 N //Y
	private String replyCreateDate;
	
	
}
