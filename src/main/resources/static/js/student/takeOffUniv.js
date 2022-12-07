//by수경 학생이 휴학신청 버튼을 눌렀을 때
//휴학중인 학생이 눌렀을 때 휴학버튼이 보이지 않도록 구현
//재학중인 학생이 눌렀을 때 휴학신청이 가능하도록 구현
//휴학신청 유의사항 팝업창이 뜨고 확인버튼 누르면 
//휴학신청이 완료 되었습니다. 팝업이 뜬 후 신청현황 페이지로 이동

function applyTakeOffUniv(){
	
	//by수경  휴학신청(관리자 승인x, 현재 학기)내역이 있는지 확인
	const stuNo = document.querySelector('#stuNo').value;
	const applyCode = document.querySelector('#applyCode').value;
	
	$.ajax({
		url: '/emp/checkApplyAjax', //요청경로
		type: 'post',
		data: {'stuNo':stuNo,'applyCode':applyCode}, //필요한 데이터
		success: function(result) {
			
			if(result){
				 Swal.fire({
				  title: '이미 신청하셨습니다.',
				  text: "신청내역 페이지로 이동합니다.",
				  icon: 'warning',
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: '확인',
				}).then((result) => {
				  if (result.isConfirmed) {
				    location.href=`/stu/stuApplyList?stuNo=${stuNo}`;
				  }
				})
				
				return;
			}
		},
		error: function() {
			alert('실패');
		}
	});

	//by수경 휴학사유 입력칸 공백 및 빈칸 방지
	const textArea = document.querySelector('textarea').value;
	//alert(textArea);

	if(textArea.replace(/\s| /gi, "").length == 0){
		
		$().ready(function () {
            Swal.fire({
                icon: 'warning',
                title: '휴학 사유는 필수 입력사항',
                text: '휴학 사유를 작성하여 주십시오.',
             });
	    });
		return;
	}
	
	//by수경 모든 정보가 입력되었을 때 휴학 유의사항 모달창
	//모달창 소스
	const modal = new bootstrap.Modal('#takeOffUnivModal');
	//모달 보여주기
	modal.show();

}

//by수경 휴학신청 form태그 실행을 위한 모달창 실행
function applyResult(){
	//동의합니다 체크박스
	const agreeCheckBox= document.querySelector('#agreeCheckBox');
	//동의합니다 체크박스에 체크표시가 되어 있다면
	const isChecked = agreeCheckBox.checked;
	
	//체크되어 있는것이 true라면 ajax실행
	if(isChecked){
		
		const stuNo = document.querySelector('#stuNo').value;
		const fromColl = document.querySelector('#collNo').value;
		const fromDept = document.querySelector('#deptNo').value;
		const applyReason = document.querySelector('#applyReason').value;
		const applyCode = document.querySelector('#applyCode').value;
			
		$.ajax({
			url: '/stu/applyTakeOffUnivAjax', //요청경로
			type: 'post',
			data: {
				'stuNo': stuNo, 'fromColl': fromColl,
				'fromDept': fromDept, 'applyReason': applyReason,
				'applyCode': applyCode
			}, //필요한 데이터
	
			success: function(result) {
				Swal.fire({
				  title: '휴학신청 완료',
				  text: "휴학신청이 완료 되었습니다.",
				  icon: 'success',
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: '확인'
				}).then((result) => {
				  if (result.isConfirmed) {
					nextPage();
				  }
				});
			},
	
			error: function() {
				alert('실패');
			}
		});
	}
	//by수경 만약 동의합니다에 체크하지 않았다면 alert창 실행
	else{
		Swal.fire({
		  title: '동의버튼 클릭',
		   text: '휴학 유의사항을 확인하시고 동의버튼을 클릭하여 주십시오.',
		  icon: 'warning',
		  confirmButtonColor: '#3085d6',
		  confirmButtonText: '확인'
		}).then((result) => {
		  if (result.isConfirmed) {
			//휴학 유의사항 모달창 다시 뜨도록 설정
			//모달창 소스
			const modal = new bootstrap.Modal('#takeOffUnivModal');
			//모달 보여주기
			modal.show();
			return;
		  }
		});
	}	
}
//by수경 신청현황을 보여주는 페이지로 이동
function nextPage(){
	
	const stuNo = document.querySelector('#stuNo').value;
	
	location.href=`/stu/stuApplyList?stuNo=${stuNo}`;
}


