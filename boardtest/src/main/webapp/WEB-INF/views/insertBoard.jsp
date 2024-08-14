<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 등록</h2>
	<hr>
	<form action="insertBoard" method="post" enctype="multipart/form-data">
		title : <input type="text" name="title"><br>
		writer : <input type="text" name="writer"><br>
		password : <input type="password" name="pwd"><br>
		contents : <br>
		file upload : <input type="file" name="uploadFile"><br>
		<textarea rows="10" cols="80" name="content"></textarea><br>
		<input type="submit" value="등록">
	</form>
</body>
</html>