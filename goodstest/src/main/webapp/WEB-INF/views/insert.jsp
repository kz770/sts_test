<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>new item</h2>
	<form action="/insert" method="post" enctype="multipart/form-data"><br>
		no : <input type="text" name="no"><br>
		item : <input type="text" name="item"><br>
		qty : <input type="text" name="qty"><br>
		price : <input type="text" name="price"><br>
		item image : <input type="file" name="uploadFile"><br>
		<input type="submit" value="insert new item">
	</form>
</body>
</html>