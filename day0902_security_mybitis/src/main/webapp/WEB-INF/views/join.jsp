<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Registraition</h2>
	<form action="/join" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><br>
		id : <input type="text" name="id"><br>
		name : <input type="text" name ="name"><br>
		password : <input type="text" name="pwd"><br>
		<input type="submit" value="submit"><br>
	</form>
</body>
</html>