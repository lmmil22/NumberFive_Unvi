//by 지아 

//수강 신청 클릭시 진행
 
 
 
 function selectLecEnroll(lecNo){
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
	
