//by 수경 전과신청 시 전공대학, 전공학과 선택 select ajax
//전과 또는 복수전공 신청할 전공대학, 전공학과 선택할 때 
//본인의 현재 소속 대학, 학과는 제외 되도록 한다.
function changeColl(){
	
	const collNo = document.querySelector('select').value;
	const deptNo = document.querySelector('#nowDept');
	
	$.ajax({
	   url: '/stu/getMajorAjax', //요청경로
	    type: 'post',
	    data: {'collNo':collNo, 'deptNo':deptNo}, //필요한 데이터
	    success: function(result) {
	   
	   		const deptSelect = document.querySelector('.deptList');
	   		
	   		const optionTags = deptSelect.querySelectorAll('option');
			$('.deptList').empty(); 
		
			let str ='';
		
			for(const dept of result){ 
				str += `<option value="${dept.deptNo}">${dept.deptName}</option>`;
			}			

			deptSelect.insertAdjacentHTML('beforeend',str); // 셀렉트박스가 끝나기 전에 str을 붙여주겠다 
		
	    },
	    error: function(){
	       alert('실패');
	    }
	});

	
}


//by수경 학생이 전과신청 버튼을 눌렀을 때
//학생이 현재 재학중인 학과를 선택했을 때 현재 재학중인 학과는 보이지 않도록 select box(ajax) 
//전과신청 버튼 클릭 시 유의사항 팝업창이 뜨고 확인버튼 누르면 
//전과신청이 완료 되었습니다. 팝업이 뜬 후 신청현황 페이지로 이동



