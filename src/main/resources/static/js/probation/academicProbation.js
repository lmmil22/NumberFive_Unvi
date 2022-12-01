
//by수경 검색조건 검색 후 새로고침 했을 때 전공대학과 전공학과 불러오기
const collNo = document.querySelector('#coll').value;

if(collNo == 'COLL_001' || collNo == 'COLL_002' || collNo == 'COLL_003' || collNo == 'COLL_004'){
	//소속대학 소속학과 조회
	changeColl();
}

//by수경 학생을 조회하기 위한 소속대학 소속학과 조회
function changeColl(){
	
	const collNo = document.querySelector('#coll').value;
	
	$.ajax({
	   url: '/emp/deptListAjax', //요청경로
	    type: 'post',
	    data: {'collNo':collNo}, //필요한 데이터
	    success: function(result) {
	   		//by수경 학과 select박스 선택
	   		const deptSelect = document.querySelector('.deptList');
	   		//by수경 학과 select박스 안에 option 태그 선택
	   		const optionTags = deptSelect.querySelectorAll('option');
			
			//기존 학과 목록을 지워준다
			$('.deptList').empty(); 
			
			//새로 그려주기
			let str ='';
		
			for(const dept of result){ 
				str += `<option value="${dept.deptNo}">${dept.deptName}</option>`;
			}			

			deptSelect.insertAdjacentHTML('beforeend',str); 
		
	    },
	    error: function(){
	       alert('실패');
	    }
	});

}

//by수경 학생 학번 클릭 시 나타날 학사경고 창 데이터
function stuInfo(stuNo){
	$.ajax({
	   url: '/emp/probationStuInfoAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo}, //필요한 데이터
	    success: function(result) {
			//학사경고 사유/날짜 부분의 데이터를 지운다
			$('.probationTb').empty();
		
			document.querySelector('#probationModal_name').innerText = result.studentVO.memberVO.memName;
		    document.querySelector('#probationModal_birth').innerText = result.studentVO.memberVO.memBirth;
		    document.querySelector('#probationModal_tell').innerText = result.studentVO.memberVO.memTell;
		    document.querySelector('#probationModal_addr').innerText = result.studentVO.memberVO.memAddr;
		    document.querySelector('#probationModal_no').innerText = result.studentVO.stuNo;
		    document.querySelector('#probationModal_grade').innerText = result.studentVO.stuYear;
		    document.querySelector('#probationModal_status').innerText = result.studentVO.stuStatus;
		    document.querySelector('#probationModal_coll').innerText = result.studentVO.collNo;
		    document.querySelector('#probationModal_dept').innerText = result.studentVO.deptNo;
		    document.querySelector('#probationModal_memNo').value = result.studentVO.memNo;
		    //이메일 보내기를 위하여 히든으로 가져갈 데이터 
		    document.querySelector('#probationModal_memEmail').value = result.studentVO.memberVO.memEmail;
			
	     	let str ='';
	     	str += '<tr>';
	     	str += '<td colspan="6">누적 경고 내역</td>';
	     	str += '</tr>';
	     	
	     	//날짜와 사유를 for문으로 뽑아낸다.
	     	for(const probation of result.probationList){
		     	str += '<tr>';
		     	str += '<td colspan="2">';
		     	str += `<div>${probation.probDate}</div>`;
		     	str += '</td>';
		     	str += '<td colspan="4">';
		     	str += `<div>${probation.probReason}</div>`;
		     	str += '</td>';
		     	str += '</tr>';
			}
			//메일 보내기 체크박스 그려준다
			str += `<tr><td colspan="6"><span><input type="checkbox" id = "probationMailChkBox"></span> 학사경고 알림 메일 발송하기<td></tr>`
			//해당 클래스 뒤에 데이터를 넣어준다.
			$('.probationTb').append(str);
			
			//승인하기 버튼을 이미 제적인 학생에게는 안보이도록 구현
			//기존 버튼부분을 지워준다
			$('.probation_div').children().eq(0).remove();
			let str1 = '';
			
			//승인 버튼 새로 그리기
			//만약 학생의 afterStatus가 STU_OUT이라면 display=none으로 안보이도록 구현
			if(result.studentVO.statusInfoVO.afterStatus == 'STU_OUT'){
				str1 += '<button type="button" class="btn btn-primary btn-sm" data-bs-dismiss="modal" id="statusInfo_afterStatus" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: 3rem; --bs-btn-font-size: 1rem; display:none;" onclick="acceptProbation();">승인하기</button>';
			}
			//만약 학생의 afterStatus가 NULL이라면
			else{
				str1 += '<button type="button" class="btn btn-primary btn-sm" data-bs-dismiss="modal" id="statusInfo_afterStatus" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: 3rem; --bs-btn-font-size: 1rem;" onclick="acceptProbation();">승인하기</button>';
			}
			//modal-footer클래스 앞에 만들겠다.
			$('.probation_div').prepend(str1);

	    },
	    error: function(){
	       alert('실패');
	    }
	});
}

