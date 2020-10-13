<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

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
<style>
.leftBar {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 200px;
	background-color: #f1f1f1;
}

.leftBara {
	display: block;
	color: #000;
	padding: 8px 16px;
	text-decoration: none;
}
.sidenav {
  height: 100%; /* Full-height: remove this if you want "auto" height */
  width: 160px; /* Set the width of the sidebar */
  top: 0; /* Stay at the top */
  left: 0;
  overflow-x: hidden; /* Disable horizontal scroll */ 
  float:left;
}

/* Change the link color on hover */
.leftBara:hover {
	background-color: #555;
	color: white;
}
.main {
  margin-left: 160px; /* Same as the width of the sidebar */
  padding: 0px 10px;

}
</style>

</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<nav>
	<div class="sidenav">
		<ul class="leftBar">
			<li><a
				href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=0"
				class="leftBara">露營帳篷</a></li>
			<li><a
				href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=1"
				class="leftBara">天幕/客廳帳篷/炊事帳篷</a></li>
			<li><a
				href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=2"
				class="leftBara">戶外寢具</a></li>
			<li><a
				href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=3"
				class="leftBara">地布</a></li>
			<li><a
				href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=4"
				class="leftBara">露營傢具</a></li>
			<li><a
				href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=5"
				class="leftBara">燈具</a></li>
			<li><a
				href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=6"
				class="leftBara">炊具</a></li>
			<li><a
				href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=7"
				class="leftBara">餐具</a></li>
			<li><a
				href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=8"
				class="leftBara">飲水/保冷</a></li>
			<li><a
				href="http://localhost:8080/CampingWebsiteSystem/list.jsp?type=9"
				class="leftBara">工具</a></li>
		</ul>
	</div>	
		<div class="main">
			<table class="table">
				<tr>
					<th>productId</th>
					<th>productName</th>
					<th>productPrice</th>
				</tr>
				<c:forEach items="${pageProducts}" var="cty">
					<tr>
						<td><a
							href="ProductServlet?opt=buyProduct&pid=${cty.productId}"
							onclick="incrementCounter()">${cty.productId}</a></td>
						<td><a
							href="ProductServlet?opt=buyProduct&pid=${cty.productId}"
							onclick="incrementCounter()">${cty.productName}</a></td>
						<td>${cty.productPrice}</td>
					</tr>
				</c:forEach>
			</table>
			<table class="table">
				<tr>
					<td><c:if test="${pageNo > 1}">
							<div id="pfirst">
								<a href="<c:url value='PageServlet?pageNo=1' />">第一頁</a>
							</div>
						</c:if></td>
					<td><c:if test="${pageNo > 1}">
							<div id="pprev">
								<a href="<c:url value='PageServlet?pageNo=${pageNo-1}' />">上一頁</a>
							</div>
						</c:if></td>
					<td><c:if test="${pageNo != totalPages}">
							<div id="pnext">
								<a href="<c:url value='PageServlet?pageNo=${pageNo+1}' />">下一頁</a>
							</div>
						</c:if></td>
					<td><c:if test="${pageNo != totalPages}">
							<div id="plast">
								<a href="<c:url value='PageServlet?pageNo=${totalPages}' />">最末頁</a>
							</div>
						</c:if></td>
					<td rowspan=2>第${pageNo}頁 / 共${totalPages}頁</td>

				</tr>
			</table>





		</div>

		<hr></hr>
		<h3>關鍵字搜尋</h3>
		<form action='PageServlet'>
			<input type="text" name='keyWord'></input> <input type="hidden"
				name="searchType" value="keyWord"> <input type="submit"
				value="送出"></input>
		</form>
		<hr></hr>
		<h3>價錢範圍搜尋</h3>
		<form action='SearchServlet'>
			<input type="hidden" name="searchType" value="price"></input> <input
				type="hidden" name="category_id" value="2"></input> <input
				type="text" name='minPrice'></input>~ <input type="text"
				name='maxPrice'></input> <input type="submit" value="送出"></input>
		</form>
		<hr></hr>
		<h3>訂單查詢(輸入會員編號)</h3>
		<form action='OrderListServlet'>
			<input type="text" name='memberID'></input> <input type="submit"
				value="送出"></input>
		</form>
		<h3>管理系統</h3>
		<a href="<c:url value='admin/login.jsp' />">管理系統</a>
	</nav>
</body>
</html>