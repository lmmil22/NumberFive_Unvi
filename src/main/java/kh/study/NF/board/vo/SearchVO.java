package kh.study.NF.board.vo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//by 유빈 :게시판 키워드검색기능 
@Getter
@Setter
@ToString
public class SearchVO {//상속 -> boardVO
	private String searchKeyword;
	private String searchValue;

}
