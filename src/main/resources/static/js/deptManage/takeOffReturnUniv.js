//휴학, 복학 신청 현황 페이지
//학번 클릭 시 해당 학생의 정보 확인 가능
//승인대기 승인신청 라디오로 변경, 체크박스로 일괄승인 구현

//by수경 학번 클릭 시 해당 학생 정보 확인
function stuInfo(stuNo){
	
	$.ajax({
	   url: '/emp/stuInfoAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo}, //필요한 데이터
	    success: function(result) {
			//학생의 데이터 
			document.querySelector('#stuInfoModal_name').innerText = result.memberVO.memName;
			document.querySelector('#stuInfoModal_no').innerText = result.stuNo;
			document.querySelector('#stuInfoModal_grade').innerText = result.stuYear;
			document.querySelector('#stuInfoModal_coll').innerText = result.collVO.collName;
			document.querySelector('#stuInfoModal_dept').innerText = result.deptVO.deptName;
			document.querySelector('#stuInfoModal_doubleDept').innerText = result.doubleNo;
			document.querySelector('#stuInfoModal_gender').innerText = result.memberVO.memGender;
			document.querySelector('#stuInfoModal_birth').innerText = result.memberVO.memBirth;
			document.querySelector('#stuInfoModal_status').innerText = result.stuStatus;
		
		},
		
	    error: function(){
	       alert('실패');
	    }
	});

}


//by수경 휴학 승인대기/승인완료 클릭 시 라디오 값 변경
function changeTakeOffStatus(applyNo, processStatus){
	
	const result = confirm('신청내역을 승인하시겠습니까?');
	
	if(result){
		
		$.ajax({
		   url: '/emp/changeStatusAjax', //요청경로
		    type: 'post',
		    data:{'applyNo':applyNo, 'processStatus':processStatus}, //필요한 데이터
		    success: function(result) {
			     //모달창 소스
				const modal = new bootstrap.Modal('#resultModal');
				//모달 보여주기
				modal.show();
		    },
		    error: function(){
		       alert('실패');
		    }
		});
	}
	
}


