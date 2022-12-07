// by 유빈 : 교수 강의 시간표 불러오기 
////////////////////////////////////////////////////////////////////////////////
//--------------------------------[시간표 불러오기]----------------------------//
//ajax start
$.ajax({
	url: '/timetable/loadProf', //요청경로
	type: 'post',
	data: {   }, //필요한 데이터
	success: function(lecList) {
		const trList = document.querySelectorAll('.lecTime'); //강의시간 
		
		for (lec of lecList) {
			console.log(lec);

			switch (lec.lecDay) {
				case '월':  // if (x === 'value1')
					for(i = lec.startRowNum; i <= lec.endRowNum; i++ ){
						trList[i].children[1].innerText += (  lec.lecName + '\n' + ' ( '+ lec.deptNo +' ) ');
						
					}
				break;
				case '화':  // if (x === 'value1')
					for(i = lec.startRowNum; i <= lec.endRowNum; i++ ){
						trList[i].children[2].innerText += (  lec.lecName + '\n' + ' ( '+lec.deptNo +' ) ');
					}
				break;
				case '수':  // if (x === 'value1')
					for(i = lec.startRowNum; i <= lec.endRowNum; i++ ){
						trList[i].children[3].innerText += ( lec.lecName+ '\n' + ' ( '+lec.deptNo +' ) ');
					}
				break;
				case '목':  // if (x === 'value1')
					for(i = lec.startRowNum; i <= lec.endRowNum; i++ ){
						trList[i].children[4].innerText += ( lec.lecName+ '\n'+ ' ( '+lec.deptNo +' ) ');
					}
			
				break;
				case '금':  // if (x === 'value1')
					for(i = lec.startRowNum; i <= lec.endRowNum; i++ ){
						trList[i].children[5].innerText += ( lec.lecName+ '\n'+ ' ( '+lec.deptNo +' ) ');
					}
			
				break;
			}
		}
	},
	error: function() {
		Swal.fire({
			icon: 'warning',
			title: 'error',
			text: '에러발생.',
		});
	}
});
//ajax end

///////////////////////////////////////////////////////////////////////////////////////////////
//----강의시간표 수업있으면 색상 채우기 함수
//const tdTag = document.querySelector('td').innerText; // td태그안의 텍스트 선택
//alert(tdTag);

