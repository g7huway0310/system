<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>
新增貼文
</h2>
<form action="./ArticleServlet" method="post">

<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr>
    <td>貼文標題:</td>
    <td><input type="text" name="Title" size="38" maxlength="38"></td>
</tr>

<tr>
    <td>文章內容:</td>
    <td><input type="text" name="Text" size="38"></td>
</tr>

<tr>
    <td>會員號碼:</td>
    <td><input type="text" name="Memberid" maxlength="38"></td>
</tr>


</table>

<input type="submit" name="delete" value="送出">

</form>




</body>
</html>