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
   <th>購買數量</th>
  </tr> 
<c:forEach var="p" items="<%=service.searchtype(choose)%>">
     <tr>
      <td>${p.productId}</td>
      <td><a href="ProductServlet?opt=buyProduct&pid=${p.productId}">${p.productName}</td></a>
      <td>${p.productPrice}</td>
      <td> <img alt="圖片讀取失敗" src="/pt/${p.productId}-img.jpg"> </td>
      <td>
      <FORM  action="<c:url value='CartServlet?opt=add' />" method="POST">
                                購買數量:
               <select name='num'>
                    <option value=1>1</option>
                    <option value=2>2</option>
                    <option value=3>3</option>
                    <option value=4>4</option>
                    <option value=5>5</option>
                    <option value=6>6</option>
                    <option value=7>7</option>
                    <option value=8>8</option>
                    <option value=9>9</option>
                    <option value=10>10</option>
               </select>
               <!-- 這些隱藏欄位都會送到後端 -->
               <Input type='hidden' name='id' value='${p.productId}'>
               <Input type='hidden' name='name' value='${p.productName}'>
               <Input type='hidden' name='spec' value='${p.productSpec}'>
               <Input type='hidden' name='warring' value='${p.productWarring}'>
               <Input type='hidden' name='price' value='${p.productPrice}'>
               <Input type='hidden' name='feature' value='${p.productfeature}'>
               <Input type='submit' value='加入購物車'>
       </FORM>
       <td>
     </tr>
</c:forEach>
</form>
</table>
   <li><a href="http://localhost:8080/CampingWebsiteSystem/listcart.jsp?type=0">回首頁</a></li>
  <li><a href="http://localhost:8080/CampingWebsiteSystem/HomePage.jsp">回首頁</a></li>
</body>
</html>