//by 지아 

//수강 신청 클릭시 진행
 

 /*
 function selectLecEnroll(lecNo){
	
	 Swal.fire({
	title: '수강신청.',
	text: "수강신청이 진행됩니.",
	icon: 'warning',
	showCancelButton: false, // cancel버튼 보이지 않도록(false) 보이도록 하고자 한다면 true
	confirmButtonColor: '#3085d6',
	confirmButtonText: '확인',
	cancelButtonText: '취소'
	}).then((result) => {
	if (result.isConfirmed) {
			//alert(lecNo);
	
	//ajax start
	 $.ajax({
		 url: '/proF/insertEnrollAjax', //요청경로
		 type: 'post',
		 data: {'lecNo':lecNo}, 
		 
		 success: function(result) {
			
			alert('수강 신청이 완료되었습니다 ');
			location.href = '/proF/enrollList';
		 },
		 error: function() {
			 alert('실패');
		 }
	 });
	//ajax end
	
		
		
	}
})
 
	
	

} 

//by 지아 
//수강 신청 취소시 진행 
function deleteLecEnroll(stuNo , lecNo){
	const result = confirm('정말 취소 할까요?')
	//alert(lecNo);
	if(result){
	//ajax start
	 $.ajax({
		 url: '/proF/deleteEnrollAjax', //요청경로
		 type: 'post',
		 data: {'stuNo':stuNo ,'lecNo':lecNo}, 
		 
		 success: function(result) {
			
			alert('수강 취소가 완료되었습니다 ');
			location.href = '/proF/enrollList';
		 },
		 error: function() {
			 alert('실패');
		 }
	 });
	//ajax end
	}
}
	
function changeDept(){
	//colleageNo 값 
 	const collNo = document.querySelector('.changeDept').value;
			
 	//alert(collNo);
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

*/