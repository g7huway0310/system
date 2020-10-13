<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8"%>
<%
	String appContext = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + appContext;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理系統</title>
</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<script type="text/javascript">
	function checkForm() {
		var userName = document.getElementById("userName");
		var passWord = document.getElementById("passWord");
		if (userName.value.length <= 0) {
			alert("請輸入名稱！");
			userName.focus();
			return false;
		}
		if (passWord.value.length <= 0) {
			alert("請輸入密碼！");
			passWord.focus();
			return false;
		}
		return true;
	}
</script>
<body>
	<h1 id="title">
		後臺管理員
		<div id="login">
			<form action="../Adminlogin" method="post"
				onsubmit="javascript:return checkForm()">
				<p>
					<input type="text" name="userName" id="userName" placeholder="名稱">
				</p>
				<p>
					<input type="password" name="passWord" id="passWord"
						placeholder="密碼">
				</p>
				<p>
					<input type="submit" id="submit" value="登入">
				</p>
			</form>
		</div>
</body>
</html>