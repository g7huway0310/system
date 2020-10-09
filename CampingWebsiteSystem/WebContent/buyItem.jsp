<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<style>
table, td, th {  
  border: 1px solid #ddd;
  text-align: left;
}

table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  padding: 15px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>商品詳情</title>
</head>
<body>
	<form action="CartServlet?opt=add&id=${buyProduct.productId}"
		method="post">
		<table>
		<tr>
		<th>產品名稱</th>
		<th>產品價格</th>
		<th>產品庫存</th>
		<th>pic</th>
		</tr>
		<tr>
		<td>${buyProduct.productPrice}</td>
		<td>${buyProduct.productName}</td>
		<td>${buyProduct.productStack}</td>
		<td> <img alt="dd" src="/pt/${buyProduct.productId}-img.jpg"> </td>
		</tr>
		</table>
			<select name="num">
              <option value=1>1</option>
              <option value=2>2</option>
              <option value=3>3</option>
              <option value=4>4</option>
              <option value=5>5</option>
            </select>
		<br>
		<button type="submit">加入購物車</button>
		<h4>詳細資訊</h4>
		<hr>
		<table width="100%">
			<tr>
				<th>產品名稱</th>
				<th>產品規格</th>
				<th>產品警告</th>
				</tr>
				<tr>
				<td>${buyProduct.productSpec}</td>
				<td>${buyProduct.productName}</td>
				<td>${buyProduct.productWarring}</td>
			</tr>
		</table>


	</form>

</body>
</html>