<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>item list</h2>
	<hr>
	<table border=1 width=50%>
		<tr>
			<th>item</th>
			<th>image</th>
		</tr>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td><a href="/detail?no=${vo.no }">${vo.item }</a></td>
				<td><img src="./images/${vo.fname }" width=200px></td>
			</tr>
		</c:forEach>
	</table>
	<ul>
	</ul>
	<a href="/insert"><button>insert new item</button></a>
</body>
</html>