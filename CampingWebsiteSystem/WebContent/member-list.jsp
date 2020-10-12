<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>會員</title>
 <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color:rgb(189, 137, 137)">
		<%-- 	<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> User
					Management Application </a>
			</div>--%>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">會員</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">會員</h3>
			<hr>
			<div class="container text-left">

			<%-- 	<a href="<%=request.getContextPath()%>/new" class="btn btn-success">增</a> --%>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>手機</th>
						<th>密碼</th>
						<th>姓名</th>
						<th>別名</th>
						<th>性別</th>
						<th>生日</th>
						<th>email</th>
						<th>地址</th>
						
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="member" items="${listMember}">

						<tr>
							<td><c:out value="${member.mobile}" /></td>
							<td><c:out value="${member.password}" /></td>
							<td><c:out value="${member.name}" /></td>
							<td><c:out value="${member.nickname}" /></td>
							<td><c:out value="${member.gender}" /></td>
							<td><c:out value="${member.birthday}" /></td>
							<td><c:out value="${member.email}" /></td>
							<td><c:out value="${member.address}" /></td>
							
							
							<td><a href="edit?mobile=<c:out value='${member.mobile}' />">改</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?mobile=<c:out value='${member.mobile}' />">刪</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>