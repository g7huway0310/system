<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
			<!--  
			<c:if test="${user==null }">
				<h2>尚未登入</h2>
				<a href="${ctxPath }/login.jsp">立刻登入</a> <% //缺少登入系統%>
			</c:if> 
			--> 
			
			<br><br><br><br><br>
			<span style="color:#7abd54;font-size:2em;">
				<h1>商品已加入購物車</h1>
				<a href="CartServlet?opt=show" class="btn btn-danger">去購物車結算</a>
				<br>
				<span>可以<a href="${ctxPath }/index.jsp">繼續選購</a></span>
			</span>			
</div>

</body>
</html>