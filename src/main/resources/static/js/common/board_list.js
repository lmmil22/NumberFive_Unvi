/*by 유빈*/

//alert('목록페이지이동확인');
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
// [게시판 조건 검색 기능 버튼 클릭시 ]
//alert(searchKeywordTag);


$("#searchBtn").click(function () {
	var searchValue = document.getElementById("searchValue").value.trim();
	var searchKeyword = document.getElementById("searchKeyword").value.trim();
	
	if(searchValue == '' || searchValue == null){
	    Swal.fire({
	        icon: 'warning',
	        title: ' [ 검색 내용 입력 필수 ]',
	        text: '검색하실 내용을 입력해주세요.',
	   		showCancelButton: false, 
			confirmButtonColor: '#3085d6',
			confirmButtonText: '확인',
		})
	}
	
	else{
		
		Swal.fire({
		  icon: 'success',  // 여기다가 아이콘 종류를 쓰면 됩니다.                     
		  title: '[ 검색 실행 ]',    
		  text: '검색한 내용을 불러오겠습니다.', 
		});
		$("button").click(function () {
			location.href=`/board/list?searchKeyword=${searchKeyword}&searchValue=${searchValue}`; 
		});
	}
});

///////////////////////////////////////////////////////////////////////////////


















