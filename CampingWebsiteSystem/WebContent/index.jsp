<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首頁 商品頁面</title>
</head>
<body>
<nav>
    <ul>
        <li><a href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=0">露營帳篷</a></li>
        <li><a href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=1">天幕/客廳帳篷/炊事帳篷</a></li>
        <li><a href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=2">戶外寢具</a></li>
         <li><a href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=3">地布</a></li>
        <li><a href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=4">露營傢具</a></li>
        <li><a href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=5">燈具</a></li>
        <li><a href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=6">炊具</a></li>
         <li><a href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=7">餐具</a></li>
        <li><a href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=8">飲水/保冷</a></li>
        <li><a href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=9">工具</a></li>
    
    </ul>
   <table cellpadding="3" cellspacing="1" border="1">
    <tr>  
    <th>productId</th>
    <th>productName</th>
    <th>productPrice</th>
    <th></th>
    </tr>
    <c:forEach items="${applicationScope.clist}" var="cty">
      <tr>
      <td>
      <a href="ProductServlet?opt=buyProduct&pid=${cty.productId}">${cty.productId}</a>
      </td>
      <td>
	  <a href="ProductServlet?opt=buyProduct&pid=${cty.productId}">${cty.productName}</a>
	  </td>
	  <td>
	  ${cty.productPrice}
	  </td> 
	  </tr> 
	</c:forEach>
	</table>
    <input tpye="text" >關鍵字搜尋</input>
     
</nav>
</body>
</html>