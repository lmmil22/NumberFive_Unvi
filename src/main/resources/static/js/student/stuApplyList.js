//by수경 학생이 휴학, 복학, 전과, 복수전공 신청을 했을 때
//관리자가 승인하기 전에 신청내역을 철회하는 것 구현

//by수경 휴학신청 철회하기
function cancelTakeOff(){
	
	const applyCode = document.querySelector('#takeOff').value;
	//alert(applyCode);
	
	Swal.fire({
		title: '신청 취소',
		text: "휴학신청을 취소하시겠습니까?",
		icon: 'warning',
		showCancelButton: true, // cancel버튼 보이지 않도록(false) 보이도록 하고자 한다면 true
		confirmButtonColor: '#3085d6',
		confirmButtonText: '확인',
		cancelButtonText: '취소'
		}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
			   url: '/stu/deleteApplyAjax', //요청경로
			    type: 'post',
			    data:{'applyCode':applyCode}, //필요한 데이터
			    success: function(result) {
					Swal.fire({
						  title: '취소 완료',
						  text: "휴학신청이 취소 되었습니다.",
						  icon: 'success',
						  showCancelButton: false,
						  confirmButtonColor: '#3085d6',
						  confirmButtonText: '확인',
						}).then((result) => {
						  if (result.isConfirmed) {
							location.href=`/stu/stuApplyList`;
							return;
						  }
						});
			    },
			    error: function(){
			       alert('실패');
			    }
			});
		}
	});

}

//by수경 복학신청 철회하기
function cancelReturn(){
	
	const applyCode = document.querySelector('#return').value;
	//alert(applyCode);
	
	Swal.fire({
		title: '신청 취소',
		text: "복학신청을 취소하시겠습니까?",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		confirmButtonText: '확인',
		cancelButtonText: '취소'
		}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
			   url: '/stu/deleteApplyAjax', //요청경로
			    type: 'post',
			    data:{'applyCode':applyCode}, //필요한 데이터
			    success: function(result) {
					Swal.fire({
						  title: '취소 완료',
						  text: "복학신청이 취소 되었습니다.",
						  icon: 'success',
						  showCancelButton: false,
						  confirmButtonColor: '#3085d6',
						  confirmButtonText: '확인',
						}).then((result) => {
						  if (result.isConfirmed) {
							location.href=`/stu/stuApplyList`;
							return;
						  }
						});
			    },
			    error: function(){
			       alert('실패');
			    }
			});
		}
	});

}
//by수경 전과신청 철회하기
function cancelChangeMajor(){
	
	const applyCode = document.querySelector('#change').value;
	//alert(applyCode);
	
	Swal.fire({
		title: '신청 취소',
		text: "전과신청을 취소하시겠습니까?",
		icon: 'warning',
		showCancelButton: true, // cancel버튼 보이지 않도록(false) 보이도록 하고자 한다면 true
		confirmButtonColor: '#3085d6',
		confirmButtonText: '확인',
		cancelButtonText: '취소'
		}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
			   url: '/stu/deleteApplyAjax', //요청경로
			    type: 'post',
			    data:{'applyCode':applyCode}, //필요한 데이터
			    success: function(result) {
					Swal.fire({
						  title: '취소 완료',
						  text: "전과신청이 취소 되었습니다.",
						  icon: 'success',
						  showCancelButton: false,
						  confirmButtonColor: '#3085d6',
						  confirmButtonText: '확인',
						}).then((result) => {
						  if (result.isConfirmed) {
							location.href=`/stu/stuApplyList`;
							return;
						  }
						});
			    },
			    error: function(){
			       alert('실패');
			    }
			});
		}
	});
}

//by수경 복수전공 신청 철회하기
function cancelDoubleMajor(){
	
	const applyCode = document.querySelector('#double').value;
	//alert(applyCode);
	
	Swal.fire({
		title: '신청 취소',
		text: "복수전공신청을 취소하시겠습니까?",
		icon: 'warning',
		showCancelButton: true, // cancel버튼 보이지 않도록(false) 보이도록 하고자 한다면 true
		confirmButtonColor: '#3085d6',
		confirmButtonText: '확인',
		cancelButtonText: '취소'
		}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
			   url: '/stu/deleteApplyAjax', //요청경로
			    type: 'post',
			    data:{'applyCode':applyCode}, //필요한 데이터
			    success: function(result) {
					Swal.fire({
						  title: '취소 완료',
						  text: "복수전공신청이 취소 되었습니다.",
						  icon: 'success',
						  showCancelButton: false,
						  confirmButtonColor: '#3085d6',
						  confirmButtonText: '확인',
						}).then((result) => {
						  if (result.isConfirmed) {
							location.href=`/stu/stuApplyList`;
							return;
						  }
						});
			    },
			    error: function(){
			       alert('실패');
			    }
			});
		}
	});

}