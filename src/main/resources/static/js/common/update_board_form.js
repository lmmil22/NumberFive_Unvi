/* by 유빈 : 게시글 수정 후 알림창 만들기*/

const updateBoardForm = document.querySelector('#updateBoardForm');
const updateBoardBtn = document.querySelector('#updateBoardBtn');

function goUpdateBoard(){
	
	Swal.fire({//ok
	   title: '[ 수정 완료]',
	   text: ' 게시글 수정이 등록을 완료했습니다.',
	   icon: 'success',
	   
	   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
	   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
	   confirmButtonText: '확인',// confirm 버튼 텍스트 지정
	   reverseButtons: true, // 버튼 순서 거꾸로
	}).then(result => {
		updateBoardForm.submit();
	});
	
}