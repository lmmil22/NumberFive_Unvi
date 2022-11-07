//by수경 학생이 복학신청 버튼을 눌렀을 때
//재학중인 학생이 눌렀을 때 복학신청 버튼이 보이지 않도록 구현
//휴학중인 학생이 눌렀을 때 복학신청이 가능하도록 구현
//복학신청 유의사항 팝업창이 뜨고 확인버튼 누르면 
//복학신청이 완료 되었습니다. 팝업이 뜬 후 신청현황 페이지로 이동


function applyReturnUniv(){
	
	const textArea = document.querySelector('textarea').value;
	//alert(textArea);

	//by수경 휴학사유 입력칸 공백 및 빈칸 방지
	if(textArea.replace(/\s| /gi, "").length == 0){
		alert('휴학 사유는 필수 입력 사항입니다. \n휴학 사유를 작성하여 주십시오.');
		return;
	}

	//by수경 복학신청 버튼 클릭 시 유의사항 모달창
	//모달창 소스
	const modal = new bootstrap.Modal('#returnUnivModal');
	//모달 보여주기
	modal.show();
	
}

//by수경 복학신청 form태그 실행을 위한 모달창 실행
function applyResult(){
	
	const stuNo = document.querySelector('#stuNo').value;
	const fromColl = document.querySelector('#collNo').value;
	const fromDept = document.querySelector('#deptNo').value;
	const applyCode = document.querySelector('#applyCode').value;
	
	
	$.ajax({
		url: '/stu/applyReturnUnivAjax', //요청경로
		type: 'post',
		data: {
			'stuNo': stuNo, 'fromColl': fromColl,
			'fromDept': fromDept, 'applyCode': applyCode
		}, //필요한 데이터

		success: function(result) {

			//모달창 소스
			const modal = new bootstrap.Modal('#applyResultModal');
			//모달 보여주기
			modal.show();

		},

		error: function() {
			alert('실패');
		}
	});
}

//by수경 신청현황을 보여주는 페이지로 이동
function nextPage(){
	
	const stuNo = document.querySelector('#stuNo').value;
	
	location.href=`/stu/stuApplyList?stuNo=${stuNo}`;
}



