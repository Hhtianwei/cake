<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cake home</title>
</head>
<body>
	<c:set var="hasLogin" value="false"/>
	<c:if test="${not empty sessionScope.customerData }">
		<c:set var="hasLogin" value="true"/>
	</c:if>
	这个是首页，
	<c:if test="${hasLogin }">
		当前登录用户是<font color="red">&nbsp;${sessionScope.customerData.nickName }</font>
		<c:url var="logoutUrl" value="/logout"/>
		<form action="${logoutUrl}" method="post">
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    <input type="submit" value="Log out" />
		</form>
		&nbsp;
		<c:url var="account" value="/account"/>
		<a href="${account }">个人中心</a>
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
	<h1>展示商品信息</h1>
	<hr>
	<c:set var="results" value="${searchResults.results }"/>
	<c:forEach var="product" items="${results }">
		<c:url var="productImg" value="${product.imageUrl}"/>
		<c:url var="productDetail" value="product/productDetail/${product.id }"/>
		<ul>
			<li>
				<a href="${productDetail }"><img src="${productImg }"/></a>
			</li>
			<li>
				<a href="${productDetail }">${product.name }</a>
			</li>
		</ul>
	</c:forEach>
	<hr>
	
	<c:set var="pagination" value="${searchResults.pagination }"/>
	<c:set var="currentPage" value="${pagination.currentPage }"/>
	
	<c:url var="productList" value="/product/productList"/>
	
	<c:if test="${currentPage-1 > 0 }">
			<a href="${productList }?currentPage=${currentPage-1 }">上一页</a>
		</c:if>
		<span>${currentPage }</span>
		<c:if test="${currentPage < pagination.totalPages }">
			<a href="${productList }?currentPage=${currentPage+1 }">下一页</a>
		</c:if>
</body>
</html>