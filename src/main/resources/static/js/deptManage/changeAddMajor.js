//by수경 학생의 전과신청 및 복수전공 신청 페이지
//승인상태 클릭 시 모달창으로 해당 학생의 신청 시 작성한 내역 보여줌

//by수경 승인상태 클릭 시 전과 신청서 보여주기
function showChangeMajorApply(stuNo, applyNo, processStatus){
	$.ajax({
	   url: '/emp/showChangeMajorApplyAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo, 'applyNo':applyNo}, //필요한 데이터
	    success: function(result) {
	      	//학생의 데이터 
			document.querySelector('#changeMajorModal_status').innerText = result.studentVO.stuStatus;
			document.querySelector('#changeMajorModal_name').innerText = result.studentVO.memberVO.memName;
			alert(('/images/' + result.studentVO.memberVO.memImage));
			
			document.querySelector('#changeMajorModal_image').src = ('/images/' + result.studentVO.memberVO.memImage);
			
			//$(`#changeMajorModal_image`).attr({ src: ('/images/' + result.studentVO.memberVO.memImage) });
			
			document.querySelector('#changeMajorModal_no').innerText = result.stuNo;
			document.querySelector('#changeMajorModal_birth').innerText = result.studentVO.memberVO.memBirth;
			document.querySelector('#changeMajorModal_tell').innerText = result.studentVO.memberVO.memTell;
			document.querySelector('#changeMajorModal_addr').innerText = result.studentVO.memberVO.memAddr;
			document.querySelector('#changeMajorModal_dept').innerText = result.studentVO.deptNo;
			document.querySelector('#changeMajorModal_fromColl').innerText = result.fromColl;
			document.querySelector('#changeMajorModal_fromDept').innerText = result.fromDept;
			document.querySelector('#changeMajorModal_grade').innerText = result.stuYear;
			document.querySelector('#changeMajorModal_doubleDept').innerText = result.studentVO.doubleNo;
			document.querySelector('#changeMajorModal_changeColl').innerText = result.toColl;
			document.querySelector('#changeMajorModal_changeDept').innerText = result.toDept;
			document.querySelector('#changeMajorModal_reason').innerText = result.applyReason;
			document.querySelector('#changeMajorModal_apply').value = result.applyNo;
	    },
	    error: function(){
	       alert('실패');
	    }
	});
	
		//by수경 승인완료 버튼 클릭 시 해당 학생 신청서는 보이지만 승인버튼은 안보이도록 구현
	
		//기존 버튼부분을 지워준다
		$('.changeMajor_div').children().eq(0).remove();
		let str = '';
		
		//승인 버튼 새로 그리기
		//만약 학생의 processStatus가 accept이라면 display=none으로 안보이도록 구현
		if(processStatus == 'accept'){
			str += '<button type="button" class="btn btn-primary btn-sm" data-bs-dismiss="modal" id="resultAllModal_btn" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: 3rem; --bs-btn-font-size: 1rem; display:none;" onclick="acceptChangeMajor();">승인하기</button>';
		}
		//만약 학생의 processStatus가 waiting 이라면 버튼 보이도록
		else{
			str += '<button type="button" class="btn btn-primary btn-sm" data-bs-dismiss="modal" id="resultAllModal_btn" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: 3rem; --bs-btn-font-size: 1rem;" onclick="acceptChangeMajor();">승인하기</button>';
		}
		//modal-footer클래스 앞에 만들겠다.
		$('.changeMajor_div').prepend(str);
	

}
//by수경 승인상태 클릭 시 복수전공 신청서 보여주기
function showDoubleMajorApply(stuNo, applyNo,processStatus){
	
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
			//alert(('/images/' + result.studentVO.memberVO.memImage)); 회원등록시 삽입한 이미지 데이터 가져오기
			document.querySelector('#doubleMajorModal_image').src = ('/images/' + result.studentVO.memberVO.memImage);
			
			document.querySelector('#doubleMajorModal_addr').innerText = result.studentVO.memberVO.memAddr;
			document.querySelector('#doubleMajorModal_dept').innerText = result.studentVO.deptNo;
			document.querySelector('#doubleMajorModal_fromColl').innerText = result.fromColl;
			document.querySelector('#doubleMajorModal_fromDept').innerText = result.fromDept;
			document.querySelector('#doubleMajorModal_grade').innerText = result.stuYear;
			document.querySelector('#doubleMajorModal_doubleNo').innerText = result.studentVO.doubleNo;
			document.querySelector('#doubleMajorModal_doubleColl').innerText = result.doubleMajorColl;
			document.querySelector('#doubleMajorModal_doubleDept').innerText = result.doubleMajorDept;
			document.querySelector('#doubleMajorModal_reason').innerText = result.applyReason;
			document.querySelector('#doubleMajorModal_apply').value = result.applyNo;
	     
	    },
	    error: function(){
	       alert('실패');
	    }
	});
	
		//by수경 승인완료 버튼 클릭 시 해당 학생 신청서는 보이지만 승인버튼은 안보이도록 구현
	
		//기존 버튼부분을 지워준다
		$('.doubleMajor_div').children().eq(0).remove();
		let str = '';
		
		//승인 버튼 새로 그리기
		//만약 학생의 processStatus가 accept이라면 display=none으로 안보이도록 구현
		if(processStatus == 'accept'){
			str += '<button type="button" class="btn btn-primary btn-sm" data-bs-dismiss="modal" id="resultAllModal_btn" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: 3rem; --bs-btn-font-size: 1rem; display:none;" onclick="acceptChangeMajor();">승인하기</button>';
		}
		//만약 학생의 processStatus가 waiting 이라면 버튼 보이도록
		else{
			str += '<button type="button" class="btn btn-primary btn-sm" data-bs-dismiss="modal" id="resultAllModal_btn" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: 3rem; --bs-btn-font-size: 1rem;" onclick="acceptChangeMajor();">승인하기</button>';
		}
		//modal-footer클래스 앞에 만들겠다.
		$('.doubleMajor_div').prepend(str);

	
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

	//체크박스에서 체크 표시된 부분 가져오기
	const checkedBoxes = document.querySelectorAll('.check1:checked');
	
		if(checkedBoxes.length == 0){
	
            Swal.fire({
                icon: 'warning',
                title: '선택한 내역이 없습니다.',
                text: '확인 후 다시 시도하여 주시길 바랍니다.',
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
	
	//승인완료 swal창 뜨고 일괄승인 ajax 실행한다 
	Swal.fire({
		  title: '일괄승인',
		  text: "일괄승인 하시겠습니까?",
		  icon: 'question',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  confirmButtonText: '승인',
		  cancelButtonText: '취소'
	}).then((result) => {
		  if (result.isConfirmed) {
			$.ajax({
			   url: '/emp/changeAllAccept', //요청경로
			    type: 'post',
			    data:{'applyNos':applyNos, 'stuNos':stuNos}, //필요한 데이터
			    success: function(result) {
					Swal.fire({
					  title: '일괄승인 완료',
					  text: "일괄승인이 완료 되었습니다. 카카오톡 메세지를 전송하시겠습니까?",
					  icon: 'success',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  confirmButtonText: '확인',
					  cancelButtonText: '취소'
					}).then((result) => {
					  if (result.isConfirmed) {
						sendKakao();
						return;
					  }
					  else{
						home();
					  }
					});
			    },
			    error: function(){
			       alert('실패');
			    }
			});
	  }else{
		home();
	   }
	});
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
		
	//승인완료 swal창 뜨고 formSubmit() 함수 실행
	Swal.fire({
		  title: '일괄승인',
		  text: "일괄승인 하시겠습니까?",
		  icon: 'question',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  confirmButtonText: '승인',
		  cancelButtonText: '취소'
	}).then((result) => {
		  if (result.isConfirmed) {
			$.ajax({
			   url: '/emp/doubleAllAccept', //요청경로
			    type: 'post',
			    data:{'applyNos':applyNos, 'stuNos':stuNos}, //필요한 데이터
			    success: function(result) {
			  		Swal.fire({
					  title: '일괄승인 완료',
					  text: "일괄승인이 완료 되었습니다. 카카오톡 메세지를 전송하시겠습니까?",
					  icon: 'success',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  confirmButtonText: '확인',
					  cancelButtonText: '취소'
					  
					}).then((result) => {
					  if (result.isConfirmed) {
						sendKakao();
						return;
					  }
					  else{
						home();
					  }
					});
			    },
			    error: function(){
			       alert('실패');
			    }
			});
	  }else{
		home();
	   }
	});
}

//by수경 전과신청 관리자 단일 승인
function acceptChangeMajor(){
	
	const applyNo = document.querySelector('#changeMajorModal_apply').value;
	const stuNo = document.querySelector('#changeMajorModal_no').innerText;
	
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
				   url: '/emp/acceptChangeMajorAjax', //요청경로
				    type: 'post',
				    data:{'applyNo':applyNo,'stuNo':stuNo}, //필요한 데이터
				    success: function(result) {
						Swal.fire({
						  title: '승인 완료',
						  text: "전과 신청이 승인되었습니다.",
						  icon: 'success',
						  confirmButtonColor: '#3085d6',
						  confirmButtonText: '확인'
						}).then((result) => {
						  if (result.isConfirmed) {
							checkChangeMajorChkBox();
							return;
						  }
						})
				    },
				    error: function(){
				       alert('실패');
				    }
				});
				return;
            }else{
				home();
			}
        });
}

