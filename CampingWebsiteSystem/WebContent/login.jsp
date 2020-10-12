<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入</title>
<style>

h1{
color:gray}
form{
height: 300px;
width: 300px;
border: 9px rgb(189, 137, 137) solid;
}
input{
border:none;
border-radius: 5px;
}

.d1{
border:1px solid red;
height:55px;
width:100px;
}
.c1{border:0;
  background-color:gray;
  color:#fff;
  border-radius:10px;
  cursor:pointer;}

.c1:hover{
  color:black;
  background-color:gray;
  
}
h3{
color:red}

</style>


</head>
<body>
<div align="center">

<form action="login" method="post">
<h1>WELCOME</h1>
<table>


<tr><td><svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-phone" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M11 1H5a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM5 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H5z"/>
  <path fill-rule="evenodd" d="M8 14a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
</svg></td><td><input type="text" name="mobile"  placeholder="mobile" ></td></tr>
<tr><td><svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-lock" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M11.5 8h-7a1 1 0 0 0-1 1v5a1 1 0 0 0 1 1h7a1 1 0 0 0 1-1V9a1 1 0 0 0-1-1zm-7-1a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h7a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2h-7zm0-3a3.5 3.5 0 1 1 7 0v3h-1V4a2.5 2.5 0 0 0-5 0v3h-1V4z"/>
</svg></td><td><input type="password" name="password" placeholder="password"></td></tr>
<tr><td></td><td><center>
<input  class="c1" type="submit" name="Login" value="登入"><input class="c1" type="button" value="註冊" onclick="location.href='member-form.jsp'"></center></td></tr>

</table>
<h3>${message}</h3>
<h3>${smessage}</h3>

</form>
</div>





</body>
</html>