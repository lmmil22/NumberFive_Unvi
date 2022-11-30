//by 지아

//학사 일정 등록버튼 누르면 실행
function insert() {
		const title = document.querySelector("#title").value;
	const startdate = document.querySelector("#startdate").value;
	const enddate =  document.querySelector("#enddate").value;
	
	//ajax start
			 $.ajax({
				 url: '/calendar/insertCalAjax', //요청경로
				 type: 'post',
				 data: {'title': title , 'startdate':startdate , 'enddate':enddate}, 
				 
				 success: function(result) {
				 
				 location.href= '/calendar/cal';
				 
				 },
				 error: function() {
					 alert('실패');
				 }
			 });
			//ajax end

}
