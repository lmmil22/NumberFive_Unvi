//by 지아 수정버튼 클릭시 진행
function updateMemInfo(memNo, btn){
	if(btn.dataset.status == 1){
		setDetail(memNo);
		btn.dataset.status = 2
	}
	else{
		const memInfoForm = document.querySelector("#memInfoForm")
 		memInfoForm.submit();
	}
	
	
}

function setDetail(memNo){
	const tell = document.querySelector("#tell")
	const addr = document.querySelector("#addr")
	const email = document.querySelector("#email")
	
	
	const origin_tell = tell.innerText;
	const origin_addr = addr.innerText;
	const origin_email = email.innerText;
	tell.innerText = '';
	addr.innerText = '';
	email.innerText = '';

	tell.insertAdjacentHTML('afterbegin', `<input type="text" name="memTell" value="${origin_tell}">`);
	addr.insertAdjacentHTML('afterbegin', `<input type="text" name="memAddr" value="${origin_addr}">`);
	email.insertAdjacentHTML('afterbegin', `<input type="text" name="memEmail" value="${origin_email}">`);
	
}


