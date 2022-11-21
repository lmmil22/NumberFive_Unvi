/*by 유빈*/
// 게시글 목록에서 게시글상세보기 클릭하는데
// 본인이 작성한 게시글 아니면 확인 불가능하도록 만들기
 
//alert('상세보기이동확인');

//-------------------------------------------------------------------//
// [비밀글 클릭시 본인글이 아니면 볼수없다고 경고창 뜨게하는 실행되는 함수]
/*function isSecrert(){
	//alert('이동확인');
	$(document).on('click', '#warning', function(e) {
      swal(
        'Warning!',
        'You clicked the <b style="color:coral;">warning</b> button!',
        'warning'
      )
    });
}*/
//--------------------------------------------------------------------------------//
//[게시글 삭제 버튼 클릭시 진행 함수]
$("#isDeleteTag").click(function () {
	Swal.fire({
	   title: '정말로 글을 삭제 하시겠습니까?',
	   text: '다시 되돌릴 수 없습니다. 신중하세요.',
	   icon: 'warning',
	   
	   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
	   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
	   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
	   confirmButtonText: '확인', // confirm 버튼 텍스트 지정
	   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
	   
	   reverseButtons: true, // 버튼 순서 거꾸로
	   
	}).then(result => {
	   // 만약 Promise리턴을 받으면,
	   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
	   
	      Swal.fire('삭제가 완료되었습니다.', '확인하였습니다.', 'success');
	      //여기까지 완료!!
	      
	      //어떻게 가져가지...?
	      location.href='@{/board/delete(boardNo=${board.boardNo})}';
	   }
	});
});