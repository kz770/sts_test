<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function checkDelete(no){
	if(confirm("정말로 삭제할까요?")){
		location.href="delete?no="+no;
	}
}
</script>
</head>
<body>
	<table width=50% border=1>
		<tr>
			<td>item</td>
			<td>${vo.item }</td>
		</tr>
		<tr>
			<td>price</td>
			<td>${vo.price}</td>
		</tr>
		<tr>
			<td>image</td>
			<td><img src="./images/${vo.fname }" width=200px></td>
		</tr>
	</table>

	<a href="/update?no=${vo.no}"><button>edit</button></a>	
	<a onclick="checkDelete(${vo.no})"><button>delete</button></a>	
	<a href="/listAll?pageNUM=1"><button>go back to list</button></a>	
</body>
</html>