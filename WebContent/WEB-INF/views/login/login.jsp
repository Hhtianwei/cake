<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>

<link href="<%=path%>/ui/css/main.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/ui/javascript/jquery/jquery-2.1.1.min.js"></script>

</head>
<body>
	
<br>
 登录页面:
 		<c:if test="${not empty errorMsg}">
 			<font color="red">${errorMsg }</font>
 		</c:if>
 	<c:url var="login" value="/login"/>
	<form id="login-form" method="post" action="${login}">
		userName:<input type="text" name="uname" value="${uname }"/><br>
		password:<input type="password" name="password"/><br>
		<br>
		remember-me:<input type="checkbox" name="remember-me"/>
		<br>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" name="提交"/>
		<br>
	</form>
	<br>
	
</body>
</html>