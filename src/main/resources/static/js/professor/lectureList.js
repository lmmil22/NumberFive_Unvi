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
function changeLecDetail(){
	

	//모달창 띄우는 소스 작성
	const Modal = new bootstrap.Modal('#changeLecModal');
	Modal.show();



}