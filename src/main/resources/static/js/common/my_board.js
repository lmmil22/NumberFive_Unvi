/*by 유빈*/

//-------------------------------------------------------------------//
// [비밀글 클릭시 본인글이 아니면 볼수없다고 경고창 뜨게하는 실행되는 함수]
$("#warning").click(function () {
    Swal.fire({
        icon: 'warning',
        title: '비밀글 확인 불가능 ',
        text: '작성자 본인이어야 확인 가능합니다.',
    });
});
///////////////////////////////////////////////////////////////////////////////
//------------[ 게시판 조건검색기능 키워드 빈값 유효성 검사  ]--------------//
//변수선언
//var searchKeywordTag = document.getElementById("searchKeywordTag").value;
//alert(searchKeywordTag);
//함수선언
/*function validateKeyword(){
  if(searchKeywordTag == '') { // 만일 두 인풋 필드값이 같지 않을 경우
    searchKeywordTag.setCustomValidity("검색 내용을 입력해주세요."); 
    
  } 
  else { // 만일 두 인풋 필드값이 같을 경우
    searchKeywordTag.setCustomValidity(''); 
    // 등록 버튼클릭시, 이벤트 실행
  }
  
}*/

// 함수실행
//searchKeywordTag.onchange = validateKeyword;

///////////////////////////////////////////////////////////////////////////////
var searchKeywordTag = document.getElementById("searchKeywordTag").value;
var boardWriterTag = document.getElementById("boardWriterId").value;

$().ready(function () {
	$("#searchBtn").click(function () {
		if(searchKeywordTag == ''){
		    Swal.fire({
		        icon: 'warning',
		        title: ' [ 검색내용 입력 필수 ]',
		        text: '검색내용을 입력해주세요.',
		   		showCancelButton: false, 
				confirmButtonColor: '#3085d6',
				confirmButtonText: '확인',
				cancelButtonText: '취소'
				}).then((result,boardWriterTag) => {
					if (result.isConfirmed) {
					location.href=`/board/Myboard?boardWriter=`+ boardWriterTag;
				}
			})
		}
		else{
			Swal.fire({
			  icon: 'success',  // 여기다가 아이콘 종류를 쓰면 됩니다.                     
			  title: '[ 검색 완료 ]',    
			  text: '검색한 내용을 불러오겠습니다.', 
			});
		}
	});
});