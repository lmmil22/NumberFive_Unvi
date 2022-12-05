package kh.study.NF.board.vo;

import java.util.List;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class BoardCategoryVO {
// BOARD_CATEGORY 테이블
//	CATE_NO
//	CATE_NAME
//	IS_USE
	private String cateNo;
	private String cateName;
	private String isUse;
	
	private List<String> cateNoList;
	
}
