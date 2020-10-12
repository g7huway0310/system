<%@page import="article.ArticleBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章列表</title>
</head>
<body>
<table border="1" width="1800px" align"center">
	<tr>
		<td colspan="2" align="center" bgcolor="lightblue"><h2>文章列表</h2></td>
		<td colspan="2" align="center" bgcolor="lightblue"><h2>內容</h2></td>
	</tr> 
	
	<tr>
		<th aligh="center" width="300px">MemberID</th>
			
		<th aligh="center" width="500px">Title</th>

		<th aligh="center" width="1000px">Text</th>
	</tr>

<c:forEach var="article" items="${alist}">
	<tr>
		<td>${article.memberid}</td>
		<td>${article.title}</td>
		<td>${article.text}</td>
	
	</tr>

</c:forEach>



<%
	ArrayList<ArticleBean> list = (ArrayList<ArticleBean>)request.getAttribute("alist"); 
	for(int i = 0; i<list.size(); i++){
		ArticleBean articleBean = list.get(i);
		System.out.println("text = "+articleBean.getText());
		System.out.println("title = "+articleBean.getTitle());
		System.out.println("ID = "+articleBean.getMemberid());
%>		
		<tr>
			<td><%= list.get(i).getMemberid() %></td>
			<td><%=list.get(i).getTitle() %></td>
			<td><%=list.get(i).getText() %></td>
		</tr>

<%		
	}
%>

	

</table>


</body>
</html>