//by수경 제적처리 버튼 클릭 시 나타날 제적 창 학생 데이터
function stuOut(stuNo){
	
	$.ajax({
	   url: '/emp/stuOutStuInfoAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo}, //필요한 데이터
	    success: function(result) {
			//학사경고 사유/날짜 부분의 데이터를 지운다
			$('.stuOutTb').empty();
		
			document.querySelector('#stuOut_name').innerText = result.studentVO.memberVO.memName;
		    document.querySelector('#stuOut_birth').innerText = result.studentVO.memberVO.memBirth;
		    document.querySelector('#stuOut_tell').innerText = result.studentVO.memberVO.memTell;
		    document.querySelector('#stuOut_addr').innerText = result.studentVO.memberVO.memAddr;
		    document.querySelector('#stuOut_no').innerText = result.studentVO.stuNo;
		    document.querySelector('#stuOut_grade').innerText = result.studentVO.stuYear;
		    document.querySelector('#stuOut_status').innerText = result.studentVO.stuStatus;
		    document.querySelector('#stuOut_coll').innerText = result.studentVO.collNo;
		    document.querySelector('#stuOut_dept').innerText = result.studentVO.deptNo;
	     
	     	let str = '';
	     	str += '<tr>';
	     	str += '<td colspan="6">누적 경고 내역</td>';
	     	str += '</tr>';
	     	
	     	//날짜와 사유를 for문으로 뽑아낸다.
	     	for(const probation of result.probationList){
		     	str += '<tr>';
		     	str += '<td colspan="2">';
		     	str += `<div>${probation.probDate}</div>`;
		     	str += '</td>';
		     	str += '<td colspan="4">';
		     	str += `<div>${probation.probReason}</div>`;
		     	str += '</td>';
		     	str += '</tr>';
			}
			
			//해당 클래스 뒤에 데이터를 넣어준다.
			$('.stuOutTb').append(str);

	    },
	    error: function(){
	       alert('실패');
	    }
	});
}

