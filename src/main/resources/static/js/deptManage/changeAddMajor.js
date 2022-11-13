//by수경 학생의 전과신청 및 복수전공 신청 페이지
//승인상태 클릭 시 모달창으로 해당 학생의 신청 시 작성한 내역 보여줌

//by수경 승인상태 클릭 시 전과 신청서 보여주기
function showChangeMajorApply(stuNo){
	$.ajax({
	   url: '/emp/showChangeMajorApplyAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo}, //필요한 데이터
	    success: function(result) {
	      	//학생의 데이터 
			document.querySelector('#stuInfoModal_status').innerText = result.studentVO.stuStatus;
			document.querySelector('#stuInfoModal_name').innerText = result.studentVO.memberVO.memName;
			document.querySelector('#stuInfoModal_no').innerText = result.stuNo;
			document.querySelector('#stuInfoModal_birth').innerText = result.studentVO.memberVO.memBirth;
			document.querySelector('#stuInfoModal_tell').innerText = result.studentVO.memberVO.memTell;
			document.querySelector('#stuInfoModal_gender').innerText = result.studentVO.memberVO.memGender;
			document.querySelector('#stuInfoModal_addr').innerText = result.studentVO.memberVO.memAddr;
			document.querySelector('#stuInfoModal_coll').innerText = result.studentVO.collNo;
			document.querySelector('#stuInfoModal_dept').innerText = result.studentVO.deptNo;
			document.querySelector('#stuInfoModal_fromColl').innerText = result.fromColl;
			document.querySelector('#stuInfoModal_fromDept').innerText = result.fromDept;
			document.querySelector('#stuInfoModal_grade').innerText = result.stuYear;
			document.querySelector('#stuInfoModal_doubleDept').innerText = result.studentVO.doubleNo;
			document.querySelector('#stuInfoModal_changeColl').innerText = result.toColl;
			document.querySelector('#stuInfoModal_changeDept').innerText = result.toDept;
			document.querySelector('#stuInfoModal_reason').innerText = result.applyReason;
	    },
	    error: function(){
	       alert('실패');
	    }
	});

}
//by수경 승인상태 클릭 시 복수전공 신청서 보여주기
function showDoubleMajorApply(stuNo){
	
	$.ajax({
	   url: '/emp/showDoubleMajorAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo}, //필요한 데이터
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

