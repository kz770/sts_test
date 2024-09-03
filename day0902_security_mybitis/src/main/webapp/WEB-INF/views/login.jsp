<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Log In</h2>
	<hr>
	<form action="/login" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		ID : <input type="text" name="username"/><br/>
		Password : <input type="text" name="password"/><br/>
		<input type="submit" value="login"/><br/>
		<input type="reset" value="reset"/><br/>
	</form>
</body>
</html>