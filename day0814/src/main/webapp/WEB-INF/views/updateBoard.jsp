<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 수정</h2>
	<hr>
	<form action="updateBoard" method="post" enctype="multipart/form-data">
		글제목 : <input type="text" name="title" value="${b.title }"><br>
		작성자 : ${b.writer }<br>
		글암호 : <input type="password" name="pwd"><br>
		글내용 : <br>		
		<textarea rows="10" cols="80" name="content">${b.content }</textarea><br>
		<input type="hidden" name="fname" value="${b.fname }">		
		<input type="hidden" name="fsize" value="${b.fsize }">		
		<input type="hidden" name="no" value="${b.no }">		
		첨부파일 : ${b.fname }(${b.fsize })<br> 
		<input type="file" name="uploadFile"><br>
		<input type="submit" value="수정">
		<input type="reset" value="다시입력">
	</form>
</body>
</html>















