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
	
	//처음 페이지가 열리고 아무것도 입력안하고 시간 체크를 누른다면 널값이 넘어가기 때문에 처리해준다
	if(lecDay == ''){
		document.querySelector("#lecDay").insertAdjacentHTML('afterend', '<div style="color:red; font-size:0.7rem;">필수입력이에요!</div>');
		return ; 
	}
	
	if(firstTime == ''){
		document.querySelector("#firstTime").insertAdjacentHTML('afterend', '<div style="color:red; font-size:0.7rem;">필수입력이에요!</div>');
		return ; 
	}
	
	if(lastTime == ''){
		document.querySelector("#lastTime").insertAdjacentHTML('afterend', '<div style="color:red; font-size:0.7rem;">필수입력이에요!</div>');
		return ; 
	}

		 //ajax start
	 $.ajax({
		 url: '/proF/timeAjax', 
		 type: 'post',
		 data: {'lecDay':lecDay ,'firstTime':firstTime , 'lastTime':lastTime},
		 success: function(result) {
			//강의 등록 불가 시 //숫자가 1이나오면 그시간에 강의가 있는것
			if(result != 0){
				Swal.fire('등록 불가', '해당 시간이 이미 등록되어있습니다', 'warning'); 
				document.querySelector('#regLectureTimeBtn').disabled = true; //등록하기 버튼이 눌리지 않음
			}
			else{ //숫자가 0이니 그시간의 강의가 없다 
				Swal.fire('시간 체크', '등록 가능한 시간입니다 ', 'success');
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
