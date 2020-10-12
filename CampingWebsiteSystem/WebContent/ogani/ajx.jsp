<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function getData(){
	//內建原生和伺服器連線
    var req=new XMLHttpRequest();
	req.open("get","http://localhost:8080/CampingWebsiteSystem/index.jsp")
	req.onload=function(){
         alert(this.responseText); 
	}	
	req.send();
}


</script>



</head>


<body>
<div>
  <span onclick="getData();">123123</span>
</div>
</body>
</html>