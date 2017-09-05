<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户列表</title>
	<link href="<%=path%>/ui/css/main.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/ui/javascript/jquery/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/ui/javascript/register.js"></script>
</head>
<body>
	<h2>用户注册</h2>
	<br>
	<c:url var="register" value="/register"/>
	
	<form:form action="${register }" method="post" commandName="customerForm">
	
		<font color="red"><form:errors path="name"/></font><br>
		用户名：<form:input path="name"/><br>
		
		<font color="red"><form:errors path="nickName"/></font><br>
		昵    称：<form:input path="nickName"/><br>
		
		<font color="red"><form:errors path="mobile"/></font><br>
		手机号：<form:input path="mobile"/><br>
		
		<font color="red"><form:errors path="email"/></font><br>
		邮    箱：<form:input path="email"/><br>
		
		<font color="red"><form:errors path="password"/></font><br>
		密    码：<form:password path="password"/><br>
		
		<font color="red"><form:errors path="confirmPassword"/></font><br>
		再次输入密码：<form:password path="confirmPassword"/><br>		
		 <input type="submit" value="register"/><br>
	</form:form>
	
</body>
</html>