<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#loginForm, #box_check{
		display: none;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	let codeNUM;
	
	$("#btnSendEmail").click(function(){
		let email = $("#email").val();
		let data= {email:email};
		$.ajax({
			url:"/sendCode",
			data:data,
			success:function(data){
				codeNUM= data;
				$("#box_check").css("display","inline-block");
			}
		});
	});
	
	$("#btnCheckNUM").click(function(){
		let checkNUM = $("#checkNUM").val();
		if(checkNUM ==codeNUM ){
			$("#loginForm").css("display","inline-block");
			$(".check").css("display","none");
		}else{
			alert("인증실패");
		}
	});
});
</script>

</head>
<body>
	<h2>회원가입</h2>
	<hr>
	<div id="box_email" class="check">
		이메일 : <input type="email" id="email">
		<button id="btnSendEmail">인증코드 전송</button>
	</div>
	
	<div id="box_check" class="check">
		인증번호 입력 : <input type="text" id="checkNUM">
		<button id="btnCheckNUM">확인</button>
	</div>
	
	<form method="post" action="join" id="loginForm">
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="password" name="pwd"><br>
		이름 : <input type="text" name="name"><br>
		이메일 : <input type="text" name="email"><br>
		전화번호 : <input type="text" name="phone"><br>
		<input type="submit" value="가입">
		<input type="reset" value="다시입력">
	</form>
	
	
</body>
</html>