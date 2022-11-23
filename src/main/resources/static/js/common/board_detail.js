/*by 유빈*/

//-------------------------------------------------------------------//
//[게시글 댓글 수정버튼 클릭시 진행 함수]
function goUpdate(btn){
	if(btn.value == '수정'){
		const replyContent = document.querySelector(".replyContent");
		const originalReplyContent = replyContent.innerText;
		//alert(originalReplyContent);
		replyContent.innerText = '';
		let str = "";
		str += `<textarea class="form-control" name="replyContent">${originalReplyContent}</textarea>`;
		replyContent.insertAdjacentHTML("afterbegin",str);
		
		btn.value = '확인';
	}
	else{
		document.querySelector('.updateForm').submit();
	}
	
}

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
}	

/*Swal.fire({
	title: '이미 신청하셨습니다.',
	text: "신청내역 페이지로 이동합니다.",
	icon: 'warning',
	confirmButtonColor: '#3085d6',
	confirmButtonText: '확인'
	}).then((result) => {
	alert(11);
})*/

//----------------------------------------------------------------------------------------------//
//[게시글 삭제버튼 클릭시 실행되는 함수]
function goDelete(){
   Swal.fire({
	   title: '정말로 삭제 하시겠습니까?',
	   text: '다시 되돌릴 수 없습니다. 신중하세요.',
	   icon: 'warning',
	   
	   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
	   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
	   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
	   confirmButtonText: '승인', // confirm 버튼 텍스트 지정
	   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
	   reverseButtons: true, // 버튼 순서 거꾸로
   
	}).then(result => {
	   // 만약 Promise리턴을 받으면,
	   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
	   	//ajax start
			$.ajax({
				url: '/board/delete', //요청경로
				type: 'get',
				data: { 'boardNo':board_no}, //필요한 데이터
				success: function(result) {
					 Swal.fire('삭제가 완료되었습니다.', '삭제완료', 'success');
				},
				error: function() {
					Swal.fire({
					        icon: 'warning',
					        title: 'error',
					        text: '에러발생.',
					    });
				}
			});
			//ajax end	
	     
	   }
	});

}