//by수경 전과신청 모달창에서 카카오톡 전송 체크박스에 체크 시 실행될 함수
function checkChangeMajorChkBox(){
	
	//전과신청 모달창에서 카카오톡 알림 보내기 체크박스에 체크가 되어 있는지 유무
	const changeMajorChkBox = document.querySelector('#changeMajor_ChkBox');
	const isChecked = changeMajorChkBox.checked;
	
	if(isChecked){
		sendKakao();
		return;
	}
	//체크박스에 체크가 없다면 다시 원래 페이지로 이동
	else{
		home();
	}
	
}

//by수경 복수전공신청 모달창에서 카카오톡 전송 체크박스에 체크 시 실행될 함수
function checkDoubleMajorChkBox(){
	
	//전과신청 모달창에서 카카오톡 알림 보내기 체크박스에 체크가 되어 있는지 유무
	const doubleMajorChkBox = document.querySelector('#doubleMajor_ChkBox');
	const isChecked = doubleMajorChkBox.checked;
	
	if(isChecked){
		sendKakao();
		return;
	}
	//체크박스에 체크가 없다면 다시 원래 페이지로 이동
	else{
		home();
	}
	
}

//by수경 복수전공신청 관리자 단일 승인
function acceptDoubleMajor(){
	const applyNo = document.querySelector('#doubleMajorModal_apply').value;
	const stuNo = document.querySelector('#doubleMajorModal_no').innerText;

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
				   url: '/emp/acceptDoubleMajorAjax', //요청경로
				    type: 'post',
				    data:{'applyNo':applyNo, 'stuNo':stuNo}, //필요한 데이터
				    success: function(result) {
					
						 Swal.fire({
							  title: '승인 완료',
							  text: "복수전공 신청이 승인되었습니다.",
							  icon: 'success',
							  confirmButtonColor: '#3085d6',
							  confirmButtonText: '확인'
							}).then((result) => {
							  if (result.isConfirmed) {
								checkDoubleMajorChkBox();
								return;
							  }
							})
				    },
				    error: function(){
				       alert('실패');
				    }
				});
					return;
			} else{
				home();
		    }
 	});

}
//by수경 다시 전과/복수전공관리자 페이지로 이동(단일승인)
function home(){
	location.href = '/emp/changeAddMajor';
}


//by수경 전과, 복수전공 조건 검색에서 search버튼 클릭 시 클릭 시 실행될 form태그
function search(){
	//조건 검색창이 두개이기 때문에 각각 form설정하면 하나 실행 시 다른 하나는 리셋되기 때문에 모두 form에 감싼다
	
	//전과 조건 검색창에서 데이터 검색 시
	document.querySelector('#search_changeMajor_fromDate').value = document.querySelector('#changeMajor_fromDate').value;
	document.querySelector('#search_changeMajor_toDate').value = document.querySelector('#changeMajor_toDate').value;
	document.querySelector('#search_changeMajor_status').value = document.querySelector('.changeMajor_status:checked').value;
	
	//복수전공 조건 검색창에서 데이터 검색 시
	document.querySelector('#search_doubleMajor_fromDate').value = document.querySelector('#doubleMajor_fromDate').value;
	document.querySelector('#search_doubleMajor_toDate').value = document.querySelector('#doubleMajor_toDate').value;
	document.querySelector('#search_doubleMajor_status').value = document.querySelector('.doubleMajor_status:checked').value;
	
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
	
	home();

}

