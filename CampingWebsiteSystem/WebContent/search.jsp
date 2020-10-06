<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
<li>按照商品類別</li>
<c:forEach items="${applicationScope.clist}" var="cty">
<li><a href="ProductServlet?opt=byCategory&cid=${cty.categortId}">${cty.productName}</a></li>
</c:forEach>
</ul>
<span class="goodsbar-title">搜索结果
					<ul class="pull-right" id="pager">
					<li style="float: left;">当前第${searchProducts.currentPage}页,</li>
					<li style="float: left;">
						<c:if test="${searchProducts.currentPage<=1}">
							<a href="${ctxPath}/BookServlet?opt=search&page=${searchProducts.currentPage}">
							上一页
							</a>
						</c:if>
						<c:if test="${searchBooks.currentPage>1}">
							<a href="${ctxPath}/BookServlet?opt=search&page=${searchProducts.currentPage-1}">
							上一页
							</a>
						</c:if>
					</li>
					<li style="float: left;">
						<c:if test="${searchProducts.currentPage<searchProducts.totalPage}">
							<a href="${ctxPath}/BookServlet?opt=search&page=${searchProducts.currentPage+1}">
							下一页
							</a>
						</c:if>
						<c:if test="${searchProducts.currentPage>=searchProducts.totalPage}">
							<a href="${ctxPath}/BookServlet?opt=search&page=${searchProducts.totalPage}">
							下一页
							</a>
						</c:if>
					</li>
					<li style="float: left;">共${searchProducts.totalPage}页</li>
				</ul>
				</span>
					<div class="goodsbar-content">
						<!-- 搜索结果 -->
						<c:if test="${searchProducts ==null}" >
							没有搜索到相关图书！
						</c:if>
						<c:if test="${searchProducts !=null}" >
							<c:forEach items="${searchProducts.dataList}" var="book" >
								<dl class="bookList">
									<dt><a href="${ctxPath }/BookServlet?opt=buyBook&bid=${book.id}"><img src="images/book.png" class="" /></a></dt>
									<dd class="title"><a href="BookServlet?opt=buyBook&bid=${book.id}">${book.title}</a></dd>
									<dd class="price">￥${book.unitPrice}</dd>
									<dd class="info">${book.author}</dd>
									<dd class="intro"><!-- ${book.contentDescription} --></dd>
									<dd class=""><a href="${ctxPath }/BookServlet?opt=buyBook&bid=${book.id}" class="btn btn-danger">立即购买</a></dd>
								</dl>
							</c:forEach>
						</c:if>
					</div>
				</div>
				<!-- 分页 -->
				<ul class="pull-right" id="pager">
					<li style="float: left;">当前第${searchBooks.currentPage}页,</li>
					<li style="float: left;">
						<c:if test="${searchProducts.currentPage<=1}">
							<a href="${ctxPath}/BookServlet?opt=search&page=${searchProducts.currentPage}">
							上一页
							</a>
						</c:if>
						<c:if test="${searchProducts.currentPage>1}">
							<a href="${ctxPath}/BookServlet?opt=search&page=${searchProducts.currentPage-1}">
							上一页
							</a>
						</c:if>
					</li>
					<li style="float: left;">
						<c:if test="${searchProducts.currentPage<searchProducts.totalPage}">
							<a href="${ctxPath}/BookServlet?opt=search&page=${searchProducts.currentPage+1}">
							下一页
							</a>
						</c:if>
						<c:if test="${searchProducts.currentPage>=searchProducts.totalPage}">
							<a href="${ctxPath}/BookServlet?opt=search&page=${searchProducts.totalPage}">
							下一页
							</a>
						</c:if>
					</li>
					<li >共${searchProducts.totalPage}頁</li>
				</ul>
</body>
</html>