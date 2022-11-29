/*by 유빈*/
///////////////////////////////////////////////////////////////////////
//------------[게시글 댓글 등록 버튼 클릭시 진행 함수]----------------//
function goReg(){

	let replyTextTag = document.querySelector('#replyText').value;//  textarea는 innerText가 아니라 value로 해야함!! 유의!!
	const regReplyForm = document.querySelector('#regReplyForm');
	// 확인용 alert(replyTextTag);
	
	// 댓글이 빈값일 때 
	if(replyTextTag == ''){
		alert('댓글을 작성해주세요.');
		return;
	}
	else{
		regReplyForm.submit();
	}
	
}

///////////////////////////////////////////////////////////////////////
//----[ 작성 후 목록조회된 댓글 수정버튼 클릭시 진행 함수]----------------------//
function goUpdate(btn){
	const replyContent = btn.closest('div.row').querySelector(".replyContent");
	const originalReplyContent = replyContent.innerText;

	//'수정'버튼으로 되어있을 때,
	if(btn.value == '수정'){
		
		replyContent.innerText = '';		
		let str = "";
		str += `<textarea class="form-control" name="replyContent">${originalReplyContent}</textarea>`;
		
		replyContent.insertAdjacentHTML("afterbegin",str);
		//'확인'버튼으로 바꾸기
		btn.value = '확인';
	}
	
	//그렇지 않을 때('확인'버튼일 때)
	else{
	//test
		// 댓글 수정할 때 값이 빈값일 때
		if(replyContent.querySelector("textarea").value == ''){
			alert('댓글을 작성해주세요.');
			
			return ;	
		}
	//test	
		replyContent.closest('form').submit();
	}
	
}
////////////////////////////////////////////////////////////////////////////////////
//---------------[ 댓글등록 유효성 검사 함수 ]------------------------------------//
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

//----------------------------------------------------------------------------------------------//
//[게시글 삭제버튼 클릭시 실행되는 함수]
function goDelete(boardNo){
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
	   if (result.isConfirmed,boardNo) { // 만약 모달창에서 confirm 버튼을 눌렀다면
		const goDeleteForm = document.querySelector('#goDeleteForm');
		let board_no = goDeleteForm.querySelector('input[type="hidden"]').value;
	   	//ajax start
			$.ajax({
				url: '/board/delete', //요청경로
				type: 'post',
				data: { 'boardNo':board_no}, //필요한 데이터
				success: function(result) {
					
					Swal.fire({
					   title: '삭제완료',
					   text: '삭제가 완료되었습니다.',
					   icon: 'success',
					   
					   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
					   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
					   //cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
					   confirmButtonText: '확인',// confirm 버튼 텍스트 지정
					  // cancelButtonText: '취소', // cancel 버튼 텍스트 지정
					   reverseButtons: true, // 버튼 순서 거꾸로
					}).then(result => {
						location.href = '/board/list';
					});
									
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
//----------------------------------------------------------------------------------------------//
//-------------------[ 댓글 삭제 버튼 클릭시 실행되는 함수]-------------------------------------//
function goReplyDelete(replyNo){
	Swal.fire({
		title: '삭제 완료.',
		text: '삭제가 완료되었습니다.',
		icon: 'success',
		showCancelButton: false, // cancel버튼 보이지 않도록(false) 보이도록 하고자 한다면 true
		confirmButtonColor: '#3085d6',
		confirmButtonText: '확인',
		cancelButtonText: '취소'
		
		}).then((result) => {
		if (result.isConfirmed) {
			location.href=`/board/deleteReply?replyNo=${replyNo}`;
		}
	})
}








