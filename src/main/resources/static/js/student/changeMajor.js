//by 수경 전과신청 시 전공대학, 전공학과 선택 select ajax
//전과 또는 복수전공 신청할 전공대학, 전공학과 선택할 때 
//본인의 현재 소속 대학, 학과는 제외 되도록 한다.
function changeColl(){
	
	const collNo = document.querySelector('select').value;
	//by수경 재학중인 학과 제외하기 위해서 매개변수로 stuNo 필요 
	const stuNo = document.querySelector('#stuNo').value;
	
	$.ajax({
	   url: '/stu/getMajorAjax', //요청경로
	    type: 'post',
	    // 추후 stuNo 추가하기
	    data: {'collNo':collNo, 'stuNo':stuNo}, //필요한 데이터
	    success: function(result) {
	   		//by수경 학과 select박스 선택
	   		const deptSelect = document.querySelector('.deptList');
	   		//by수경 학과 select박스 안에 option 태그 선택
	   		const optionTags = deptSelect.querySelectorAll('option');
			//by수경 태그를 비우자
			$('.deptList').empty(); 
		
			let str ='';
		
			for(const dept of result){ 
				str += `<option value="${dept.deptNo}">${dept.deptName}</option>`;
			}			

			deptSelect.insertAdjacentHTML('beforeend',str); 
		
	    },
	    error: function(){
	       alert('실패');
	    }
	});

	
}

//by수경 학생이 전과신청 버튼을 눌렀을 때
//학생이 현재 재학중인 학과를 선택했을 때 현재 재학중인 학과는 보이지 않도록 select box(ajax) 
//전과신청 버튼 클릭 시 유의사항 팝업창이 뜨고 확인버튼 누르면 
//전과신청이 완료 되었습니다. 팝업이 뜬 후 신청현황 페이지로 이동

function applyChangeMajor(){
	
	//by수경 전과신청(관리자 승인x, 현재 학기)내역이 있는지 확인
	const stuNo = document.querySelector('#stuNo').value;
	const applyCode = document.querySelector('#applyCode').value;
	
	$.ajax({
		url: '/emp/checkApplyAjax', //요청경로
		type: 'post',
		data: {'stuNo':stuNo,'applyCode':applyCode}, //필요한 데이터
		success: function(result) {
			if(result){
				 
				 Swal.fire({
				  title: '이미 신청하셨습니다.',
				  text: "신청내역 페이지로 이동합니다.",
				  icon: 'warning',
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: '확인'
				}).then((result) => {
				  if (result.isConfirmed) {
				    location.href=`/stu/stuApplyList?stuNo=${stuNo}`;
				  }
				})
				
				return;
			}
		},
		error: function() {
			alert('실패');
		}
	});
	
	//by수경 전공대학과 전공학과를 선택하지 않았다면
	const coll = document.querySelector('#coll').value;
	if(coll == ''){
		$().ready(function () {
            Swal.fire({
                icon: 'warning',
                title: '전공대학/전공학과 선택',
                text: '변경할 전공대학과 학과를 선택하여 주십시오.',
             });
	    });
		return;
	}
	
	const dept = document.querySelector('#dept').value;
	if(dept == ''){
		$().ready(function () {
            Swal.fire({
                icon: 'warning',
                title: '전공대학/전공학과 선택',
                text: '변경할 전공대학과 학과를 선택하여 주십시오.',
             });
	    });
		return;
	}
	
	
	//by수경 전과사유 공백 및 빈칸 방지
	const textArea = document.querySelector('textarea').value;
	//alert(textArea);

	if(textArea.replace(/\s| /gi, "").length == 0){
		
		$().ready(function () {
            Swal.fire({
                icon: 'warning',
                title: '전과 사유는 필수 입력사항',
                text: '전과 사유를 작성하여 주십시오.',
             });
        });     
		return;
	}
	

	//by수경 모든 정보가 입력되었을 때 전과 유의사항 모달창
	//모달창 소스
	const modal = new bootstrap.Modal('#changeMajorModal');
	//모달 보여주기
	modal.show();

}

//by수경 신청 쿼리 실행
function applyResult(){
	
	//동의합니다 체크박스
	const agreeCheckBox= document.querySelector('#agreeCheckBox');
	//동의합니다 체크박스에 체크표시가 되어 있다면
	const isChecked = agreeCheckBox.checked;
	
	//동의합니다에 체크되어 있다면
	if(isChecked){
	
		const stuNo = document.querySelector('#stuNo').value;
		const fromColl = document.querySelector('#collNo').value;
		const fromDept = document.querySelector('#deptNo').value;
		const applyReason = document.querySelector('#applyReason').value;
		const applyCode = document.querySelector('#applyCode').value;
		const toColl = document.querySelector('#coll').value;
		const toDept = document.querySelector('#dept').value;
		
		$.ajax({
			url: '/stu/applyChangeMajorAjax', //요청경로
			type: 'post',
			data: {
				'stuNo': stuNo, 'fromColl': fromColl,'fromDept': fromDept, 
				'applyReason': applyReason, 'applyCode': applyCode,
				'toColl':toColl, 'toDept':toDept
			}, //필요한 데이터
	
			success: function(result) {
	
				//모달창 소스
				const modal = new bootstrap.Modal('#applyResultModal');
				//모달 보여주기
				modal.show();
			},
	
			error: function() {
				alert('실패');
			}
		});
	}
	
	//by수경 동의합니다에 체크하지 않았다면 alert창 실행
	else{
		
		$().ready(function () {
            Swal.fire({
                icon: 'warning',
                title: '동의버튼 클릭',
                text: '전과 유의사항을 확인하시고 동의버튼을 클릭하여 주십시오.',
             });
        });     

		//모달창 소스
		const modal = new bootstrap.Modal('#changeMajorModal');
		//모달 보여주기
		modal.show();
		return;
	}
}

//by수경 신청현황을 보여주는 페이지로 이동
function nextPage(){
	
	const stuNo = document.querySelector('#stuNo').value;
	
	location.href=`/stu/stuApplyList?stuNo=${stuNo}`;
}

