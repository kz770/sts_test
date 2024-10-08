<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
#op {
	display: none;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#searchColumn").change(function(){
			let c_name=$(this).val();
			if(c_name=="price"){
				$("#op").css("display","inline-block");
			}else{
				$("#op").css("display","none");
			}
		});
	})
</script>
</head>
<body>
	<div id="container">
		<h2>list all</h2>
		<form action="listAll" method="get">
			<select name="searchColumn" id="searchColumn">
				<option value="item">상품명</option>
				<option value="price">가격</option>
			</select>
			<select name="op" id="op">
				<option value="=">=</option>
				<option value=">=">>=</option>
				<option value="<="><=</option>
				<option value=">">></option>
				<option value="<"><</option>
				<option value="!=">!=</option>
			</select> <input type="search" name="keyword" value="${keyword }"> <input type="hidden"
				value="1" name="pageNUM"> <input type="submit" value="검색">
		</form>
		<br> 총 ${totalRecord }개의 상품이 있습니다. <br> <br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th><a href="listAll?orderColumn=no&pageNUM=1">NO</a></th>
					<th><a href="listAll?orderColumn=item&pageNUM=1">ITEM</a></th>
					<th>IMAGE</th>
					<th><a href="listAll?orderColumn=price&pageNUM=1">PRICE</a></th>
					<th><a href="listAll?orderColumn=qty&pageNUM=1">QTY</a></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.no }</td>
						<td><a href="/detail?no=${vo.no }">${vo.item }</a></td>
						<td><img src="./images/${vo.fname }" width=200px></td>
						<td>${vo.price }</td>
						<td>${vo.qty }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr>
		<c:forEach var="i" begin="1" end="${totalPage }">
			<a href="listAll?pageNUM=${i }">${i }&nbsp&nbsp</a>
		</c:forEach>
		<br> 총 ${totalPage }페이지
	</div>
</body>
</html>