/*by 유빈*/

//------------------------------ 변수 ---------------------------------------//
//로그인 모달
const login_modal = document.querySelector('#login_modal');

//------------------------------ 함수 정의 -----------------------------------//

//로그인 기능 함수(ajax로 로그인실행시 진행) 
function goLogin(loginInfo) {
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
				if(result) {/*loginInfo 값이 null아니면(false)*/
					alert('로그인 성공!!!');
					location.href='/member/afterLogin';
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
//const a = `[[${isLoginFail}]]`;
//alert(a);
}

//////////////////로그인 모달이 닫히면 실행되는 이벤트
login_modal.addEventListener('hidden.bs.modal', function(event) {
	login_modal.querySelector('form').reset();
});