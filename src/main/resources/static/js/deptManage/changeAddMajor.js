//by수경 학생의 전과신청 및 복수전공 신청 페이지
//승인상태 클릭 시 모달창으로 해당 학생의 신청 시 작성한 내역 보여줌

//by수경 승인상태 클릭 시 전과 신청서 보여주기
function showChangeMajorApply(stuNo, applyNo){
	$.ajax({
	   url: '/emp/showChangeMajorApplyAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo, 'applyNo':applyNo}, //필요한 데이터
	    success: function(result) {
	      	//학생의 데이터 
			document.querySelector('#changeMajorModal_status').innerText = result.studentVO.stuStatus;
			document.querySelector('#changeMajorModal_name').innerText = result.studentVO.memberVO.memName;
			document.querySelector('#changeMajorModal_no').innerText = result.stuNo;
			document.querySelector('#changeMajorModal_birth').innerText = result.studentVO.memberVO.memBirth;
			document.querySelector('#changeMajorModal_tell').innerText = result.studentVO.memberVO.memTell;
			document.querySelector('#changeMajorModal_gender').innerText = result.studentVO.memberVO.memGender;
			document.querySelector('#changeMajorModal_addr').innerText = result.studentVO.memberVO.memAddr;
			document.querySelector('#changeMajorModal_coll').innerText = result.studentVO.collNo;
			document.querySelector('#changeMajorModal_dept').innerText = result.studentVO.deptNo;
			document.querySelector('#changeMajorModal_fromColl').innerText = result.fromColl;
			document.querySelector('#changeMajorModal_fromDept').innerText = result.fromDept;
			document.querySelector('#changeMajorModal_grade').innerText = result.stuYear;
			document.querySelector('#changeMajorModal_doubleDept').innerText = result.studentVO.doubleNo;
			document.querySelector('#changeMajorModal_changeColl').innerText = result.toColl;
			document.querySelector('#changeMajorModal_changeDept').innerText = result.toDept;
			document.querySelector('#changeMajorModal_reason').innerText = result.applyReason;
	    },
	    error: function(){
	       alert('실패');
	    }
	});

}
//by수경 승인상태 클릭 시 복수전공 신청서 보여주기
function showDoubleMajorApply(stuNo, applyNo){
	
	$.ajax({
	   url: '/emp/showDoubleMajorAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo , 'applyNo':applyNo}, //필요한 데이터
	    success: function(result) {
			//학생의 데이터 
			document.querySelector('#doubleMajorModal_status').innerText = result.studentVO.stuStatus;
			document.querySelector('#doubleMajorModal_name').innerText = result.studentVO.memberVO.memName;
			document.querySelector('#doubleMajorModal_no').innerText = result.stuNo;
			document.querySelector('#doubleMajorModal_birth').innerText = result.studentVO.memberVO.memBirth;
			document.querySelector('#doubleMajorModal_tell').innerText = result.studentVO.memberVO.memTell;
			document.querySelector('#doubleMajorModal_gender').innerText = result.studentVO.memberVO.memGender;
			document.querySelector('#doubleMajorModal_addr').innerText = result.studentVO.memberVO.memAddr;
			document.querySelector('#doubleMajorModal_coll').innerText = result.studentVO.collNo;
			document.querySelector('#doubleMajorModal_dept').innerText = result.studentVO.deptNo;
			document.querySelector('#doubleMajorModal_fromColl').innerText = result.fromColl;
			document.querySelector('#doubleMajorModal_fromDept').innerText = result.fromDept;
			document.querySelector('#doubleMajorModal_grade').innerText = result.stuYear;
			document.querySelector('#doubleMajorModal_doubleNo').innerText = result.studentVO.doubleNo;
			document.querySelector('#doubleMajorModal_doubleColl').innerText = result.doubleMajorColl;
			document.querySelector('#doubleMajorModal_doubleDept').innerText = result.doubleMajorDept;
			document.querySelector('#doubleMajorModal_reason').innerText = result.applyReason;
	     
	    },
	    error: function(){
	       alert('실패');
	    }
	});

	
}

//전과신청 테이블 제목줄 체크박스
const chkAll1 = document.querySelector('#allChk1');

//by수경 전과 체크박스 선택
chkAll1.addEventListener('click',function(){
	//제목줄 체크박스가 체크되어 있거나 해제되어 있는지 여부
	const isChecked = chkAll1.checked;
	
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

//by수경 전과신청 일괄승인 
function changeAllAccept(){
	//모달창 if/else 구분을 위한 임의 데이터
	document.querySelector('#resultAllModal_btn').dataset.flag = 1;
	
	//form태그 가져오기
	const changeForm = document.querySelector('#changeForm');
	
	//체크박스에서 체크 표시된 부분 가져오기
	const checkedBoxes = document.querySelectorAll('.check1:checked');
	
		if(checkedBoxes.length == 0){
			alert('선택한 내역이 없습니다. \n확인 후 다시 시도하여 주시길 바랍니다.');
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
	changeForm.querySelector('#changeInput1').value = applyNos;
	//stuNo를 담을 stuNos를 input 히든으로 데이터 담아간다.
	changeForm.querySelector('#changeInput2').value = stuNos;
		
	//모달창 소스
	const modal = new bootstrap.Modal('#resultAlltModal');
	//모달 보여주기
	modal.show();
}

//복수전공신청 테이블 제목줄 체크박스
const chkAll2 = document.querySelector('#allChk2');

//by수경 복수전공 체크박스 선택
chkAll2.addEventListener('click',function(){
	//제목줄 체크박스가 체크되어 있거나 해제되어 있는지 여부
	const isChecked = chkAll2.checked;
	
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

//by수경 복수전공 신청 일괄승인 
function doubleAllAccept(){
	//모달창 if/else 구분을 위한 임의 데이터
	document.querySelector('#resultAllModal_btn').dataset.flag = 2;
	
	//form태그 가져오기
	const doubleForm = document.querySelector('#doubleForm');
	
	//체크박스에서 체크 표시된 부분 가져오기
	const checkedBoxes = document.querySelectorAll('.check2:checked');
	
		if(checkedBoxes.length == 0){
			alert('선택한 내역이 없습니다. \n확인 후 다시 시도하여 주시길 바랍니다.');
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
	doubleForm.querySelector('#doubleInput1').value = applyNos;
	//stuNo를 담을 stuNos를 input 히든으로 데이터 담아간다.
	doubleForm.querySelector('#doubleInput2').value = stuNos;
		
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
		//전과 일괄신청 form태그를 실행시킨다.
		changeForm.submit();
	}
	else{
		//복수전공 일괄신청 form태그를 실행시킨다.
		doubleForm.submit();
	}
}

//by수경 전과신청 관리자 단일 승인
function acceptChangeMajor(){
	
	const applyNo = document.querySelector('#changeApplyNo').value;
	const stuNo = document.querySelector('#changeApplyNo').dataset.stuNo;
	
	$.ajax({
	   url: '/emp/acceptChangeMajorAjax', //요청경로
	    type: 'post',
	    data:{'applyNo':applyNo,'stuNo':stuNo}, //필요한 데이터
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
//by수경 복수전공신청 관리자 단일 승인
function acceptDoubleMajor(){
	const applyNo = document.querySelector('#doubleApply').value;
	const stuNo = document.querySelector('#doubleApply').dataset.stuNo;
	alert(stuNo);
	
	$.ajax({
	   url: '/emp/acceptDoubleMajorAjax', //요청경로
	    type: 'post',
	    data:{'applyNo':applyNo, 'stuNo':stuNo}, //필요한 데이터
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
