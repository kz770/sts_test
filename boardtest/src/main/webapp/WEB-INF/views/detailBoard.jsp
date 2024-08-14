<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>post detail</h2>
	<hr>
	no : ${vo.no }<br>
	title : ${vo.title }<br>
	writer : ${vo.writer }<br>
	content : <br>
	<textarea rows="10" cols="80" disabled>${vo.content }</textarea><br>
	regdate : ${vo.regdate }<br>
	attached file :<br>
	<img src="./upload/${vo.fname }" width="200px"><br>
	ip : ${vo.ip }<br>
	<a href="/update?no=${vo.no }">update</a>
	<a href="/delete?no=${vo.no }">delete</a>
	<a href="/listBoard">go back to list</a>
</body>
</html>