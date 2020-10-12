<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<form action="Update" method="post">
<table>
<tr><td>mobile</td><td><input type="text" name="mobile"></td></tr>
<tr><td>password</td><td><input type="password" name="password"></td></tr>
<tr><td>name</td><td><input type="text" name="name"></td></tr>
<tr><td>nickname</td><td><input type="text" name="nickname"></td></tr>
<tr><td>gender</td><td>Male<input type="radio" name="gender" value="male">Female<input type="radio" name="gender" value="female"></td></tr>
<tr><td>birthday</td><td><input type="text" name="birthday"></td></tr>
<tr><td>E-mail</td><td><input type="text" name="email"></td></tr>
<tr><td>address</td><td><input type="text" name="address"></td></tr>
<tr><td></td><td><input type="submit" value="register"></td></tr>

</table>

</form>
</body>
</html>