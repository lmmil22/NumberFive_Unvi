/*by 유빈*/
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

////////////////////////////////////////////////////////////////////////////////////
//----------[ 회원가입 모달 유효성 검사 함수 ]------------------------------------//
function checkValid(){
// ----------------------------변수선언-------------------------------------------//	
	let str ='';//validation 처리 표시 문자열

	let memNoTag = document.querySelector('.memNo');
	let memNameTag = document.querySelector('#memName');
	let memAddrTag = document.querySelector('#memAddr');
	let memAddrDetailTag = document.querySelector('#memAddrDetail');
	let memPwTag = document.querySelector('.memPw');
	let memEmailTag = document.querySelector('#memEmail');
	//let memRoleTag = document.querySelector('.memRole');
	
	// --비밀번호 유효성검사
	//조건: 영문 및 숫자 조합 8자리 이상 ~ 15자리 이하
	let pw_regex = new RegExp("^(?=.*[0-9])(?=.*[a-zA-z]).{8,15}$");
	// --이메일 유효성검사
	let email_regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
// ---------------- if문 --------------------------------------------------------//	
// [ 빈 값일 때 ]
	
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
				$(memNoTag).next().remove();
				str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
				memNoTag.insertAdjacentHTML('afterend', str);
				
				//이름
				if(memNameTag.value == ''){
					str = '이름은 필수입력입니다.';
					$(memNameTag).next().remove();
					str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
					memNameTag.insertAdjacentHTML('afterend', str);
					
					//주소
					if(memAddrTag.value == ''){
						str = '주소는 필수입력입니다.';
						$(memAddrTag).next().remove();
						str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
						memAddrTag.insertAdjacentHTML('afterend', str);
						
						//상세주소
						if(memAddrDetailTag.value == ''){
							str = '상세주소는 필수입력입니다.';
							$(memAddrDetailTag).next().remove();
							str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
							memAddrDetailTag.insertAdjacentHTML('afterend', str);
							
							//회원권한
							/*if(memRoleTag.value == ''){
								str = '회원권한은 필수 입니다.';
								$(memRoleTag).next().remove();
								str = `<span style="color:red; font-size:0.5rem;">${str}</span>`;
								memRoleTag.insertAdjacentHTML('afterend', str);
								
								return ;	
							}*/
							
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
	
	
	// 형식에 맞지않을 때
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
	
   