<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<style>
.sidenav {
  height: 100%; /* Full-height: remove this if you want "auto" height */
  width: 160px; /* Set the width of the sidebar */
  top: 0; /* Stay at the top */
  left: 0;
  overflow-x: hidden; /* Disable horizontal scroll */ 
  float:left;
  
}
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

</body>
</html>