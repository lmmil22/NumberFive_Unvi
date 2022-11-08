//by 수경 전과신청 시 전공대학, 전공학과 선택 select ajax
//전과 또는 복수전공 신청할 전공대학, 전공학과 선택할 때 
//본인의 현재 소속 대학, 학과는 제외 되도록 한다.
function changeColl(){
	
	const collNo = document.querySelector('select').value;
	//by수경 제외하기 위해서 매개변수로 stuNo 필요 
	//const stuNo = document.querySelector('#stuNo');
	
	$.ajax({
	   url: '/stu/getMajorAjax', //요청경로
	    type: 'post',
	    // 추후 stuNo 추가하기
	    data: {'collNo':collNo}, //필요한 데이터
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
				alert('이미 신청하셨습니다. \n신청내역 페이지로 이동합니다.');
				location.href=`/stu/stuApplyList?stuNo=${stuNo}`;
				return;
			}
		},
		error: function() {
			alert('실패');
		}
	});
	
	
	//const toColl = document.querySelector('#coll option:selected').value;
	//const toDept = document.querySelector('#dept option:selected').value;
	//alert(toColl);
	//alert(toDept);
	
	
	//by수경 전과사유 공백 및 빈칸 방지
	const textArea = document.querySelector('textarea').value;
	//alert(textArea);

	if(textArea.replace(/\s| /gi, "").length == 0){
		alert('전과 사유는 필수 입력 사항입니다. \n전과 사유를 작성하여 주십시오.');
		return;
	}

	//by수경 모든 정보가 입력되었을 때 전과 유의사항 모달창
	//모달창 소스
	const modal = new bootstrap.Modal('#changeMajorModal');
	//모달 보여주기
	modal.show();

}

function applyResult(){
	
	const stuNo = document.querySelector('#stuNo').value;
	const fromColl = document.querySelector('#collNo').value;
	const fromDept = document.querySelector('#deptNo').value;
	const applyReason = document.querySelector('#applyReason').value;
	const applyCode = document.querySelector('#applyCode').value;
	
	
	
	 //모달창 소스
	const modal = new bootstrap.Modal('#applyResultModal');
	//모달 보여주기
	modal.show();	
	
	
}

