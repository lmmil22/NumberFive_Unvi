/*by 유빈*/
// 목적: 
// 1. 로그인 시, 실패성공 alert띄우고 해당 다음 페이지 넘어가기위해서
// 2. 이메일비밀번호찾기 모달창 띄우면서, ajax로 조회성공되면 이메일로 임시비밀번호발급을 위해서 발급되면 다시 로그인하기.
// 3. 회원가입 시큐리티적용 암호화된 비밀번호 사용하기


//------------------------------ 변수 ---------------------------------------//
//회원가입 모달
const join_modal = document.querySelector('#join_modal');
//로그인 모달
const login_modal = document.querySelector('#login_modal');

//------------------------------ 함수 정의 -----------------------------------//

//회원가입에서 search 버튼 클릭시, 진행 메소드
function searchAddr() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			// 예제를 참고하여 다양한 활용법을 확인해 보세요.

			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			const roadAddr = data.roadAddress; // 도로명 주소 변수
			document.querySelector('#memAddr').value = roadAddr;

		}
	}).open();
}

//로그인 기능 함수(ajax로 로그인실행시 진행) 
function sendEmail(loginInfo) {
	//[[${sessionScope.loginInfo}]] js에서 데이터가져오는 방법
	const member_no = login_modal.querySelector('#memNo').value;
	const member_name = login_modal.querySelector('#memName').value;
	const member_email = login_modal.querySelector('#memEmail').value;
	
		//ajax start
		//$을 사용하려면, 제이쿼리 문법이기때문에 자스보다 먼저 로딩해야한다
		$.ajax({
			url: '/member/ajaxLogin', //요청경로
			type: 'post',
			data: { 'memNo':member_no ,'memName' :member_name ,'memEmail' :member_email,'loginInfo' : loginInfo }, //필요한 데이터
			success: function(result) {
				alert(result);
				if(result) {/*loginInfo 값이 null아니면(false)*/
					alert('로그인 성공!!!');
					location.href='/member/homeLogin';
				}
				else{
					alert('로그인 실패!!!');
				}
			},
			error: function() {
				alert('로그인 실패');
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
//----------------------------- 이벤트 정의 ---------------------------------//
//////////////////회원가입 모달이 닫히면 실행되는 이벤트(함수 매개변수 event)///////////////
join_modal.addEventListener('hidden.bs.modal', function(event) {//모달이 완전히 닫혔을 때 실행
	//--------- <모듈창을 다시 열었을 때, 이전 값들 남아있지 않도록 빈값 만들기> ---------//
	//[방법(1)_기본]
	//회원가입 모달창에 있는 input 태그를 모두들고온다
	//('')안에는 css와 동일하다
	//input태그 중 버튼만 제외하고 모두 선택한다
	//	const modal_inputs = join_modal.querySelectorAll('input:not[type="button"]');

	//반복문돌려서 모든 input태그 ''빈값을 주면 모듈창을 닫았을 때 빈값이다.
	//	for(const inputTag of modal_inputs){
	//		inputTag.value ='';
	//	}
	//[방법(2)_간략]
	// 모달창의 from태그를 선택하여 초기화
	join_modal.querySelector('form').reset();
});


//////////////////로그인 모달이 닫히면 실행되는 이벤트////////////////////////////////

// 회원가입 유효성처리한다고 일단 주석처리함 오류나서
// login_modal.addEventListener('hidden.bs.modal', function(event) {
//	login_modal.querySelector('form').reset();
//});




/////////////////////////////////////////////////////////////////////////
// 내가 갖고온 소스 (스프링부트로 이메일로 임시비밀번호 찾기 )
/*$('.modal').on('hidden.bs.modal', function (e) {
        console.log('modal close');
        $(this).find('form')[0].reset()
    });

    $("#checkEmail").click(function () {
	alert('이메일을 발송했습니다. 확인해주세요.');
	
	
        let userEmail = $("#userEmail").val();
        let userName = $("#userName").val();

        $.ajax({
            type: "GET",
            url: "/mail/check/findPw",
            data: {
                "userEmail": userEmail,
                "userName": userName
            },
            success: function (res) {
                if (res['check']) {
                    swal("발송 완료!", "입력하신 이메일로 임시비밀번호가 발송되었습니다.", "success").then((OK) => {
                        if(OK) {
                            $.ajax({
                                type: "POST",
                                url: "/mail/check/findPw/sendEmail",
                                data: {
                                    "userEmail": userEmail,
                                    "userName": userName
                                }
                            })
                            window.location = "/login";
                        }


                    }
                )
                    $('#checkMsg').html('<p style="color:darkblue"></p>');
                } else {
                    $('#checkMsg').html('<p style="color:red">일치하는 정보가 없습니다.</p>');
                }
            }
        })
    })*/
    
    

////////////////////////////////////////////////////////////////////////////////////
//----------[ 회원가입 모달 유효성 검사 함수 ]------------------------------------//
function checkValid(){
// ----------------------------변수선언-------------------------------------------//	
	let str ='';//validation 처리 표시 문자열

	let memNoTag = document.querySelector('#memNo');
	let memNameTag = document.querySelector('#memName');
	let memAddrTag = document.querySelector('#memAddr');
	let memAddrDetailTag = document.querySelector('#memAddrDetail');
	
	// --비밀번호 유효성검사
	//조건: 영문 및 숫자 조합 8자리 이상 ~ 15자리 이하
	let pw_regex = new RegExp("^(?=.*[0-9])(?=.*[a-zA-z]).{8,15}$");
	let memPwTag = document.querySelector('#memPw');
	// --이메일 유효성검사
	let email_regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
	let memEmailTag = document.querySelector('#memEmail');
	
// ---------------- if문 --------------------------------------------------------//	
// 1) 빈 값일 때
	// 비번
	if(memPwTag.value == ''){
		str = '비밀번호는 필수입력입니다.';
		
		$(memPwTag).next().remove();
		str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
		memPwTag.insertAdjacentHTML('afterend', str);
		//이메일
		if(memEmailTag.value == ''){
			str = '이메일은 필수입력입니다.';
			
			$(memEmailTag).next().remove();
			str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
			memEmailTag.insertAdjacentHTML('afterend', str);
			//학번교번
			if(memNoTag.value == ''){
				str = '학번 및 교번은 필수입력입니다.';
				
				//$(memNoTag).next().remove();
				str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
				memNoTag.insertAdjacentHTML('afterend', str);
				//이름
				if(memNameTag.value == ''){
					str = '이름은 필수입력입니다.';
					
					//$(memNameTag).next().remove();
					str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
					memNameTag.insertAdjacentHTML('afterend', str);
					//주소
					if(memAddrTag.value == ''){
						str = '주소는 필수입력입니다.';
						
						//$(memAddrTag).next().remove();
						str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
						memAddrTag.insertAdjacentHTML('afterend', str);
						//상세주소
						if(memAddrDetailTag.value == ''){
							str = '상세주소는 필수입력입니다.';
							
							str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
							memAddrDetailTag.insertAdjacentHTML('afterend', str);
							
							return ;	
						}
						return ;	
					}
					return ;	
				}
				return ;	
			}
			return ;	
		}
		return ;	
	}
	
	
// 2)정규식 맞지않을 때
	if(!pw_regex.test(memPwTag.value)){// false 값이 아니면(비밀번호정규식대로 사용하지 않으면)
		str = '올바른 비밀번호 형식이 아닙니다.';
		
		$(memPwTag).next().remove();
		str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
		memPwTag.insertAdjacentHTML('afterend', str);
		
		if(!email_regex.test(memEmailTag.value)){// false 값이 아니면(비밀번호정규식대로 사용하지 않으면)
			str = '올바른 이메일 형식이 아닙니다.';
			
			$(memEmailTag).next().remove();
			str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
			memEmailTag.insertAdjacentHTML('afterend', str);
			
			return;
		}
		return;
	}	
	
	//폼태그 서브밋주기
	document.querySelector('#joinFormTag').submit();
}	

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



