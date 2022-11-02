
//by 수경 전과신청 시 전공대학, 전공학과 선택 select ajax
function changeColl(){
	
	const collNo = document.querySelector('select').value;
	
	$.ajax({
	   url: '/stu/getMajorAjax', //요청경로
	    type: 'post',
	    data: {'collNo':collNo}, //필요한 데이터
	    success: function(result) {
	   
	   		const deptSelect = document.querySelector('.deptList');
	   		
	   		const optionTags = deptSelect.querySelectorAll('option');
			$('.deptList').empty(); //모든 자식들 지워줄거에요 
		
			let str ='';
		
			for(const dept of result){ //옵션이 계속 반복된다
				str += `<option value="${dept.deptNo}">${dept.deptName}</option>`;
			}			

			deptSelect.insertAdjacentHTML('beforeend',str); // 셀렉트박스가 끝나기 전에 str을 붙여주겠다 
		
	    },
	    error: function(){
	       alert('실패');
	    }
	});

	
}