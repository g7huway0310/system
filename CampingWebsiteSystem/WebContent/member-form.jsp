<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>會員</title>
<style>

input{
	light-height:999em;
	}
.c1{
border: 9px rgb(189, 137, 137) solid;
width:600px;
padding:10px;

}

.p1{border:0;
  background-color:gray;
  color:#fff;
  border-radius:10px;
  cursor:pointer;}

.p1:hover{
  color:black;
  background-color:gray;
  
}
input{

border:1px gray solid;
border-top:none;
border-right:none;
border-left:none;
outline:none

}

</style>
<%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
<script type="text/javascript">

function chkName() {
    let theNameObj = document.getElementById("name");
    
    let theNameVal = theNameObj.value;
    let sp = document.getElementById("idsn")
    let theNameValLen = theNameVal.length
    var regx = /[^\u4e00-\u9fa5]$/;
    let flag1 = true;
    if (theNameVal == "")
        sp.innerHTML =  "姓名不可空白";
    else if (theNameValLen >= 2) {

        if (!regx.test(theNameVal)) {

            flag1 = true;
            sp.innerHTML = "正確";

        } else {

            sp.innerHTML = "請輸入中文"
        }
    }

    else {
        sp.textContent = "請輸入兩字以上";
    }
}


    // for(let i=0;i<theNameValLen;i++){

    //     let ch = theNameObjVal.charAt(i)


</script>




</head>
<body>
<center>


	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${member != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${member == null}">
					<form action="insert" method="post">
				</c:if>

			<%-- 	<caption>
					<h2>
						<c:if test="${member != null}">
            			修改
            		</c:if>
						<c:if test="${member == null}">
            			註冊
            		</c:if>
					</h2>
				</caption>--%>

				<c:if test="${member != null}">
					<input type="hidden" name="id" value="<c:out value='${member.mobile}' />" />
				</c:if>
				<div class="c1">
					<label>手機:</label><br> <input type="text"
						value="<c:out value='${member.mobile}'  />" class="form-control"
						name="mobile" required="required" pattern="[0-9]{10}"     >
					<br><br>
				
					<label>密碼:</label><br> <input type="password"
						value="<c:out value='${member.password}' />" class="form-control"
						name="password" required="required">
				
					<br><br>
				
					<label>姓名:</label><br> 
					<input type="text" value="<c:out value='${member.name}' />"  class="form-control" id="name" name="name" onchange='chkName()' />
					<div id="idsn"></div>
						
						
				
					<br><br>
				
					<label>別名:</label><br> <input type="text"
						value="<c:out value='${member.nickname}' />" class="form-control"
						name="nickname">
				
					<br><br>
				
					<label>性別:</label> 男<input type='radio' value="<c:out value='male' />"class="form-control" name="gender">
									        女<input type='radio' value="<c:out value='female' />"class="form-control" name="gender">
				
				<br><br>
					<label>生日:</label> <br><input type="date"
						value="<c:out value='${member.birthday}' />" class="form-control"
						name="birthday">
				
				<br><br>
					<label>E-mail</label><br> <input type="email"
						value="<c:out value='${member.email}' />" class="form-control"
						name="email">
				<br><br>
				
					<label>地址:</label><br> <input type="text"
						value="<c:out value='${member.address}' />" class="form-control"
						name="address">
				<br><br>

				<button class="p1" type="submit" class="btn btn-success" >確認</button>
				</div>
				</form>
			
			</div>
		</div>
	</div>
</body>
</html>