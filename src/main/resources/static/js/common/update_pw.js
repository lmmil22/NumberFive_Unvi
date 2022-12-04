/*by 유빈 : 비밀번호변경 재입력*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//-------------------------------- 변수 선언 -------------------------------------//
var password = document.getElementById("password")
  , confirm_password = document.getElementById("confirm_password");
let str =''; //validation 처리 표시 문자열
// 비밀번호 유효성검사) 조건: 영문 및 숫자 조합 8자리 이상 ~ 15자리 이하
let pw_regex = new RegExp("^(?=.*[0-9])(?=.*[a-zA-z]).{8,15}$");  

//----------[ 임시비밀번호 유효성 검사 함수 ]------------------------------------//
function validatePassword(){
	// 1)정규식 맞지않을 때
	if(!pw_regex.test(password.value)){// false 값이 아니면(비밀번호정규식대로 사용하지 않으면)
		str = '비밀번호 형식이 올바르지 않습니다.';
		$(password).next().remove();
		str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
		password.insertAdjacentHTML('afterend', str);
	}	
	// 2)비밀번호 재입력 일치검사
	if(password.value != confirm_password.value) {
	    confirm_password.setCustomValidity("비밀번호가 일치하지 않습니다.");
	  	
	} else {
	    confirm_password.setCustomValidity(''); 
	    // 변경완료 버튼클릭시, 이벤트 실행
	    document.getElementById("checkBtn").onclick = function(){
			Swal.fire({
				// 형식에맞게 비밀번호 (재)입력시 스왈띄우고 홈화면가기  
				title: '[ 비밀번호 변경 성공 ]',
				text: "입력하신 비밀번호로 재로그인해주시기바랍니다.",
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
		} 	 
	} 	 
}
//--------------------------------  함수실행  -----------------------------------------//	
password.onchange = validatePassword ; 
confirm_password.onkeyup = validatePassword ;



