<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>edit item</h2>
	<form action="/update" method="post" enctype="multipart/form-data"><br>
		no : <span>${vo.no }</span><input type="hidden" name="no" value="${vo.no }"><br>
		item : <input type="text" name="item" value="${vo.item }" ><br>
		qty : <input type="text" name="qty" value="${vo.qty }"><br>
		price : <input type="text" name="price" value="${vo.price }"><br>
		item image : <input type="file" name="uploadFile"><br>
		<input type="hidden" name="fname" value="${vo.fname }"><br>
		<input type="submit" value="edit complete">
	</form>
</body>
</html>