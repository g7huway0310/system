<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table cellpadding="3" cellspacing="1" border="1">
    <tr>  
    <th>productId</th>
    <th>productName</th>
    <th>productPrice</th>
    <th></th>
    </tr>
    <c:forEach items="${searchpageProducts}" var="cty">
      <tr>
      <td>
      <a href="ProductServlet?opt=buyProduct&pid=${cty.productId}" onclick="incrementCounter()">${cty.productId}</a>
      </td>
      <td>
	  <a href="ProductServlet?opt=buyProduct&pid=${cty.productId}" onclick="incrementCounter()">${cty.productName}</a>
	  </td>
	  <td>
	  ${cty.productPrice}
	  </td> 
	  </tr> 
	</c:forEach>
	</table>
<table border="1">
  <tr>
    <td width='76'>
        <c:if test="${searchPageNo > 1}">
           <div id="pfirst">
              <a href="<c:url value='PageServlet?searchPageNo=1&searchJSP=123' />">第一頁</a>
           </div>
        </c:if>
     </td>
     <td width='76'>
        <c:if test="${searchPageNo > 1}">
           <div id="pprev">
              <a href="<c:url value='PageServlet?searchJSP=123&searchPageNo=${searchPageNo-1}' />">上一頁</a>
           </div>
        </c:if>  
     </td>
     <td width='76'>
            <c:if test="${searchPageNo != totalPages}">
                <div id="pnext">
                   <a href="<c:url value='PageServlet?searchJSP=123&searchPageNo=${searchPageNo+1}'/>">下一頁</a>
                </div>
            </c:if>
     </td>  
     <td width='76'>
            <c:if test="${searchPageNo != totalPages}">
                <div id="plast">
                    <a href="<c:url value='PageServlet?searchJSP=123&searchPageNo=${searchtotalPages}' />">最末頁</a>
                </div>
            </c:if>
     </td>
     <td width='176' align="center">
                      第${searchPageNo}頁 / 共${searchtotalPages}頁
     </td>  
</tr>
</table>	
</body>
</html>