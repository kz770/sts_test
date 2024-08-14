<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>update my posting</h2>
	<hr>
	<form action="update" method="post" enctype="multipart/form-data">
		title : <input type="text" name="title" value="${vo.title }"><br>
		writer : <span>${vo.writer }</span><br>
		password : <input type="password" name="pwd"><br>
		contents : <br>
		<textarea rows="10" cols="80" name="content">${vo.content }</textarea><br>
		<input type="hidden" name="fname" value="${vo.fname }">
		<input type="hidden" name="fsize" value="${vo.fsize }">
		<input type="hidden" name="no" value="${vo.no }">
		file upload : ${vo.fname }(${vo.fsize })<br>
		<input type="file" name="uploadFile"><br>
		<input type="submit" value="수정완료">
		
	</form>
</body>
</html>