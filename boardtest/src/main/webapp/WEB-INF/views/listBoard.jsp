<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시물 목록</h2>
<hr>
<table width=80% border=1 style="border: 1px solid black;">
<tr >
	<th>글번호</th>
	<th>글제목</th>
	<th>작성자</th>
</tr>
<c:forEach items="${list }" var="vo">
	<tr>
		<td>${vo.no }</td>
		<td><a href="detailBoard?no=${vo.no }">${vo.title }</a></td>
		<td>${vo.writer }</td>
	</tr>	
</c:forEach>
</table>
<a href="insertBoard">게시물 등록</a><br>
</body>
</html>