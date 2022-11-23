/*by 유빈*/
// 태그선택
// 감싸고 있는 태그전체를 빈값만들기
// input 타입의 text를 넣는다고 
// 
alert('상세보기이동확인');

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
function goUpdate(){
	
	const replyContent = document.querySelector(".replyContent");
	const originalReplyContent = document.querySelector(".textarea").value;
	//alert(originalReplyContent);
	replyContent.innerText = '';
	let str = "";
	str += `<textarea class="form-control" name="replyContent">${originalReplyContent}</textarea>`;
	replyContent.insertAdjacentHTML("afterbegin",str);
}
