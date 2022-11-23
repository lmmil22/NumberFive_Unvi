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
//----------------------------- 이벤트 정의 ---------------------------------//

//////////////////로그인 모달이 닫히면 실행되는 이벤트////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////
/*//----------[ 회원가입 모달 유효성 검사 함수 ]------------------------------------//
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
}	*/

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



