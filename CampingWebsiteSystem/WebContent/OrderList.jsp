<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<body>
<jsp:include page="header.jsp"></jsp:include>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">訂單編號</th>
				<th scope="col">訂購日期</th>
				<th scope="col">總金額</th>
				<th scope="col">送貨地址</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${!empty memberOrders}">
					<c:forEach items="${memberOrders}" var="i" varStatus="n">
						<tr>
							<td><input type="checkbox" name="choice" value="${i.orderNo}">
							<td><a
								href='<c:url value='orderDetail.do?memberId=${LoginOK.memberId}&orderNo=${anOrderBean.orderNo}' />'>
									${i.orderNo} </a></td>
							<td>${i.orderDate}</td>
							<td>${i.totalAmount}</td>
							<td>${i.shippingAddress}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="8"><h4 class="text-center">沒有訂單</h4></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

</body>
</html>