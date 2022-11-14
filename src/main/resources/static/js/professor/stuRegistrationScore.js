function selectLecStu(lecNo){
	
	
	//alert(lecNo);
	
	//ajax start
	 $.ajax({
		 url: '/proF/selectLecStuAjax', //요청경로
		 type: 'post',
		 data: {'lecNo':lecNo}, 
		 
		 success: function(result) {
			const stuListTable = document.querySelector('.stuListTable');
			const tbody = stuListTable.querySelector('tbody');
			stuListTable.removeChild(tbody);
			
			let str ='';
			
			str += '<tbody>';
			for(const stu of result){
			str += '<tr>';
			str += `<td>${stu.stuNo}</td>`;
		/*	str += `<td>${stu.stuName}</td>`;*/
			
			str += `<td>`;
		/*	str += `<span onclick="showDetail();">${stu.stuName}</span>`;*/
			str += `<a onclick="gradeDetail(${stu.stuNo});">${stu.studentVO.memNo}</a>`;
			str += `</td>`;
			
			str += `<td>${stu.gradeVO.grade}</td>`;

			str += `<td><input type="button" value="변경" onclick="deleteStu(${stu.stuNum},this);">               </td>`;
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

//학생 이름 클릭시 점수 등록 가능 
function gradeDetail(){
	
	//ajax start
	 $.ajax({
		 url: '/proF/setScoreAjax', //요청경로
		 type: 'post',
		 data: {'stuNum':stuNum}, //필요한 데이터 //전달해야하는 데이터는 괄호안에 작성  //콜론을 기준으로 홍이라는데이터를 네임이라는 이름으로 던질게요 2개 이상일땐 쉼표로 나열 해준다 
		 //콜론 기준으로 왼쪽이 던지는 데이터의 이름 
		// 컨트롤러에서 이제 내가 던진  stuNum을 받을 수 있다 
		 success: function(stu) {
		let str = '';
		
		str += '<table class="detailTable">   ';
		str += '	<tr>                      ';
		str += '		<td>학생 번호 </td>   ';
		str += '		<td>학생 이름 </td>   ';
		str += '		<td>소속 학급명 </td> ';
		str += '	</tr>                     ';
		str += '	<tr>                      ';
		str += `<td id="stuNumTd">${stu.stuNum}</td>`;
		str += `<td>${stu.stuName}</td>`;
		str += `<td>${stu.className}</td>`;
		str += '	</tr>                     ';
		str += '	<tr>                      ';
		str += '		<td>국어점수</td>     ';
		str += '		<td>영어점수</td>     ';
		str += '		<td>수학점수</td>     ';
		str += '	</tr>                     ';
		str += '	<tr id="scoreTr">                      ';
/*	하나의 데이터만 옮길땐	str += `<td id="korScoreTd">${stu.korScore}</td>`;
*/		str += `<td>${stu.korScore}</td>`;
		str += `<td>${stu.engScore}</td>`;
		str += `<td>${stu.mathScore}</td>`;
		str += '	</tr>                     ';
		str += '</table>                      ';
		str += '	<div style="text-align:center;">                     ';
		str += '	<input id="scoreBtn" type="button" value="점수등록" onclick="setScore();">                     ';
		str += '	</div>                     ';
		      
		
		const detailTd = document.querySelector('#detailTd');
		
		detailTd.innerHTML = ''; //빈문자로 주고 바꾼다 
		detailTd.insertAdjacentHTML('beforeend' , str); // 처음은 들어갈 위치 , 그다음 넣을 것
		//afterbegin도 가능 
		
	
		 },
		 error: function() {
			 alert('실패');
		 }
	 });
	//ajax end
	
	
	
}