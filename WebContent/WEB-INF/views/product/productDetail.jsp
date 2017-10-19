<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cake home</title>

<style type="text/css">
.currentSize{
	color: red;
}
.currentStock{
	color: red;
}
.currentPrice{
	color: red;
}
.hidden{
	display: none;
}

</style>

<script type="text/javascript" src="<%=path%>/ui/javascript/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/ui/javascript/product.js"></script>
</head>
<body>
	<c:set var="hasLogin" value="false"/>
	<c:if test="${not empty sessionScope.customerData }">
		<c:set var="hasLogin" value="true"/>
	</c:if>
	这个是产品信息，
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

	<c:set var="productId" value=""/>
	<hr>
	<table border="1" align="center">
		<tbody>
			
			<tr>
				<td></td>
				<td>
					<c:url var="productImage" value="${product.imageUrl }"/>
					<img alt="" src="${productImage }">
				</td>
			</tr>
			<tr>
				<td>产品名称</td>
				<td>${product.name }</td>
			</tr>
			<tr>
				<td>产品描述</td>
				<td>${product.longName }</td>
			</tr>
			<tr>
				<td>源产地</td>
				<td>${product.location }</td>
			</tr>
			<tr>
				<td>形状</td>
				<td>${product.shape }</td>
			</tr>

			<tr>
				<td>尺寸</td>
				<td>
					<c:forEach var="subProduct" items="${product.subProductDatas }" varStatus="status">
						<c:if test="${status.index == 0 }">
							<c:set var="productId" value="${subProduct.id}"/>
						</c:if>
						<span productId="${subProduct.id }" class="sizePro ${(status.index == 0)?'currentSize':''}">${subProduct.size }</span>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>价格</td>
				<td>
					<c:forEach var="subProduct" items="${product.subProductDatas }" varStatus="status">
						<span productId="${subProduct.id }" class="pricePro ${(status.index == 0)?'currentPrice':'hidden'}">${subProduct.price }</span>
					</c:forEach>
				</td>
			</tr>
			
			<tr>
				<td>库存</td>
				<td>
					<c:forEach var="subProduct" items="${product.subProductDatas }" varStatus="status">
						<span productId="${subProduct.id }" class="stockPro ${(status.index == 0)?'currentStock':'hidden'}">${subProduct.stock }</span>
					</c:forEach>
				</td>
			</tr>
		</tbody>
	</table>
	
	<hr>
	<c:url var="addToCart" value="/cart/addToCart"/>
	<a href="${addToCart }?productId=${productId}"  id="addToCart">加入购物车</a>
	<br>
	<br>
	<br>
</body>
</html>