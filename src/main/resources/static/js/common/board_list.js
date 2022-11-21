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
/*	alert('이동확인');
	//여기까지 완료 
	
	//스왈띄우기....해야함 왜 안..되지...
	$(document).on('click', '#warning', function(e) {
      swal(
        'Warning!',
        'You clicked the <b style="color:coral;">warning</b> button!',
        'warning'
      )
    });
}
*///
/*$().ready(function () {
  
  $("#warning").click(function () {
    Swal.fire({
      icon: 'success',
      title: 'Alert가 실행되었습니다.',
      text: '이곳은 내용이 나타나는 곳입니다.',
    });
*/