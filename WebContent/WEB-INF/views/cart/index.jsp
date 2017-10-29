<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cake cart</title>
<style type="text/css">

div.contentMain{
	 margin:0 auto; 
	 width:1200px;
	  height:auto; 
	  border:1px solid #F00
}

div.productItem{
margin-left:100px;
	width:1000px;
	height:120px;
	border:1px solid #F00
	}

div.cartTotal{
	margin-left:601px;
	width:500px;
	height:220px;
	border:1px solid #F00
} 
</style>
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
	
	<h1>购物车</h1>
		
	<c:set var="entries" value="${cartData.entries }"/>
	
	<div class="contentMain">
		
		<div class="productItems">
			<br>
			<c:forEach var="entry" items="${entries}">
				<br>
				<div class="productItem">
					<c:url var="productImg" value="${entry.product.imageUrl }"/>
					<c:url var="productUrl" value="/product/productDetail/${entry.product.id }"/>
					<a href="${productUrl }"><img alt="" src="${productImg}" style="width: 100px;height: 100px"/></a>
					${entry.product.name }&nbsp;${entry.price }&nbsp;${entry.quantity }&nbsp;${entry.totalPrice }
				</div>
				
			</c:forEach>
			<br>
			
			<div class="cartTotal">
				<span class="totalTitle">总计：￥${cartData.totalPrice }</span>
				<br>
				<button class="placeOrder"><spring:message code="cart.button.placeOrder"/></button>
			</div>
			<br>
		</div>	
	</div>
	
</body>
</html>