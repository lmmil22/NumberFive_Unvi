//by 지아

function changeDept(){
	//colleageNo 값 
 	const collNo = document.querySelector('select').value;
			
 	alert(collNo);
	 //ajax start
	 $.ajax({
		 url: '/proF/getDeptListAjax', 
		 type: 'post',
		 data: {'collNo':collNo},
		 success: function(result) {
			
			const deptSelect = document.querySelector('.changeDeptList');
			
			
			const optionTags = deptSelect.querySelectorAll('option');
			$('.changeDeptList').empty(); //모든 자식들 지워줄거에요 
		
			let str ='';
		

		
			for(const dept of result){ //옵션이 계속 반복된다
			str += `<option value="${dept.deptNo}">${dept.deptName}</option>`;
				}			



		
		deptSelect.insertAdjacentHTML('beforeend',str); // 셀렉트박스가 끝나기 전에 str을 붙여주겠다 
		
			
		
		 },
		 error: function() {
			 alert('실패');
		 }
	
	 });
	//ajax end		
			
	 

}
