/*by 유빈*/
//alert('js 이동확인');

//-------------------------------------------------------------------//
//---[파일첨부 추가 버튼 클릭시 진행 함수]
const mainImgDiv = document.querySelector('#mainImgDiv');
const addFileBtn = document.querySelector('#addFileBtn');

mainImgDiv.addEventListener('change', function (event) {
  //alert('누름!');
  mainImgDiv.innerText = '';
  let str = '';
  str += '<button id="addFileBtn" class="btn btn-light" type="button" onclick="goAddFile()";>추가</button>';
  addFileBtn.insertAdjacentHTML('afterbegin',str);
  
});
buttonElement.addEventListener('click', {
  handleEvent: function (event) {
    alert('handleEvent 함수로 누름!');
  }
});

//------[ 파일첨부하기  함수] ---------------------------------------------------//
function goAddFile(){
  const subImgsDiv = document.querySelector('#subImgsDiv');
  //alert('!!!');
  subImgsDiv.innerText = '';
  let str = '';
  str += `
			 ※ 2개이상 이미지 파일 선택하여 첨부 가능합니다.
		 	<input class="form-control" type="file" name="subImgs" multiple>           
		`;
  subImgsDiv.insertAdjacentHTML('afterbegin',str);
}

//------[ 등록버튼 클릭시 진행 함수]  ---------------------------------------------------//
 /*Swal.fire({
	title: '[ 글등록 완료]',
	text: "입력하신 글이 등록되었습니다.",
	icon: 'success',
	showCancelButton: false, 
	confirmButtonColor: '#3085d6',
	confirmButtonText: '확인',
	cancelButtonText: '취소'
	}).then((result) => {
		if (result.isConfirmed) {
		location.href='/board/list';
	}
}) */
///////////////////////////////////////////////////////////////////////////////
//------------[ 카테고리명 빈값 유효성 검사  ]--------------//
//변수선언
/*var boardTitle = document.getElementById("boardTitle");
alert(boardTitle);
var boardContent = document.getElementById("boardContent");
alert(boardTitle);
//함수선언
function validatecateName(){
  if(boardTitle.value == '' ) { // 만일 두 인풋 필드값이 같지 않을 경우
	  boardTitle.setCustomValidity("제목글을 입력해주세요."); 
	  if(boardContent.value == '' ) {
		  boardContent.setCustomValidity("내용을 입력해주세요."); 
	  }
  } 
  else { // 만일 두 인풋 필드값이 같을 경우
    // 오류가 없으면 메시지를 빈 문자열로 설정해야한다. 오류 메시지가 비어 있지 않은 한 양식은 유효성 검사를 통과하지 않고 제출되지 않는다.
    // 따라서 빈값을 주어 submit 처리되게 한다
    boardTitle.setCustomValidity(''); 
    boardContent.setCustomValidity(''); 
    // 등록 버튼클릭시, 이벤트 실행
    document.getElementById("regBtn").onclick = function(){
    	Swal.fire({
    		title: '[ 글등록 완료]',
    		text: "입력하신 글이 등록되었습니다.",
    		icon: 'success',
    		showCancelButton: false, 
    		confirmButtonColor: '#3085d6',
    		confirmButtonText: '확인',
    		cancelButtonText: '취소'
    		}).then((result) => {
    			if (result.isConfirmed) {
    			location.href='/board/list';
    		}
    	})
	} 
  }
  
}

// 함수실행
boardTitle.onchange = validatecateName;
boardContent.onchange = validatecateName;

*/















