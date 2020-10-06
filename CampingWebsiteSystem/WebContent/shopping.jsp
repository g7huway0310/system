<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜尋商品</title>
<h3>依照品牌搜尋全部商品</h3>
</head>
<body>
   <form action="./ShoppingServlet" method="post">
   <input type="text" name="key" size="10" maxlength="10">
   <input type="submit" name="brand">
   <br>
   <br>
   
   <form action="./ShoppingServlet" method="post">
   <input type="text" name="typekey" size="10" maxlength="10">
   <input type="submit" name="type">

</body>
</html>