<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 取得今天的日期，今天的日期應當在最後確認時才取得 -->
<jsp:useBean   id="today"  class="java.util.Date" scope="session"/> 
<title>Insert title here</title>
</head>
<script type="text/javascript">
function cancelOrder() {
	if (confirm("確定取消此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[0].finalDecision.value = "CANCEL";
		document.forms[0].action="<c:url value='ProcessOrder.do' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return;
	} else {
		return;
	}
}
function reconfirmOrder() {
	var sa = document.getElementById('ShippingAddress').value;
	if  (sa === "") {
		window.alert ('出貨地址不能是空白');
		return ; 
	}
	if (confirm("確定送出此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[0].finalDecision.value = "ORDER";
		document.forms[0].action="<c:url value='ProcessOrder.do' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return;
	} else {
		return;
	}
}
</script>
<body>
	<FORM style="margin: 0 auto; width: 700px;"
		action="<c:url value='ProcessOrder' />" method="POST">
		<table border='1'>
			<tr>
				<!--<td border-style: ridge;>會員編號：${LoginOK.memberId}</td>  -->
				<td border-style: ridge;>訂購人:<input type="text" name="orderPerson"></input></td>
				<td border-style: ridge;>訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/></td>
				<td border-style: ridge;>出貨地址:<input type="text" name="Address"> </td>
				<td border-style: ridge;>付款方式 <input type="text" name="Pay"></input> </td>
			</tr>
		</table>
		<table border='1'>
			<tr>
				<th style="text-align:center;font-size: 12pt;" width="350">商品名稱:</th>
				<th style="text-align:center;font-size: 12pt;" width="150">價錢:</th>
				<th style="text-align:center;font-size: 12pt;" width="200">數量:</th>
			</tr>
			 <c:forEach var="p" items="${cart.map}">
			<tr>
			    <td border-style: ridge;>${p.key}</td> 
			    <td border-style: ridge;>${p.value.price}</td>
			    <td border-style: ridge;>${p.value.quantity}</td>
			</tr>
	    </c:forEach>
	        <tr>
	        <td></td>
	        </tr>
		</table>
	</FORM>
	
    <input type="button" name="OrderBtn"  value="確定送出" onclick="reconfirmOrder()">
    
    <input type="button" name="CancelBtn" value="取消訂單" onclick="cancelOrder()">

</body>
</html>