<%@page import="shoppingMallBean.ShoppingProduct"%>
<%@page import="DAOImp.BusinessServiceImp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% BusinessServiceImp service=new BusinessServiceImp();%>
<% int choose = Integer.valueOf(request.getParameter("type"));%>

<table cellpadding="2" cellspacing="1" border="1">
 <tr>  
   <th>productId</th>
   <th>productName</th>
   <th>productPrice</th>
    <th>addToCart</th>
  </tr>
<form action="CartServlet" method="post" >
<c:forEach var="p" items="<%=service.searchtype(choose)%>">
     <tr>
      <td>${p.productId}</td>
      <td>${p.productName}</td>
      <td>${p.productPrice}</td>
      <td>
      <%session.setAttribute("chooseType",choose);%>
      <%session.setAttribute("action","add");%>
      <button type="submit" name="id" value="${p.productId}">Submit</button>
     
      </td>
     </tr>
</c:forEach>
</form>
<input type="range" min="0" max="10">
 
</table>
   <li><a href="http://localhost:8080/CampingWebsiteSystem/listcart.jsp?type=0">回首頁</a></li>
  <li><a href="http://localhost:8080/CampingWebsiteSystem/HomePage.jsp">回首頁</a></li>
</body>
</html>