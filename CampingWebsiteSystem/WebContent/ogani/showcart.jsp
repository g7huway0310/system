<%@page import="shoppingMallBean.ShoppingProduct"%>
<%@page import="DAOImp.BusinessServiceImp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="jqajax_select.js"></script>
<script type="text/javascript">
function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='CartServlet?opt=delItem&id="+n+"'/>"
		                         
		document.forms[0].method="POST";
		document.forms[0].submit();
		console.log("刪除")
	} else {
		console.log("未刪除")
	}
}
function deAll(){
	document.forms[0].action="<c:url value='CartServlet?opt=delAll'/>"
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function checkout(){
	document.forms[0].action="<c:url value='BuyServlet'/>"
	document.forms[0].method="POST";
	document.forms[0].submit();
}


function modify(key, qty, index) {
	
	var x = "newQty" + index;
	
	var newQty = document.getElementById(x).value;
	
	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		return ; 
	}
	if  (newQty == 0 ) {
		window.alert ("請執行刪除功能來刪除此項商品");
		document.getElementById(x).value = qty;
		return ; 
	}
	if  (newQty == qty ) {
		window.alert ("新、舊數量相同，不必修改");
		return ; 
	}
	if (confirm("確定將此商品的數量由" + qty + " 改為 " + newQty + " ? ") ) {
		document.forms[0].action="<c:url value='CartServlet?opt=changeIn&id=" + key + "&quantity=" + newQty +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		document.getElementById(x).value = qty;
	}
}

</script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<title>購物車頁面</title>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<body>
	<% BusinessServiceImp service=new BusinessServiceImp();%>>

	<table border="1">
		<tr>
		    <th>productID</th>
			<th>productName</th>
			<th>productPrice</th>
			<th>規格</th>
			<th>數量</th>
			<th>小記</th>
			<th colspan=2>操作</th>
		</tr>
		
		<c:forEach varStatus="vs" var="item" items="${cart.map}">
		
			<c:set var="id" value="${item.key}" />
			<tr>
			    <td>${item.key}</td>
				<td><a href="ProductServlet?opt=buyProduct&pid=${item.key}">${item.value.product.productName}</a></td>
				<td>${item.value.product.productPrice}</td>
				<td>${item.value.product.productSpec}</td>
				<td><select id="newQty${vs.index}"   name="quantity"
					value="${item.value.quantity} onchange="update()";>
						<option value="1" ${item.value.quantity == '1' ? 'selected' : ''}>1</option>
						<option value="2" ${item.value.quantity == '2' ? 'selected' : ''}>2</option>
						<option value="3" ${item.value.quantity == '3' ? 'selected' : ''}>3</option>
						<option value="4" ${item.value.quantity == '4' ? 'selected' : ''}>4</option>
						<option value="5" ${item.value.quantity == '5' ? 'selected' : ''}>5</option>
				</select></td>
				<td>${item.value.price}元</td>
				
				<td>
			    <input type="button" id="delete" value="刪除"
					onclick="confirmDelete('${item.key}')"></input></td>
				<td>
				<Input type="button" name="update" value="修改" onclick="modify('${item.key}', ${item.value.quantity}, ${vs.index})">	
			    </td>
			</tr>
		</c:forEach>
		
		<td colspan="4">總價</td>
		
		<td>${cart.price }￥</td>

		<td>
			<%session.setAttribute("action","delAll");%>
			<Input type="button" name="update" value="清空購物車" onclick="deAll()"></Input>
		</td>
		<td colspan="2"><Input type="button" name="checkout" value="結算" onclick="checkout()"></Input></td>
		
		<a >>繼續選購</a>
		
	</table>
	<form>
		<input type="hidden" name="a" />
	</form>

</body>
</html>