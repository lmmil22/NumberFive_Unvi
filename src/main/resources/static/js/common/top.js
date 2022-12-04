/*by 유빈*/


//------------------------------ 변수 ---------------------------------------//
//로그인 모달
const login_modal = document.querySelector('#login_modal');
//------------------------------ 함수 정의 -----------------------------------//

//로그인 기능 함수(ajax로 로그인실행시 진행) 
function goLogin(loginInfo) {
	const member_no = login_modal.querySelector('#memNo').value;
	const member_pw = login_modal.querySelector('#memPw').value;
	
		//ajax start
		//$을 사용하려면, 제이쿼리 문법이기때문에 자스보다 먼저 로딩해야한다
		$.ajax({
			url: '/login', //요청경로
			type: 'post',
			data: { 'memNo':member_no ,'memPw' :member_pw ,'loginInfo' : loginInfo }, //필요한 데이터
			success: function(result) {
				alert(result);
				if(result) {/*loginInfo 값이 null아니면(false)*/
					alert('로그인 성공!!!');
					location.href='/member/homeLogin';
				}
				else{
					//1)alert('로그인 실패!!!');
					//2)swal사용
					Swal.fire({
				        icon: 'warning',
				        title: '로그인 실패',
				        text: '학번/교번 및 비밀번호가 일치하지않습니다.',
				    });
				    
				}
			},
			error: function() {
				//1)alert('로그인 실패');
				//2)swal사용
				Swal.fire({
				        icon: 'warning',
				        title: 'error',
				        text: '에러발생.',
				    });
			}
		});
		//ajax end
}

//----------------------------- 이벤트 정의 -----------------------------------//

//// 로그인 실패여부로 모달창을 띄워주는 기능
function isLoginFail(){
	const isLoginFail = document.querySelector('#isLoginFail').value;

	if(isLoginFail == 'true'){
		//아이디를 통해서 모달객체를 받아서 show로 강제로 열리도록(닫히도록) 할수있다.
		const myModalAlternative = new bootstrap.Modal('#login_modal');
		myModalAlternative.show(); //myModalAlternative.hide();
	}
// 확인) 로그인 실패여부를 js에 데이터 불러오기
const a = `[[${isLoginFail}]]`;
alert(a);
}


/////////////////////////////////////////////////////////////////////////
// 구글링 소스 (스프링부트로 이메일로 임시비밀번호 찾기 )
/*$("#checkEmail").click(function () {
    const userEmail = $("#userEmail").val();
    const userName = $("#memNo").val();
    const checkAndSendEmail = document.forms["checkAndSendEmail"];
    $.ajax({
        type: 'post',
        url: '/member/checkAndSendEmail',
        data: {
            'memEmail': userEmail,
            'memNo': userName
        },
        dataType: "text",
        success: function (result) {
            if(result != null){
               alert('임시비밀번호를 전송 했습니다.');
			  
               checkAndSendEmail.submit();
            }else {
                alert('존재하지 않은 이메일입니다.');
            }

        },error: function () {
            console.log('에러 체크!!')
        }
    })
});
    */

//-----------------------------------------------------------------------------------------------------------------//	
// < SWAL TEST > :  클릭했을 때 실행되는 SWAL
//로그인 버튼클릭시 진행되는 함수
$("#alertStart").click(function () {
	// - 1)로그인 성공시
		// 1-1) 로그인 인증을 성공하셨습니다. 
		// 1-2) 로그인 권한에 따라  확인버튼 클릭시, 각각의 컨트롤러로 이동시킨다. 
		// -->  교수/관리자/학생 에따라 각각 이동  /member/afterLogin 으로 이동
	// - 2)로그인 실패시
		// 2-1) 비밀번호와 학번/교번이 올바르지 않습니다. 다시 재로그인하도록 이동시킨다.
    Swal.fire({
        icon: 'success',
        title: '로그인 성공',
        text: '로그인 인증이 되었습니다.',
    });
});


//-----------------------------------------------------------------------------------------------------------------//	



