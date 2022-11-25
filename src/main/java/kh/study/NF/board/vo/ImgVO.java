package kh.study.NF.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImgVO {
	// BOARD_IMG 테이블
//	IMG_CODE
//	ORIGIN_NAME
//	ATTACHED_NAME
//	IS_MAIN
//	BOARD_NO	
	private String  imgCode;
	private String  originName;
	private String  attachedName;
	private String  isMain;//null가능(미사용)갖고오긴 했는디..있을 필요가 없다... Y(메인)/N(서브이미지)
	private String boardNo;
	
	
}
