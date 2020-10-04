<%@page import="shoppingMallBean.ShoppingProduct"%>
<%@page import="DAOImp.BusinessServiceImp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<title>購物車頁面</title>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
 <script type="text/javascript">  
     function deleteItem(id){  
        var  b = window.confirm("确定要删除吗？");  
           if(b){  
                window.location.href="${pageContext.request.contextPath}/servlet/DeleteItemServlet?id="+id;             
           }  
        }  
        function clearcart(){  
        var  b = window.confirm("确定要清空您当前所选的商品吗？");  
           if(b){  
                window.location.href="${pageContext.request.contextPath}/ClearCartServlet";             
           }  
        }  
    function changequantity(input,id,oldvalue){  
           //得到修改的数量  
          var quantity = input.value;  
            
          //判断是否是正整数  
          if(quantity<0 || quantity!=parseInt(quantity)){  
                 alert("请输入正整数！！");  
                 input.value=oldvalue;  
                 return;  
          }  
           var  b = window.confirm("确定要将数量修改为："+quantity);  
           if(b){  
                window.location.href="${pageContext.request.contextPath}/Servlet/ChangeQuantitySevlet?id="+id+"&quantity="+quantity;             
           }  
         
       }  
      
    </script>  
<body>
<% BusinessServiceImp service=new BusinessServiceImp();%>>
<% int userchoose = (int) session.getAttribute("chooseType"); %>
<% int store=userchoose; %>
<form action="CartServlet" method="post" >  
<table cellpadding="2" cellspacing="1" border="1">
 <tr>  
   <th>productId</th>
   <th>productName</th>
   <th>productPrice</th>
   <th>productSPec</th>
   <th>數量</th>
   <th>小計</th>
   <th>操作</th>
  </tr>

<c:forEach var="item" items="${cart.map}">
     <tr>
      <td>${item.key}</td>
      <td>${item.value.product.productName}</td>
      <td>${item.value.product.productPrice}</td>
      <td>${item.value.product.productSpec}</td>
      <td>  
       <input type="text" name="quantity" value="${item.value.quantity}" onchange="changequantity(this,${item.key},${item.value.quantity})"/>
      </td>
      <td>${item.value.price}￥</td> 
      <td>  
       <%session.setAttribute("action","delItem");%>
       <button type="submit" name="id" value="${item.key}">刪除</button>
      </td>
     </tr>
  </c:forEach>
 <td colspan="4">總價</td>
 <td>${cart.price }￥</td>

 <td>
  <%session.setAttribute("action","delAll");%>
 <button type="submit" name="action" value="delAll">清空購物車</button>
 </td>
 <td>去結算</td>  
  <li><a href="http://localhost:8080/CampingWebsiteSystem/listcart.jsp?type=<%=userchoose%>">>繼續選購</a></li>

</table>
</form>
<form action="./CreateOrder" method="post">
<input type="submit" name="type">


</body>
</html>