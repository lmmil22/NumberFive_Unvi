//by 지아

function changeDept(){
	//colleageNo 값 
 	const collNo = document.querySelector('select').value;
			
 	alert(collNo);
//const tbody = document.querySelector('.stuListTable > tbody');
			//const deptSelect = document.querySelector('.changeDeptList');
			//const optionTags = deptSelect.querySelectorAll('option');
			//deptSelect.removeChild(optionTags); // 부모 테이블 중에 자식을 지울건데 티바디만 지울거에요
			
	 //ajax start
	 $.ajax({
		 url: '/proF/getDeptListAjax', 
		 type: 'post',
		 data: {'collNo':collNo},
		 success: function(result) {
			
			//현재 (지금) 열러있는 html파일에서 찾는다 (클래스가stuListTable을 찾는다  )
			//const deptSelect = document.querySelector('.changeDeptList');
			
			//전체가 뜨는 테이블은 지워준다 
			
			//const tbody = document.querySelector('.stuListTable > tbody');
			//const optionTags = deptSelect.querySelectorAll('option');
			//***** 이걸 사용하면 FOR 문을 돌려서 하나씩 지워줘야한다 자식들을  */deptSelect.removeChild(optionTags); // 부모 테이블 중에 자식을 지울건데 티바디만 지울거에요
			$('.changeDeptList').empty(); //제이 쿼리문법인데 FOR문을 돌리지않고 자식들을 다 지워줄 수 있다 
		
		//추가할 태그를 문자열로 받음 (+= 를 사용ㅇ하면 문자열이 계속 연결된다 )
			let str ='';
		

			str += '<option>';
			for(const dept of result){ //학생목록이 다 있다 . stu = 학생한명


//최근버전 백틱사용 


			str += '<tr>';
			str += `<td>${stu.stuNum}</td>`;
		/*	str += `<td>${stu.stuName}</td>`;*/
			
			str += `<td>`;
		/*	str += `<span onclick="showDetail();">${stu.stuName}</span>`;*/
			str += `<a onclick="showDetail(${stu.stuNum});">${stu.stuName}</a>`;
			str += `</td>`;
			
			str += `<td>${stu.stuAge}</td>`;
			str += `<td>${stu.className}</td>`;
			str += `<td><input type="button" value="삭제" onclick="deleteStu(${stu.stuNum},this);">               </td>`;
			str += '</tr>';

			}

			str += '</tbody>';
		
		stuListTable.insertAdjacentHTML('beforeend',str); //이 테이블이 끝나기 전에 str을 붙여주겠다 
		
			
		
		 },
		 error: function() {
			 alert('실패');
		 }
	
	 });
	//ajax end		
			
	 

}
