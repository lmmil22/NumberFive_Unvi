//by 지아

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

//중복 날짜 시간 체크
function time(){
	const lecDay = document.querySelector("#lecDay").value;
	const firstTime = document.querySelector("#firstTime").value;
	const lastTime =  document.querySelector("#lastTime").value;

		 //ajax start
	 $.ajax({
		 url: '/proF/timeAjax', 
		 type: 'post',
		 data: {'lecDay':lecDay ,'firstTime':firstTime , 'lastTime':lastTime},
		 success: function(result) {
			//강의 등록 불가 시 //숫자가 1이나오면 그시간에 강의가 있는것
			if(result != 0){
				Swal.fire('등록 불가', '등록 노노', 'warning'); 
				document.querySelector('#regLectureTimeBtn').disabled = true; //등록하기 버튼이 눌리지 않음
			}
			else{ //숫자가 0이니 그시간의 강의가 없다 
				Swal.fire('등록!!', '등록!!!', 'success');
				document.querySelector('#regLectureTimeBtn').disabled = false; //등록하기 버튼이 활성화 된다 
				
			}
		
		 },
		 error: function() {
			 alert('실패');
		 }
	 });
	//ajax end		
	
	
}


function setDisable(){
	document.querySelector('#regLectureTimeBtn').disabled = true;
}
