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

//by수경 학번 클릭 시 나타날 제적 창 학생 데이터
function stuInfo(stuNo){
	
	$.ajax({
	   url: '/emp/stuOutStuInfoAjax', //요청경로
	    type: 'post',
	    data:{'stuNo':stuNo}, //필요한 데이터
	    success: function(result) {
			//학사경고 사유/날짜 부분의 데이터를 지운다
			$('.stuOutTb').empty();
		
			document.querySelector('#stuOutModal_name').innerText = result.studentVO.memberVO.memName;
		    document.querySelector('#stuOutModal_birth').innerText = result.studentVO.memberVO.memBirth;
		    document.querySelector('#stuOutModal_tell').innerText = result.studentVO.memberVO.memTell;
		    document.querySelector('#stuOutModal_addr').innerText = result.studentVO.memberVO.memAddr;
		    document.querySelector('#stuOutModal_no').innerText = result.studentVO.stuNo;
		    document.querySelector('#stuOutModal_grade').innerText = result.studentVO.stuYear;
		    document.querySelector('#stuOutModal_status').innerText = result.studentVO.stuStatus;
		    document.querySelector('#stuOutModal_coll').innerText = result.studentVO.collNo;
		    document.querySelector('#stuOutModal_dept').innerText = result.studentVO.deptNo;
		   
	     
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



