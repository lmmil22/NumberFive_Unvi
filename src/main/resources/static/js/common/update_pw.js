/*by 유빈*/
//----------[ 임시비밀번호 유효성 검사 함수 ]------------------------------------//
/*function checkValidPw(){
// ----------------------------변수선언-------------------------------------------//	
	let str ='';//validation 처리 표시 문자열

	// 비밀번호 유효성검사) 조건: 영문 및 숫자 조합 8자리 이상 ~ 15자리 이하
	let pw_regex = new RegExp("^(?=.*[0-9])(?=.*[a-zA-z]).{8,15}$");
	// 첫번째 비밀번호 입력
	let memPwTag = document.querySelector('#memPw1');
	// 두번째 비밀번호 재입력
	let memPw2Tag = document.querySelector('#memPw2');*/
// ---------------- if문 --------------------------------------------------------//	
// 1) 빈 값일 때
	// 비번
	/*if(memPwTag.value == ''){
		str = '비밀번호는 필수입력입니다.';
		$(memPwTag).next().remove();
		str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
		memPwTag.insertAdjacentHTML('afterend', str);
		
		if(memPw2Tag.value == ''){
			str = '위와 동일한 비밀번호를 한번 더 입력하세요.';
			$(memPw2Tag).next().remove();
			str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
			memPw2Tag.insertAdjacentHTML('afterend', str);
			return ;
		}	
		return ;	
	}
	*/
	
// 2)정규식 맞지않을 때
	/*if(!pw_regex.test(memPwTag.value)){// false 값이 아니면(비밀번호정규식대로 사용하지 않으면)
		str = '올바른 비밀번호 형식이 아닙니다.';
		$(memPwTag).next().remove();
		str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
		memPwTag.insertAdjacentHTML('afterend', str);
		return;
	}	*/
	//폼태그 서브밋주기
/*	document.querySelector('#checkPwFormTag').submit();
}	
*/







var password 			= document.getElementById("#memPw1")
	, confirm_password	= document.getElementById("#memPw2");
	  
function validatePassword(){
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("비밀번호가 일치하지 않습니다.");
  } else {
    confirm_password.setCustomValidity(''); // 오류가 없으면 메시지를 빈 문자열로 설정해야한다. 오류 메시지가 비어 있지 않은 한 양식은 유효성 검사를 통과하지 않고 제출되지 않는다.
  }
}
	
password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;



//-----------------------------------------------------------------------------------------------------------------//	