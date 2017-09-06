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
	<title>注册结果</title>
	<link href="<%=path%>/ui/css/main.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/ui/javascript/jquery/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/ui/javascript/register.js"></script>
</head>
<body>
	<h2>用户注册</h2>
	<br>
	<c:url var="login" value="/login"/>
	<h2>恭喜您，注册成功，请<a href="${login }">登录</a></h2>
</body>
</html>