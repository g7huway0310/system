<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>新增資料確認</title>

</head>
<body>
<jsp:useBean id="rel_Comment" class="comment.CommentBean" scope="session"/>
<h2>
新增資料如下請確認
</h2>
<form action=".\CommentServlet" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">

<tr bgcolor="#FFFFE1">
    <td>營地編號:</td>
   
    <td><jsp:getProperty name="rel_Comment" property="campgroundid" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>會員號碼:</td>
    <td><jsp:getProperty name="rel_Comment" property="memberid" /></td>

<tr bgcolor="#FFFFE1">
    <td>評價等級:</td>
    <td><jsp:getProperty name="rel_Comment" property="ranking" /></td>
</tr>

<tr bgcolor="#F2F4FB">
    <td>評語:</td>
    <td><jsp:getProperty name="rel_Comment" property="content" /></td>
</tr>
</table>
<input type="submit" name="confirm" value="確認">
</form>
</body>
</html>