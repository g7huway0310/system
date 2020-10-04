<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@page import="DAOImp.ShoppingDAOImp,shoppingMallBean.ShoppingProduct"%> 
<h1>search Result</h1>
<c:forEach var="node" items="${brandSearch}"> 
    <p>${node.productName}</p>
</c:forEach>
<c:forEach var="type" items="${typeSearch}"> 
    <p>${type.productName}</p>
    <p>${type.productStack}</p>
</c:forEach>
</body>
</html>