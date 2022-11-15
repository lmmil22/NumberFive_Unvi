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
			str += '<tr class="gradeTr" >';
			str += `<td>${stu.stuNo}</td>`;
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

//학생 이름 클릭시 점수 등록 가능 
function gradeDetail(stuNo, selectedTag) {
	//alert(selectedTag.parent().previous().text);

	//ajax start
	$.ajax({
		url: '/stu/deleteStu', //요청경로
		type: 'post',
		data: { 'stuNo': stuNo }, //필요한 데이터 //'stuNum' 컨트롤러로 전달되는 이름 //여긱서 던진거와 컨트롤러에서 적어준거와 이름만 같으면 받아지기 때문에 가능은하다 자바스크립트 문법을 맞춰준것 
		success: function(result) {
			
			const btn = document.querySelector('.gradeBtn');
			if (btn.value == '변경') {
			
			//에이작스로 점수 들고오고 성공 부분에 넣어준다 
			//
			const selectedTd = selectedTag.closest('tr').querySelector('td:nth-child(3)'); //선택한 tr에서 3번째 자식을 선택해준다 
			$(selectedTd).empty();

			let str = '';
			str += `<select>`;
				
			str += `<option value="미등록">미등록</option>`;
			for(const grade of result){
			str += `<option value="${grade.grade}">${grade.grade}</option>`;
			}
			str += `</select>`;

			$(selectedTd).append(str);

			}
			btn.value = '확인';

		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end




}
