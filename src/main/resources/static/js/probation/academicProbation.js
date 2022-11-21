
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
	     document.querySelector('#probationModal_name').innerText = result.memberVO.memName;
	     document.querySelector('#probationModal_birth').innerText = result.memberVO.memBirth;
	     document.querySelector('#probationModal_tell').innerText = result.memberVO.memTell;
	     document.querySelector('#probationModal_addr').innerText = result.memberVO.memAddr;
	     document.querySelector('#probationModal_no').innerText = result.stuNo;
	     document.querySelector('#probationModal_grade').innerText = result.stuYear;
	     document.querySelector('#probationModal_status').innerText = result.stuStatus;
	     document.querySelector('#probationModal_coll').innerText = result.collNo;
	     document.querySelector('#probationModal_dept').innerText = result.deptNo;
	     document.querySelector('#probationModal_date').innerText = result.statusInfoVO.academicProbationVO.probDate;
	     document.querySelector('#probationModal_reason').innerText = result.statusInfoVO.academicProbationVO.probReason;

	    },
	    error: function(){
	       alert('실패');
	    }
	});

	
}
