<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<h2>list Book</h2>
	유저 아이디 : ${id }
	
	<a href="logout">LOGOUT</a>
	
	<h4>유저 전체 정보</h4>
	${member.id } <br>
	${member.pwd } <br>
	${member.name } <br>
	${member.role } <br>
</body>
</html>