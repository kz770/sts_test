<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>도서목록</h2>
<hr>
<c:forEach item="${list }" var="vo">
	${vo.bookid }
	${vo.bookname }
	${vo.publisher }
	${vo.price }
</c:forEach>
</body>
</html>