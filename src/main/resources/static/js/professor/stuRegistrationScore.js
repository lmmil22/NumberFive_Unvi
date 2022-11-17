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
			
			str += '<tbody class="selectBody">';
			for(const stu of result){
			str += '<tr class="gradeTr" >';
			str += `<td id="stuNoTd">${stu.stuNo}</td>`;
			str += `<td>`;
			str += `<a onclick="gradeDetail(${stu.stuNo});">${stu.studentVO.memNo}</a>`;
			str += `</td>`;
			str += `<td >${stu.gradeVO.grade}</td>`;
			str += `<td><input  class="gradeBtn" type="button" value="변경" onclick="gradeDetail('${stu.stuNo}',this)">               </td>`;
			str += '</tr>'; //js 문자열을 인식시켜주기 위해서는 홀따옴표안에 감싸야한다 
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

//변경버튼 클릭시  점수 등록 가능 
function gradeDetail(stuNo, selectedTag) {
	//alert(selectedTag.parent().previous().text);
	const selectedTd = selectedTag.closest('tr').querySelector('td:nth-child(3)'); //선택한 tr에서 3번째 자식을 선택해준다 
	const btn= document.querySelector(".gradeBtn");
	 //선택한 tr에서 3번째 자식을 선택해준다 

	if (btn.value == '변경') {
		prepareRegGrade(selectedTd, btn);
		
	}
	//변경이 아니라ㅏ value가 확인일때 실행된다
	else {
		regGrade(selectedTd, btn , stuNo);
	}


	

}

//확인 버튼을 누르면 업데이트진행되는 ajax?
function updateGrade(){
	//const btn= document.querySelector(".gradeBtn").value;
	// const stuNo = document.querySelector('#stuNoTd').innerText; 
// const selectedTd = document.querySelector(".selectBody").closest('tr').querySelector('td:nth-child(3)').value;
	
	if (btn.value == '확인') {
	//ajax start
	$.ajax({
		url: '/proF/updateGradeAjax', //요청경로
		type: 'post',
		data: { 'stuNo': stuNo }, //필요한 데이터 //'stuNum' 컨트롤러로 전달되는 이름 //여긱서 던진거와 컨트롤러에서 적어준거와 이름만 같으면 받아지기 때문에 가능은하다 자바스크립트 문법을 맞춰준것 
		success: function(result) {
		
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
	
	
	
}


}



//버튼이 변경일때
function prepareRegGrade(selectedTd, btn){
	//ajax start
	$.ajax({
		url: '/proF/setScoreAjax', //요청경로
		type: 'post',
		data: {},  
		success: function(result) {
			
			//에이작스로 점수 들고오고 성공 부분에 넣어준다 
			
			//기존의 성적
			const origin_grade = $(selectedTd).text(); //입력되어있던걸 변경할때 셀렉트값에 기존에 선택한게 보여야하기 때문
			
			$(selectedTd).empty();
			
			let str = '';
			
			str += `<select>`;
			for(const grade of result){ //체크한 값이 보이도록 하기위해서 
				if(grade.grade == origin_grade){
					str += `<option value="${grade.grade}" selected>${grade.grade}</option>`;
				}
				else{
					str += `<option value="${grade.grade}">${grade.grade}</option>`;
				}
			}
			str += `</select>`;

			$(selectedTd).append(str);
			btn.value = '확인';
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}
//확인 버튼이 활성화 되었을때
function regGrade(selectedTd, btn ,stuNo){
	
	//const grade = selectedTd.querySelector('option:selected').value;
	//td 등 안에있는 텍스트를 빼고 싶을대는 text 인것이고 
	// value = input 등에 있는 벨류속성값을 빼주는것이다 
	const grade = $(selectedTd).find('option:selected').val(); //선택된 자식의 벨류값이다 
	//alert(stuNo);
	//alert(grade);
	
	
	//ajax start
	$.ajax({
		url: '/proF/updateGradeAjax', //요청경로
		type: 'post',
		data: {'stuNo':stuNo , 'grade':grade},  
		success: function(result) {
			
			//에이작스로 점수 들고오고 성공 부분에 넣어준다 
			//
			$(selectedTd).empty();
			

			let str = result;
			$(selectedTd).append(str);
			btn.value = '변경';
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}
