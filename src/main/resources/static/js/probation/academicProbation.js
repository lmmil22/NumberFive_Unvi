
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
			
			$('.deptList').empty(); 
		
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
		
			document.querySelector('#probationModal_name').innerText = result.stuInfo.memberVO.memName;
		    document.querySelector('#probationModal_birth').innerText = result.stuInfo.memberVO.memBirth;
		    document.querySelector('#probationModal_tell').innerText = result.stuInfo.memberVO.memTell;
		    document.querySelector('#probationModal_addr').innerText = result.stuInfo.memberVO.memAddr;
		    document.querySelector('#probationModal_no').innerText = result.stuInfo.stuNo;
		    document.querySelector('#probationModal_grade').innerText = result.stuInfo.stuYear;
		    document.querySelector('#probationModal_status').innerText = result.stuInfo.stuStatus;
		    document.querySelector('#probationModal_coll').innerText = result.stuInfo.collNo;
		    document.querySelector('#probationModal_dept').innerText = result.stuInfo.deptNo;
		    document.querySelector('#probationModal_memNo').value = result.stuInfo.memNo;
	     
	     	let str ='';
	     	str += '<tr>';
	     	str += '<td colspan="6">누적 경고 내역</td>';
	     	str += '</tr>';
	     	
	     	//날짜와 사유를 for문으로 뽑아낸다.
	     	for(const probation of result.probationList){
		     	str += '<tr>';
		     	str += '<td colspan="2">';
		     	str += `<div id="probationModal_date">${probation.probDate}</div>`;
		     	str += '</td>';
		     	str += '<td colspan="4">';
		     	str += `<div id ="probationModal_reason">${probation.probReason}</div>`;
		     	str += '</td>';
		     	str += '</tr>';
			}
			//해당 클래스 뒤에 데이터를 넣어준다.
			$('.probationTb').append(str);

	    },
	    error: function(){
	       alert('실패');
	    }
	});
}

//by수경 제적처리 버튼 시 나타날 제적 창 학생 데이터
function stuOut(stuNo){
	$.ajax({
	   url: '/emp/stuOutStuInfoAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo}, //필요한 데이터
	    success: function(result) {
			//학사경고 사유/날짜 부분의 데이터를 지운다
			$('.stuOutTb').empty();
		
			document.querySelector('#stuOut_name').innerText = result.stuInfo.memberVO.memName;
		    document.querySelector('#stuOut_birth').innerText = result.stuInfo.memberVO.memBirth;
		    document.querySelector('#stuOut_tell').innerText = result.stuInfo.memberVO.memTell;
		    document.querySelector('#stuOut_addr').innerText = result.stuInfo.memberVO.memAddr;
		    document.querySelector('#stuOut_no').innerText = result.stuInfo.stuNo;
		    document.querySelector('#stuOut_grade').innerText = result.stuInfo.stuYear;
		    document.querySelector('#stuOut_status').innerText = result.stuInfo.stuStatus;
		    document.querySelector('#stuOut_coll').innerText = result.stuInfo.collNo;
		    document.querySelector('#stuOut_dept').innerText = result.stuInfo.deptNo;
	     
	     	let str = '';
	     	str += '<tr>';
	     	str += '<td colspan="6">누적 경고 내역</td>';
	     	str += '</tr>';
	     	
	     	//날짜와 사유를 for문으로 뽑아낸다.
	     	for(const probation of result.probationList){
		     	str += '<tr>';
		     	str += '<td colspan="2">';
		     	str += `<div id="probationModal_date">${probation.probDate}</div>`;
		     	str += '</td>';
		     	str += '<td colspan="4">';
		     	str += `<div id ="probationModal_reason">${probation.probReason}</div>`;
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
	const stuNo = document.querySelector('#probationModal_no').innerText;
	const probReason = document.querySelector('#probReason').value;
	const semNo = document.querySelector('#semNo').value;
	const memNo = document.querySelector('#probationModal_memNo').value;

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
				    location.href = '/emp/probation';
				  }
				})
		     
		    },
		    error: function(){
		       alert('실패');
		    }
		});
}
//by수경 제적처리 버튼 클릭 시
function acceptStuOut(){
	
	const stuNo = document.querySelector('#stuOut_no').innerText;
	const stuOutReason = document.querySelector('#stuOutReason').value;
	
	$.ajax({
	   url: '/emp/acceptStuOutAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo,'stuOutReason':stuOutReason}, //필요한 데이터
	    success: function(result) {
			
				Swal.fire({
				  title: '제적처리 완료',
				  text: "해당 학생을 제적처리하였습니다. 카카오톡으로 내역을 발송하시겠습니까?",
				  icon: 'success',
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: '확인'
				}).then((result) => {
				  if (result.isConfirmed) {
				    sendKakao();
				  }
				})

	    },
	    error: function(){
	       alert('실패');
	    }
	});
}

//by수경 카카오톡 메세지 공유하기
function sendKakao(){
		
	Kakao.Share.sendCustom({
	  templateId: 86136,
	  templateArgs: {
	    title: '제목 영역입니다.',
	    description: '설명 영역입니다.',
	  },
	
	});

}
