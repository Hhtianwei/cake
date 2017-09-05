<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
		<a href="${login }">login</a>&nbsp;<a href="${register }">register</a>
	</c:if>
	<br>
	<hr>
	<h1>展示商品信息</h1>
	<hr>
	...
	<hr>
</body>
</html>