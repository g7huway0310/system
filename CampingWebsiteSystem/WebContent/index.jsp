<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首頁 商品頁面</title>
<script type="text/javascript">
 function init() {
        let count = localStorage.getItem('counter');
        if(count === null){
            count = 0;
            localStorage.setItem('counter', count);
        }
        count = parseInt(count);
        updateCount(count);
    }
    function incrementCounter() {
        let count = parseInt(localStorage.getItem('counter'));
        count = count + 1;
        localStorage.setItem('counter', count);
        updateCount(count);
        return true;
    }
    function updateCount(count) {
        document.getElementById("count").innerHTML = "Clicked "+count+" times!";
    }
</script>
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
    <c:forEach items="${pageProducts}" var="cty">
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
        <c:if test="${pageNo > 1}">
           <div id="pfirst">
              <a href="<c:url value='PageServlet?pageNo=1' />">第一頁</a>
           </div>
        </c:if>
     </td>
     <td width='76'>
        <c:if test="${pageNo > 1}">
           <div id="pprev">
              <a href="<c:url value='PageServlet?pageNo=${pageNo-1}' />">上一頁</a>
           </div>
        </c:if>  
     </td>
     <td width='76'>
            <c:if test="${pageNo != totalPages}">
                <div id="pnext">
                   <a href="<c:url value='PageServlet?pageNo=${pageNo+1}' />">下一頁</a>
                </div>
            </c:if>
     </td>  
     <td width='76'>
            <c:if test="${pageNo != totalPages}">
                <div id="plast">
                    <a href="<c:url value='PageServlet?pageNo=${totalPages}' />">最末頁</a>
                </div>
            </c:if>
     </td>
     <td width='176' align="center">
                      第${pageNo}頁 / 共${totalPages}頁
     </td>  
</tr>
</table>	
<hr></hr>
<h3>關鍵字搜尋</h3>
<form action='PageServlet'>
<input type="text" name='keyWord'></input>
<input type="hidden" name="searchType" value="keyWord">
<input type="submit" value="送出"></input>
</form>
<hr></hr>
<h3>價錢範圍搜尋</h3>
<form action='SearchServlet'>
<input type="hidden" name="searchType" value="price"></input>
<input type="hidden" name="category_id" value="2"></input>
<input type="text" name='minPrice'></input>~
<input type="text" name='maxPrice'></input>
<input type="submit" value="送出"></input>
</form>
</nav>
</body>
</html>