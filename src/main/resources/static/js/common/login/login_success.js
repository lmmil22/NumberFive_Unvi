// by 유빈 : 학생 로그인 권한 결과 성공페이지!! //
Swal.fire({
        icon: 'success',
        title: '로그인 성공',
        text: '로그인 인증이 되었습니다.',
        className : 'swal-wide' //커스텀 사이즈                  
    });
$("button").click(function () {
	location.href='/member/afterLogin'; 
});

