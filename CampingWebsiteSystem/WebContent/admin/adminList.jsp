<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<body>
<h2 class="text-center">管理員列表</h2>
<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">id</th>
      <th scope="col">帳號</th>
      <th scope="col">密碼</th>
      <th scope="col">姓名</th>
      <th scope="col">操作</th>
    </tr>
  </thead>
  <tbody>
    <c:choose>
			<c:when  test="${!empty adminList}">
					<c:forEach items="${adminList}" var="i" varStatus="n">
						<tr>
							<td><input type="checkbox" name="choice" value="${i.id}">
							<td>${i.id}</td>
							<td>${i.userName}</td>
							<td>${i.passWord}</td>
							<td>${i.name}</td>
							<td>
								<a class="btn btn-info btn-sm" href="jsp/admin/AdminManageServlet?action=edit&id=${i.id}">修改</a>
								<a class="btn btn-danger btn-sm" href="jsp/admin/AdminManageServlet?action=del&id=${i.id}" onclick="javascript:return confirm('确定要删除吗？');">删除</a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="8"><h4 class="text-center">沒有管理者了</h4></td>
					</tr>
				</c:otherwise>
			</c:choose>
  </tbody>
</table>
		
		
		
		
		
		
		
		
		
		
		
	
<script type="text/javascript">
	
	//按钮禁用限制
	if("${pageBean.curPage}"==1){
		$(".homePage").attr("disabled","disabled");
		$(".prePage").attr("disabled","disabled");
	}
	if("${pageBean.curPage}"=="${pageBean.pageCount}"){
		$(".page-go").attr("disabled","disabled");
		$(".nextPage").attr("disabled","disabled");
		$(".lastPage").attr("disabled","disabled");
	}
	//按钮事件
	$(".homePage").click(function(){
		window.location="${basePath}jsp/admin/AdminManageServlet?action=list&page=1";
	})
	$(".prePage").click(function(){
		window.location="${basePath}jsp/admin/AdminManageServlet?action=list&page=${pageBean.prePage}";
	})
	$(".nextPage").click(function(){
		window.location="${basePath}jsp/admin/AdminManageServlet?action=list&page=${pageBean.nextPage}";
	})
	$(".lastPage").click(function(){
		window.location="${basePath}jsp/admin/AdminManageServlet?action=list&page=${pageBean.pageCount}";
	})
	//控制页面跳转范围
	$(".page-go").click(function(){
		var page=$("#page-input").val();
		var pageCount=${pageBean.pageCount};
		if(isNaN(page)||page.length<=0){
			$("#page-input").focus();
			alert("请输入数字页码");
			return;
		}
		if(page > pageCount || page < 1 ){
			alert("输入的页码超出范围！");
			$("#page-input").focus(); 
		}else{
			window.location="${basePath}jsp/admin/AdminManageServlet?action=list&page="+page;
		}
	})
	
	//批量选中
	$("#batDelChoice").change(function(){
		if(!$("input[name='choice']").prop("checked")){
			$(this).prop("checked",true);
			$("input[name='choice']").prop("checked",true);
			
		}else{
			$(this).removeProp("checked");
			$("input[name='choice']").removeProp("checked");
		}	
	})
	
	
	
	$("#batDel").click(function(){
		var choices=$("input:checked[name='choice']");
		var ids="";
		for(i=0;i<choices.length;i++){
			if(i!=choices.length-1){
				ids+=choices.eq(i).val()+",";
			}else{
				ids+=choices.eq(i).val();
			}
		}
		if(ids==""){
			alert("请勾选要删除的内容！");
			return;
			
		}
		if(confirm("你确定要删除"+choices.length+"条用户吗？")){
			window.location="${basePath}jsp/admin/AdminManageServlet?action=batDel&ids="+ids;
		}
	})
	
	
	
</script>

</body>
</html>