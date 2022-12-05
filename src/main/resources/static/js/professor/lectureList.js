//by 지아 
//a태그가 무용지물 된다 
// $('a').click(function(event){
//    event.preventDefault(); 
//  });

//페이징 라이브러리
var box = paginator({
    table: document.getElementById("table_box_bootstrap").getElementsByTagName("table")[0],
    box_mode: "list",
});
box.className = "box";
document.getElementById("table_box_bootstrap").appendChild(box);

//by 지아 
//스왈창에서 강의 수정을 누르면 
function changeLecDetail(lecNo) {
	//const update = document.querySelector('#updateData');

	Swal.fire({
		title: '강의 수정',
		text: '강의를 수정하시겠습니까?',
		icon: 'question',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '승인',
		cancelButtonText: '취소',

	}).then(result => {
		// 만약 Promise리턴을 받으면,
		if (result.isConfirmed) { // 만약 얼럿창에서 confirm 버튼을 눌렀다면


			//ajax start
			 $.ajax({
				 url: '/proF/lecListAjax', //요청경로
				 type: 'post',
				 data: {'lecNo':lecNo}, 
				 
				 success: function(result) {
					 	//모달창 띄우는 소스 작성
					//result에 내가 조회한 데이터가 있고 
					// 그 데이터를 모달이 띄우기 전에 
					// 모달 영역에 넣어준다
					setModelData(result); 
				 },
				 error: function() {
					 alert('실패');
				 }
			 });
			//ajax end

			//모달을 띄우는   
			const Modal = new bootstrap.Modal('#changeLecModal');
			Modal.show();
		}
	});
}


//모달창에서 수정하기 버튼 클릭 시 실행
function updateLecInfo(){
	//ajax start
	 $.ajax({
		 url: '/proF/updateLecAjax', //요청경로
		 type: 'post',
		 //제이쿼리 문법으로 form 모든 내용을 한꺼번에 데이터로 ajax로 보냄
		 data: $('#updateDataForm').serialize(), 
		 success: function(result) {
			$('#changeLecModal').modal('hide'); //수정하기 눌러주면 모달을 닫고
			
			Swal.fire({
				title: '강의 수정',
				text: '수정이 완료되었습니다',
				icon: 'success',
				confirmButtonColor: '#3085d6',
				confirmButtonText: '확인',
				reverseButtons: true,
			}).then(result => {
				if (result.isConfirmed) { //강의 수정이 진행된후
				location.href = '/proF/viewLecList';
		   }
		});
			
		 },
		 error: function() {
			 alert('실패');
		 }
	 });
	//ajax end
}


//by 지아 
//강의 삭제 클릭 시 
function deleteLec(lecNo) {
	//const result = confirm('강의를 삭제하시겠어요?');
	Swal.fire({
		title: '강의 삭제',
		text: '강의를 삭제하시겠어요.',
		icon: 'warning',

		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '승인',
		cancelButtonText: '취소',

		reverseButtons: true,

	}).then(result => {

		if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면


			//if(result){

			//ajax start
			$.ajax({
				url: '/proF/deleteLecAjax', //요청경로
				type: 'post',
				data: { 'lecNo': lecNo },
				success: function(result) {
					alert('aaa');
					location.href = '/proF/viewLecList';

				},
				error: function() {
					alert('실패');
				}
			});
			//ajax end
			//}
			Swal.fire('강의 삭제', '삭제가 완료되었습니다', 'success');
   }
});
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
/*
Swal.fire({
   title: '정말로 그렇게 하시겠습니까?',
   text: '다시 되돌릴 수 없습니다. 신중하세요.',
   icon: 'warning',
   
   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
   confirmButtonText: '승인', // confirm 버튼 텍스트 지정
   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
   
   reverseButtons: true, // 버튼 순서 거꾸로
   
}).then(result => {
   // 만약 Promise리턴을 받으면,
   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
   
      Swal.fire('승인이 완료되었습니다.', '화끈하시네요~!', 'success');
   }
});*/