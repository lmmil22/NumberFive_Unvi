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
	//ajax start
	 $.ajax({
		 url: '/proF/lecListAjax', //요청경로
		 type: 'post',
		 data: {'lecNo':lecNo}, //필요한 데이터 //전달해야하는 데이터는 괄호안에 작성  //콜론을 기준으로 홍이라는데이터를 네임이라는 이름으로 던질게요 2개 이상일땐 쉼표로 나열 해준다 
		 //콜론 기준으로 왼쪽이 던지는 데이터의 이름 
		 success: function(result) {
			 alert('aaa');
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


function setModelData(result){
	document.querySelector('#changeLecModal_lecName').value = result.lecName; 
	document.querySelector('#changeLecModal_deptNo').innerText = result.deptNo;
	document.querySelector('#changeLecModal_collNo').innerText = result.collNo;
	document.querySelector('#changeLecModal_empNo').innerText = result.empNo;
	document.querySelector('#changeLecModal_lastTime').innerText = result.lectureTimeVO.lastTime;
	document.querySelector('#changeLecModal_lecScore').value = result.lecScore;
	
}