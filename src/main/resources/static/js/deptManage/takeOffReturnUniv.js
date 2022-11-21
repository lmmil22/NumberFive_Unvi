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

//by수경 휴학 승인대기/승인완료 클릭 시 라디오 값 변경(단일 변경)
function changeTakeOffStatus(applyNo, processStatus, stuNo){
	$().ready(function () {
        Swal.fire({
            title: '승인하기',
            text: "신청내역을 승인하시겠습니까?",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '승인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
				$.ajax({
				   url: '/emp/changeTakeOffStatusAjax', //요청경로
				    type: 'post',
				    data:{'applyNo':applyNo, 'processStatus':processStatus
				    	 ,'stuNo':stuNo}, //필요한 데이터
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
            //라디오 박스 클릭하고 취소했을 때 기존값으로 돌아가도록 구현
            else{
				event.preventDefault();
			}
            
        })
     });

}
//by수경 복학 승인대기/승인완료 클릭 시 라디오 값 변경(단일 변경)
function changeComebackStatus(applyNo, processStatus, stuNo){
	
	const result = confirm('신청내역을 승인하시겠습니까?');
	
	if(result){
		
		$.ajax({
		   url: '/emp/changeComebackStatusAjax', //요청경로
		    type: 'post',
		    data:{'applyNo':applyNo, 'processStatus':processStatus
		    	 ,'stuNo':stuNo}, //필요한 데이터
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
	
	//라디오 박스 클릭하고 취소했을 때 기존값으로 돌아가도록 구현
	else{
		event.preventDefault();
	}
	
	
}

//휴학신청 테이블 제목줄 체크박스
const checkAll2 = document.querySelector('#allcheck2');

//by수경 휴학 체크박스 선택
checkAll2.addEventListener('click',function(){
	//제목줄 체크박스가 체크되어 있거나 해제되어 있는지 여부
	const isChecked = checkAll2.checked;
	
	//제목줄 제외 나머지 체크박스
	const checkBoxes = document.querySelectorAll('.check2');
	
	//만약 제목줄 체크박스에 체크표시가 되어 있다면
	if(isChecked){
		//모든 체크박스에 체크표시를 하겠다.
		for(const checkBox of checkBoxes){
			checkBox.checked =true;
		}
	}
	//제목줄 체크박스에 체크표시가 없다면
	else{
		//모든 체크박스에 체크표시 없애겠다.
		for(const checkBox of checkBoxes){
			checkBox.checked = false;			
		}
	}
	
});

//by수경 휴학신청 일괄승인 
function takeOffAllAccept(){
	//모달창 if/else 구분을 위한 임의 데이터
	document.querySelector('#resultAllModal_btn').dataset.flag = 1;
	
	//form태그 가져오기
	const takeOffForm = document.querySelector('#takeOffForm');
	
	//체크박스에서 체크 표시된 부분 가져오기
	const checkedBoxes = document.querySelectorAll('.check2:checked');
	
		if(checkedBoxes.length == 0){
			$().ready(function () {
	                Swal.fire({
	                    icon: 'warning',
	                    title: '선택한 내역이 없습니다.',
	                    text: '확인 후 다시 시도하여 주시길 바랍니다.',
		             });
		        });
				return;
		}
	
	//여러 체크박스가 선택되었다면
	//applyNo 데이터 가져오기
	let applyNos = '';
		for(const checkedBox of checkedBoxes){
			//체크박스들의 applyNo value값을 가져온다.
			applyNos = applyNos + checkedBox.value + ',';
		}
	
	//stuNo데이터 가져오기
	let stuNos = '';	
		for(const checkedBox of checkedBoxes){
			//체크박스들의 stuNo dataset값 가져온다
			stuNos = stuNos + checkedBox.dataset.stuNo+ ',';
		}
	
	//applyNo를 담을 applyNos를 input 히든으로 데이터 담아간다.
	takeOffForm.querySelector('#takeOffInput1').value = applyNos;
	//stuNo를 담을 stuNos를 input 히든으로 데이터 담아간다.
	takeOffForm.querySelector('#takeOffInput2').value = stuNos;
		
	//모달창 소스
	const modal = new bootstrap.Modal('#resultAlltModal');
	//모달 보여주기
	modal.show();
	
	
}


//복학신청 테이블 제목줄 체크박스
const checkAll1 = document.querySelector('#allcheck1');

//by수경 복학 체크박스 선택
checkAll1.addEventListener('click',function(){
	//제목줄 체크박스가 체크되어 있거나 해제되어 있는지 여부
	const isChecked = checkAll1.checked;
	
	//제목줄 제외 나머지 체크박스
	const checkBoxes = document.querySelectorAll('.check1');
	
	//만약 제목줄 체크박스에 체크표시가 되어 있다면
	if(isChecked){
		//모든 체크박스에 체크표시를 하겠다.
		for(const checkBox of checkBoxes){
			checkBox.checked =true;
		}
	}
	//제목줄 체크박스에 체크표시가 없다면
	else{
		//모든 체크박스에 체크표시 없애겠다.
		for(const checkBox of checkBoxes){
			checkBox.checked = false;			
		}
	}
	
});

//by수경 복학신청 일괄승인 
function comebackAllAccept(){
	//모달창 if/else 구분을 위한 임의 데이터
	document.querySelector('#resultAllModal_btn').dataset.flag = 2;
	
	//form태그 가져오기
	const comebackForm = document.querySelector('#comebackForm');
	
	//체크박스에서 체크 표시된 부분 가져오기
	const checkedBoxes = document.querySelectorAll('.check1:checked');
	
	if(checkedBoxes.length == 0){
		
		$().ready(function () {
            Swal.fire({
                icon: 'warning',
                title: '선택한 내역이 없습니다.',
                text: '확인 후 다시 시도하여 주시길 바랍니다.',
             });
		 });
		return;
	
	}
	
	//여러 체크박스가 선택되었다면
	//applyNo데이터 가져오기
	let applyNos = '';

		for(const checkedBox of checkedBoxes){
			//체크박스들의 applyNo value값을 가져온다.
			applyNos = applyNos + checkedBox.value + ',';
		}	
	//stuNo데이터 가져오기	
	let stuNos = '';

		for(const checkedBox of checkedBoxes){
			//체크박스들의 stuNo dataset값 가져온다
			stuNos = stuNos + checkedBox.dataset.stuNo+ ',';
		}
	
	//applyNo를 담을 applyNo를 input 히든으로 데이터 담아간다.
	comebackForm.querySelector('#comebackInput1').value = applyNos;
	//stuNo를 담을 stuNos를 input 히든으로 데이터 담아간다.
	comebackForm.querySelector('#comebackInput2').value = stuNos;
	
	//모달창 소스
	const modal = new bootstrap.Modal('#resultAlltModal');
	//모달 보여주기
	modal.show();
	
}

//by수경 클릭 시 form태그 실행
function formSubmit(){
	
	//모달창 if/else 구분을 위한 임의 데이터
	const flag = document.querySelector('#resultAllModal_btn').dataset.flag;
	
	if(flag == 1){
		//휴학 일괄신청 form태그를 실행시킨다.
		takeOffForm.submit();
	}
	else{
		//복학 일괄신청 form태그를 실행시킨다.
		comebackForm.submit();
	}
}


//by수경 복학, 휴학 조건 검색에서 search버튼 클릭 시 클릭 시 실행될 form태그
function search(){
	//조건 검색창이 두개이기 때문에 각각 form설정하면 하나 실행 시 다른 하나는 리셋되기 때문에 모두 form에 감싼다
	
	//복학 조건 검색창에서 데이터 검색 시
	document.querySelector('#search_takeOffUniv_fromDate').value = document.querySelector('#takeOffUniv_fromDate').value;
	document.querySelector('#search_takeOffUniv_toDate').value = document.querySelector('#takeOffUniv_toDate').value;
	document.querySelector('#search_takeOffUniv_status').value = document.querySelector('.takeOffUniv_status:checked').value;
	
	//휴학 조건 검색창에서 데이터 검색 시
	document.querySelector('#search_comeback_fromDate').value = document.querySelector('#comeback_fromDate').value;
	document.querySelector('#search_comeback_toDate').value = document.querySelector('#comeback_toDate').value;
	document.querySelector('#search_comeback_status').value = document.querySelector('.comeback_status:checked').value;
	
	//form태그를 실행한다.
	document.querySelector('#searchForm').submit();
}

//by수경 카카오톡 메세지 전송
function sendKakao(){
		
	Kakao.Share.sendCustom({
	  templateId: 86153,
	  templateArgs: {
	    title: '제목 영역입니다.',
	    description: '설명 영역입니다.',
	  },
	
	});

}