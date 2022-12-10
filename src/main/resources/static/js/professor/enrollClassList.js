//by 지아 

//수강 신청 클릭시 진행
 

 
function selectLecEnroll(lecNo) {

	Swal.fire({
		title: '수강신청.',
		text: "수강신청이 진행됩니다.",
		icon: 'warning',
		showCancelButton: false, // cancel버튼 보이지 않도록(false) 보이도록 하고자 한다면 true
		confirmButtonColor: '#3085d6',
		confirmButtonText: '확인',
		cancelButtonText: '취소'
	}).then((result) => {
		if (result.isConfirmed) {
			//alert(lecNo);

			//ajax start
			$.ajax({
				url: '/proF/insertEnrollAjax', //요청경로
				type: 'post',
				data: { 'lecNo': lecNo },

				success: function(result) {

					Swal.fire({
						title: '수강신청완료',
						text: "수강신청 되었습니다. ",
						icon: 'success',
						confirmButtonColor: '#3085d6',
						confirmButtonText: '확인',
					}).then(result => {
						// 만약 Promise리턴을 받으면,
						if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면


							location.href = '/proF/enrollList';
						}
					})
				},
				error: function() {
					alert('실패');
				}
			});
			//ajax end



		}
	})

	
	

} 

//by 지아 
//수강 신청 취소시 진행 
function deleteLecEnroll(stuNo, lecNo) {

	Swal.fire({
		title: '수강취소',
		text: "수강취소가 진행됩니다.",
		icon: 'warning',
		showCancelButton: false, // cancel버튼 보이지 않도록(false) 보이도록 하고자 한다면 true
		confirmButtonColor: '#3085d6',
		confirmButtonText: '확인',
		cancelButtonText: '취소'
	}).then((result) => {
		if (result.isConfirmed) {


			//const result = confirm('정말 취소 할까요?')
			//alert(lecNo);

			//ajax start
			$.ajax({
				url: '/proF/deleteEnrollAjax', //요청경로
				type: 'post',
				data: { 'stuNo': stuNo, 'lecNo': lecNo },
				success: function(result) {

					Swal.fire({
						title: '수강 취소완료',
						text: "수강 취소가 완료되었습니다. ",
						icon: 'success',
						reverseButtons: true,
						confirmButtonColor: '#3085d6',
						confirmButtonText: '확인',
					}).then(result => {
						// 만약 Promise리턴을 받으면,
						if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면


							location.href = '/proF/enrollList';
						}
					})
				},
				error: function() {
					alert('실패');
				}
			});
			//ajax end

		}
	});
}
	
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
			str = `<option value="">전체</option>`;
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
//이렇게 진행되면 수강 신청 완료 완료 이런식으로 두번 뜨게 된다 
/*function selectLecEnroll(lecNo){
	
	 Swal.fire({
	title: '수강신청.',
	text: "수강신청이 진행됩니다.",
	icon: 'warning',
	showCancelButton: false, // cancel버튼 보이지 않도록(false) 보이도록 하고자 한다면 true
	confirmButtonColor: '#3085d6',
	confirmButtonText: '확인',
	cancelButtonText: '취소'
	}).then((result) => {
	if (result.isConfirmed) {
			//alert(lecNo);
	
	//ajax start
	 $.ajax({
		 url: '/proF/insertEnrollAjax', //요청경로
		 type: 'post',
		 data: {'lecNo':lecNo}, 
		 
		 success: function(result) {
			
					    Swal.fire({
						  title: '수강신청완료',
						  text: "수강신청 되었습니다. ",
						  icon: 'success',
						  showCancelButton: true,
						  confirmButtonColor: '#3085d6',
						  confirmButtonText: '확인',
						  cancelButtonText: '취소'
					}).then(result => {
   // 만약 Promise리턴을 받으면,
   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
   
      Swal.fire('승인이 완료되었습니다.', '화끈하시네요~!', 'success');
						location.href = '/proF/enrollList';
   }
})
		 },
		 error: function() {
			 alert('실패');
		 }
	 });
	//ajax end
	
		
		
	}
})
 
*/