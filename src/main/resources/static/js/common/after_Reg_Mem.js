/** by 유빈: test용 **/
 Swal.fire({
	title: '[ 회원 등록 완료 ]',
	text: "회원 로그인 홈화면으로 이동합니다.",
	icon: 'success',
	
	showCancelButton: false, // cancel버튼 보이지 않도록(false) 보이도록 하고자 한다면 true
	confirmButtonColor: '#3085d6',
	confirmButtonText: '확인',
	cancelButtonText: '취소'
	
	}).then((result) => {
		if (result.isConfirmed) {
			location.href=`/member/homeLogin`;
	}
})
