//by수경 검색조건 검색 후 새로고침 했을 때 전공대학과 전공학과 불러오기
const collNo = document.querySelector('#coll').value;

if(collNo == 'COLL_001' || collNo == 'COLL_002' || collNo == 'COLL_003' || collNo == 'COLL_004'){
	//소속대학 소속학과 조회
	changeColl();
}

//by수경 학생을 조회하기 위한 소속대학 소속학과 조회
function changeColl(){
	
	const collNo = document.querySelector('#coll').value;
	
	$.ajax({
	   url: '/emp/deptListAjax', //요청경로
	    type: 'post',
	    data: {'collNo':collNo}, //필요한 데이터
	    success: function(result) {
	   		//by수경 학과 select박스 선택
	   		const deptSelect = document.querySelector('.deptList');
	   		//by수경 학과 select박스 안에 option 태그 선택
	   		const optionTags = deptSelect.querySelectorAll('option');
			
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

//by수경 카카오톡 메세지 전송
function sendKakao(){
		
	Kakao.Share.sendCustom({
	  templateId: 86136,
	  templateArgs: {
	    title: '제목 영역입니다.',
	    description: '설명 영역입니다.',
	  },
	
	});

}