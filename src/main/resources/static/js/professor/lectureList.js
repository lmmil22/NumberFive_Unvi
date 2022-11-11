//by 지아 
//페이징 라이브러리
var box = paginator({
    table: document.getElementById("table_box_bootstrap").getElementsByTagName("table")[0],
    box_mode: "list",
});
box.className = "box";
document.getElementById("table_box_bootstrap").appendChild(box);

//by 지아 
//강의 수정을 누르면 
function changeLecDetail(lecNo){
	const result = confirm('강의를 수정하시겠어요?');
	
	if (result){
	//ajax start
	 $.ajax({
		 url: '/proF/lecListAjax', //요청경로
		 type: 'post',
		 data: {'lecNo':lecNo}, 
		 
		 success: function(result) {
			 //alert('aaa');
			 	//모달창 띄우는 소스 작성
			//result에 내가 조회한 데이터가 있고 
			// 그 데이터를 모달이 띄우기 전에 
			// 모달 영역에 넣어준다
			setModelData(result); 
			
			const Modal = new bootstrap.Modal('#changeLecModal');
			Modal.show();
		 },
		 error: function() {
			 alert('실패');
		 }
	 });
	//ajax end
	}
	$('#changeLecModal').modal('hide');
	
	

}

//by 지아 
//강의 삭제 클릭 시 
function deleteLec(lecNo){
	const result = confirm('강의를 삭제하시겠어요?');
	
	//ajax start
	 $.ajax({
		 url: '/proF/deleteLecAjax', //요청경로
		 type: 'post',
		 data: {'lecNo':lecNo},
		 success: function(result) {
			 alert('aaa');
			location.href = '/proF/viewLecList';

		 },
		 error: function() {
			 alert('실패');
		 }
	 });
	//ajax end
	
}



function setModelData(result){
	document.querySelector('#changeLecModal_lecName').value = result.lecName; 
	document.querySelector('#changeLecModal_deptNo').innerText = result.deptNo;
	document.querySelector('#changeLecModal_collNo').innerText = result.collNo;
	document.querySelector('#changeLecModal_empNo').innerText = result.empNo;
	document.querySelector('#changeLecModal_lastTime').value = result.lectureTimeVO.lastTime;
	document.querySelector('#changeLecModal_firstTime').value = result.lectureTimeVO.firstTime;
	document.querySelector('#changeLecModal_lecScore').value = result.lecScore;
	document.querySelector('#changeLecModal_lecDay').value = result.lectureTimeVO.lecDay;
	document.querySelector('#changeLecModal_lecNo').value = result.lecNo;
	
}