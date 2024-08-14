<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="delete">
		삭제를 위해 비밀번호를 입력해주세요!<br>
		<input type="hidden" name="no" value=${no }><br>
		<input type="password" name="pwd" ><br>
		<input type="submit" value="삭제하기"><br>
	</form>
</body>
</html>