<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cake 后台管理</title>
</head>
<body>
	<c:set var="hasLogin" value="false"/>
	<c:if test="${not empty sessionScope.customerData }">
		<c:set var="hasLogin" value="true"/>
	</c:if>
	<c:if test="${hasLogin }">
		当前登录用户是<font color="red">&nbsp;${sessionScope.customerData.nickName }</font>
		<c:url var="logoutUrl" value="/logout"/>
		<form action="${logoutUrl}" method="post">
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    <input type="submit" value="Log out" />
		</form>
	</c:if>
	<c:if test="${!hasLogin }">
		未登录
		<br>
		<c:url var="login" value="/login"/>
		<c:url var="register" value="/register"/>
		<a href="${login }">登录</a>&nbsp;<a href="${register }">注册</a>
	</c:if>
	<br>
	<hr>
	<h1>列表</h1>
	<hr>
	
		<c:url var="addProduct" value="/admin/addProduct"/>
		<c:url var="updateProduct" value="/admin/updateProduct"/>
		<c:url var="deleteProduct" value="/admin/deleteProduct"/>
		<c:url var="productManage" value="/admin/productManage"/>
		
		<c:set var="pagination" value="${searchResults.pagination }"/>
		<c:set var="currentPage" value="${pagination.currentPage }"/>
		<c:set var="results" value="${searchResults.results }"/>
		
		<a href="${addProduct }">新增产品</a>
		<br/>
		<table border="1">
			<thead>
				<tr>
					<th>序号</th>
					<th>编号</th>
					<th>名称</th>
					<th>长名</th>
					<th>形状</th>
					<th>价格</th>
					<th>大小</th>
					<th>数量</th>
					<th>图片</th>
					<th colspan="2">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${results }" varStatus="index">
					<tr>
						<td>${index.count }</td>
						<td>${product.id }</td>
						<td>${product.name }</td>
						<td>${product.longName }</td>
						<td>${product.shape }</td>
						<td>${product.price }</td>
						<td>${product.size }</td>
						<td>${product.stock }</td>
						<td>图片</td>
						<td><a href="${updateProduct }?id=${product.id }">修改</a></td>
						<td><a href="${deleteProduct }?id=${product.id }">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${currentPage-1 > 0 }">
			<a href="${productManage }?currentPage=${currentPage-1 }">上一页</a>
		</c:if>
		<span>${currentPage }</span>
		<c:if test="${currentPage < pagination.totalPages }">
			<a href="${productManage }?currentPage=${currentPage+1 }">下一页</a>
		</c:if>
</body>
</html>