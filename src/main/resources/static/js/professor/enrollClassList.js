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
		 },
		 error: function() {
			 alert('실패');
		 }
	 });
	//ajax end
	
} 

	
	//ajax start
	 $.ajax({
		 url: '/proF/lecListAjax', //요청경로
		 type: 'post',
		 data: {'lecNo':lecNo}, 
		 
		 success: function(result) {
			
		 },
		 error: function() {
			 alert('실패');
		 }
	 });
	//ajax end
	