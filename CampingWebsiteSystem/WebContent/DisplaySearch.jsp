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


<%
	ArrayList<ArticleBean> atlsrh = (ArrayList<ArticleBean>)request.getAttribute("atlsrh"); 
	for(int i = 0; i<atlsrh.size(); i++){
		ArticleBean articleBean = atlsrh.get(i);
		System.out.println("text = "+articleBean.getText());
		System.out.println("title = "+articleBean.getTitle());
		System.out.println("ID = "+articleBean.getMemberid());
%>		
		<tr>
			<td><%=atlsrh.get(i).getMemberid() %></td>
			<td><%=atlsrh.get(i).getTitle() %></td>
			<td><%=atlsrh.get(i).getText() %></td>
		</tr>

<%		
	}
%>

	

</table>


</body>
</html>