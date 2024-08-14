<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 상세</h2>
	<hr>
	글번호 : ${b.no }<br>
	글제목 : ${b.title }<br>
	글내용 : <br>
	<textarea rows="10" cols="80">${b.content }</textarea><br>
	등록일 : ${b.regdate }<br>
	ip: ${b.ip }<br>
	
	<a href="updateBoard?no=${b.no }">수정</a>&nbsp;&nbsp;
	<a href="deleteBoard?no=${b.no }">삭제</a>
	<a href="insertBoard?no=${b.no }">답글작성</a>
</body>
</html>














