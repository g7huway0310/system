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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<style>
.main {
  margin-left: 160px; /* Same as the width of the sidebar */
  padding: 0px 10px;
}
</style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<jsp:include page="Leftbar.jsp"></jsp:include>

<% BusinessServiceImp service=new BusinessServiceImp();%>

<% int choose = Integer.valueOf(request.getParameter("type"));%>

<div class=main>
<table class="table table-striped">
 <tr>  
   <th>productId</th>
   <th>productName</th>
   <th>productPrice</th>
   <th>圖片</th>
   <th>數量</th>
  </tr> 

<c:forEach var="p" items="<%=service.searchtype(choose)%>">
     <tr>
      <td>${p.productId}</td>
      <td><a href="ProductServlet?opt=buyProduct&pid=${p.productId}">${p.productName}</a></td>
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
               <Input type='hidden' name='choose' value=<%=choose%>>
               <Input type='submit' value='加入購物車' class="form-control">
       </FORM>
       <td>
     </tr>
</c:forEach>
</form>
</table>
</div>
   
</body>
</html>