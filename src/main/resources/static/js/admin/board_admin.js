/*by 유빈*/
//---------------- 스크립트 실행과 동시에 필요한 변수 생성 ---------------------//
//제목줄 체크박스
const checkAll = document.querySelector('#checkAll');

//제목줄 제외한 장바구니 체크박스
const chks = document.querySelectorAll('.chk');

//
//let catetNosInput =document.querySelector('#catetNos').value;
//----------------------------- 이벤트 정의 영역 -------------------------------//
//////////////////////////////////////////////////////////////////////////
//전체선택, 전체해제 이벤트
checkAll.addEventListener('click',function(){
	//제목 줄 체크박스 체크여부
	const isChecked = checkAll.checked;	//true, false
	
	//카테고리 목록 모든 체크박스
	const checkBoxes = document.querySelectorAll('.chk');
	
	//제목줄 체크박스가 체크되었다면
	if(isChecked){
		
		//모든 체크박스에 체크 표시
		for(const checkBox of checkBoxes){
			checkBox.checked = true;
		}
	}
	
	else{
		//모든 체크박스 체크 해제
		for(const checkBox of checkBoxes){
			checkBox.checked = false;
		}
	}
});

/////////////////////////////////////////////////////////////////////
//제목줄 아래 체크박스칸들이 전부! 체크가 된다면..
	//제목줄 아래 체크박스칸들이 전부! 가져온다.
	
	for(const e of chks){
		
		//위의 이벤트문을 조금 변경한다면..
		e.addEventListener('click',e=>{
			const cnt = chks.length; //제목줄 밑의 전체 체크박스의 갯수는 cnt
			const checkedCnt = document.querySelectorAll('.chk:checked').length; //클래스가 chk 인 것들 중에서 체크된것만 들고오겠다.그것의 갯수.
		
		if(cnt == checkedCnt){
			checkAll.checked = true;
		}
		
		else{
			checkAll.checked = false;
					
		}		
	});	
}

/////////////////////////////////////////////////////////////////////
/////////-------------선택삭제 버튼 클릭------------///////
function deleteCate(selectedTag){
	
	const deleteForm = document.querySelector('#cateForm');
	//체크한 cartCode 다 들고 온다.(cartCode값은 체크박스안에  value값과 같다)
	const checkedChks = document.querySelectorAll('.chk:checked');
	//체크한 cartCode 데이터 다 가져오기
	const checkedCnt = document.querySelectorAll('.chk:Checked').length;	
		
		//--선택한 상품이 없을 때--//
		if(checkedCnt == 0){
			Swal.fire({
				title: '[ 체크박스 선택 필요 ]',
				text: "선택된 게시판의 카테고리가 없습니다.",
				icon: 'warning',
				showCancelButton: false, 
				confirmButtonColor: '#3085d6',
				confirmButtonText: '확인',
				cancelButtonText: '취소'
				}).then((result) => {
				if (result.isConfirmed) {
				location.href=`/board/boardAdmin`;
				}
			})
			
			return;
		}
		
		//--선택된 체크박스들 있을 때--//
		let catetNos = '';
		for(const checkedChk of checkedChks){
			catetNos = catetNos + checkedChk.value + ',';
		}
		
	catetNosInput = catetNos;
	
	//action값 바꾸기
	if(selectedTag.innerText == '선택삭제'){
		deleteForm.action = '/board/deleteCate?cateNos='+ catetNosInput;
		
	}
	else{
		deleteForm.action = '/board/boardAdmin'
	}
	
	deleteForm.submit();
	document.getElementById("deleteBtn").onclick = function(){
	Swal.fire({
				title: '[ 삭제 완료 ]',
				text: "선택된 게시판의 카테고리이 삭제되었습니다.",
				icon: 'success',
				showCancelButton: false, 
				confirmButtonColor: '#3085d6',
				confirmButtonText: '확인',
				cancelButtonText: '취소'
				}).then((result) => {
				if (result.isConfirmed) {
				location.href=`/board/boardAdmin`;
				}
			})
	}

}