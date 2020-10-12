<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增貼文內容確認</title>
</head>
<body>
<jsp:useBean id="rel_Article" class="article.ArticleBean" scope="session"/>
<h2>
新增貼文如下請確認
</h2>
<form action=".\ArticleServlet" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">

<tr bgcolor="#FFFFE1">
    <td>貼文標題:</td>
   
    <td><jsp:getProperty name="rel_Article" property="title" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>貼文內容:</td>
    <td><jsp:getProperty name="rel_Article" property="text" /></td>
</tr>

<tr bgcolor="#FFFFE1">
    <td>貼文內容:</td>
    <td><jsp:getProperty name="rel_Article" property="ariticleid" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>會員編號:</td>
    <td><jsp:getProperty name="rel_Article" property="memberid" /></td>
</tr>

</table>
<input type="submit" name="submit" value="confirm">
</form>
</body>
</html>