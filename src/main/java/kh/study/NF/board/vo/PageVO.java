package kh.study.NF.board.vo;

public class PageVO extends SearchVO {//연달아 상속받도록!BoardVO > PageVo > SearchVO
	private int nowPage;//현재선택된페이지
	private int totalDataCnt;//전체게시글(데이터)수
	private int beginPage;//화면에 보이는 첫 페이지
	private int endPage;//화면에 보이는 마지막 페이지
	private int displayCnt;//한 화면에 보여지는 게시글 수
	private int displayPageCnt;//한 화면에 보여지는 페이지 수
	private boolean prev;//이전 버튼의 유무
	private boolean next;//다음 버튼의 유무
	
	private int startNum;//시작 row_num 행번호
	private int endNum;//마지막 row_num 행번호
	
	//생성자(객체만들어지면서 시작되는 페이지 기본설정)
	public PageVO() {
		nowPage = 1;
		displayCnt = 5;
		displayPageCnt = 5;
	}
	public void setPageInfo() {//페이지정보 setter
		//화면에 보이는 마지막 페이지 번호
		endPage = displayPageCnt * (int)Math.ceil(nowPage/(double)displayPageCnt); 
		//시작페이지
		beginPage = endPage-displayPageCnt+1;
		//전체 페이지수 
		int totalPageCnt = (int)Math.ceil(totalDataCnt / (double)displayCnt);
		//next 버튼 유무
		if(endPage < totalPageCnt) {//화면에 보이는 마지막 페이지값이 전체페이지 값(마지막페이지값)보다 작으면 next버튼보임
			next=true;
		}
		else {
			next=false;
			endPage= totalPageCnt;//뒤에 페이지가 없으면 전체페이지까지만 보이도록!
		}
		//prev 유무( 삼항연산자 )
		prev = beginPage ==1? false :true;
		
		startNum =  (nowPage-1) * displayCnt +1;
		endNum = nowPage * displayCnt ;
	}
	
	public void setNowPage(int nowPage) {
		this.nowPage =nowPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setTotalDataCnt(int totalDataCnt) {
		this.totalDataCnt = totalDataCnt;
		
	}
	public int getTotalDataCnt() {
		return totalDataCnt;
	}
	public void setNext(boolean next) {
		this.next = next;
		
	}
	public boolean getNext() {
		return next;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean getPrev() {
		return prev;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	
	public int getStartNum() {
		return startNum;
	}
	
	public int getEndNum() {
		return endNum;
	}
	
	
}
