// 이메일 중복 체크
const input = document.getElementById("email");

const p = document.getElementById("result");

// blur : 선택된 요소에서 포커스가 사라졌을 때
//input.addEventListener("blur", request );

const request = ()=> {
	
	let url = "check";
	
	let email = input.value;
	
	fetch(url + "?email=" + email)
		 .then(res=>res.text())
		 .then(data=>{
		    console.log(data);

            if(data == 't'){
                p.innerHTML="사용가능한 이메일 입니다.";
                p.style="color : green";
            }else{
                p.innerHTML="중복된 이메일 입니다.";
                p.style="color : red";
            }
		 })
		 .catch(err=>{
		    console.log(err);
		 });
	
}









