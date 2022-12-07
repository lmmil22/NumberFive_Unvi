// by 유빈 : 학생 로그인 권한 결과 실패페이지 //
Swal.fire({
        icon: 'error',
        title: '로그인 실패',
        text: '로그인 정보가 일치하지않습니다.',
        className : 'swal-wide' //커스텀 사이즈                  
    });
$("button").click(function () {
	location.href='/member/homeLogin'; 
});