//by수경 학사경고 승인하기 버튼 클릭
function acceptProbation(){

	//학사경고 쿼리에 필요한 데이터 보내기
	const stuNo = document.querySelector('#probationModal_no').innerText;
	const probReason = document.querySelector('#probReason').value;
	const semNo = document.querySelector('#semNo').value;
	const memNo = document.querySelector('#probationModal_memNo').value;
	

	Swal.fire({
		  title: '학사경고',
		  text: "승인하시겠습니까?",
		  icon: 'question',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  confirmButtonText: '승인',
		  cancelButtonText: '취소'
	}).then((result) => {
		  if (result.isConfirmed) {
			$.ajax({
			   url: '/emp/acceptProbationAjax', //요청경로
			    type: 'post',
			    data:{'stuNo':stuNo,'probReason':probReason,
			    'semNo':semNo, 'memNo':memNo}, //필요한 데이터
			    success: function(result) {	
		            Swal.fire({
					  title: '학사경고 승인',
					  text: "학사경고가 승인되었습니다.",
					  icon: 'success',
					  confirmButtonColor: '#3085d6',
					  confirmButtonText: '확인'
					}).then((result) => {
					  if (result.isConfirmed) {
						//학사 경고 메일 보내기 함수 실행
						sendMail();
					  }
					})
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

//학생에게 학사경고 안내 메일 전송하기
function sendMail(){
	
	//메일보내기 체크박스 버튼이 체크 되어 있다면 메일 발송되도록 구현
	const probationMailChkBox = document.querySelector('#probationMailChkBox');
	const isChecked = probationMailChkBox.checked;
	
	//메일전송 시 컨텐츠 영역에 넣을 데이터 
	const memName = document.querySelector('#probationModal_name').innerText;
	const memEmail = document.querySelector('#probationModal_memEmail').value;
	const probReason = document.querySelector('#probReason').value;
	
	if(isChecked){
		
		//메일 보내기 로딩 화면				
		let timerInterval
		Swal.fire({
		  title: '처리중입니다.',
		  html: '메일 전송 완료까지 <b></b> 남았습니다.',
		  timer: 3030,
		  timerProgressBar: true,
		  didOpen: () => {
		    Swal.showLoading()
		    const b = Swal.getHtmlContainer().querySelector('b')
		    timerInterval = setInterval(() => {
		      b.textContent = Swal.getTimerLeft()
		    }, 100)
		  },
		  willClose: () => {
		    clearInterval(timerInterval)
		  }
		});		
		
		//ajax 시작
		$.ajax({
		   url: '/mail/sendProbationMailAjax', //요청경로
		    type: 'post',
		    data:{'memEmail':memEmail, 'memName':memName,
		    	  'probReason':probReason}, //필요한 데이터
		    	  
		    success: function(result) {
				Swal.fire({
				  title: '메일 전송 완료',
				  text: "메일 전송이 완료 되었습니다.",
				  icon: 'success',
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: '확인'
				}).then((result) => {
				  if (result.isConfirmed) {
					home();
				  }
				})
		    },
		    error: function(){
		      alert('실패');
		    }
		});
		
		return;
	}
	//체크박스에 체크가 되어 있지 않다면 다시 원래 페이지로 돌아간다.
	else{
		home();
	}

}
//수경 다시 학사경고 페이지로 이동
function home(){
	location.href = '/emp/probation';
}


//by수경 제적처리 버튼 클릭 시
function acceptStuOut(){
	
	const stuNo = document.querySelector('#stuOut_no').innerText;
	const stuOutReason = document.querySelector('#stuOutReason').value;
	
	Swal.fire({
	  title: '제적처리',
	  text: "승인하시겠습니까?",
	  icon: 'question',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  confirmButtonText: '승인',
	  cancelButtonText: '취소'
	}).then((result) => {
	  if (result.isConfirmed) {
		$.ajax({
		   url: '/emp/acceptStuOutAjax', //요청경로
		    type: 'post',
		    data:{'stuNo':stuNo,'stuOutReason':stuOutReason}, //필요한 데이터
		    success: function(result) {
				
				Swal.fire({
				  title: '제적처리 완료',
				  text: "해당 학생을 제적처리하였습니다. 카카오톡으로 발송하시겠습니까?",
				  icon: 'success',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: '확인',
				  cancelButtonText: '취소'
				}).then((result) => {
				  if (result.isConfirmed) {
				    sendKakao();
				   
				  }else{
					home();
				}
				})
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

//by수경 카카오톡 메시지 공유하기
function sendKakao(){
		
	Kakao.Share.sendCustom({
	  templateId: 86136,
	  templateArgs: {
	    title: '제목 영역입니다.',
	    description: '설명 영역입니다.',
	  },
	});
	 home();
}
