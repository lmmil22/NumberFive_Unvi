/*by 유빈*/
//alert('js 이동확인');
//-------------------------------------------------------------------//
//[파일첨부 추가 버튼 클릭시 진행 함수]
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

//
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

