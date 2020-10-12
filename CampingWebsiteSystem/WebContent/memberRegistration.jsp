<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>

        form{
            height: 600px;
            width: 600px;
            border: 1px rgb(189, 137, 137) solid;
        }

    </style>
</head>
<body>

<form action="Register" method="post" >
<div align="center">
<h1>會員註冊</h1><br>
手機:<input type="text" name="mobile" placeholder="輸入手機"><br><br><br>
密碼:<input type="password" name="password" placeholder="輸入密碼"><br><br><br>
姓名:<input type="text" name="name" placeholder="輸入姓名"><br><br><br>
別名:<input type="text" name="nickname" placeholder="輸入別名"><br><br><br>
性別:男<input type="radio" name="gender" value="male">女<input type="radio" name="gender" value="female"><br><br><br>
生日:<input type="date" name="birthday" placeholder="輸入生日"><br><br><br>
E-mail:<input type="text" name="email" placeholder="輸入email"><br><br><br>
地址:<input type="text" name="address" placeholder="輸入地址"><br><br><br>

<input type="submit" value="確認送出">


</div>
</form>
</body>
</html>