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
	<title>个人中心</title>
	<link href="<%=path%>/ui/css/main.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/ui/javascript/jquery/jquery-2.1.1.min.js"></script>
</head>
<body>
	<hr>
	<br>
	<h2>修改密码</h2>
	<br>
	<c:url var="updatePassword" value="/updatePassword"/>
	<form:form action="${updatePassword}" commandName="passwordForm" method="post">
		
		<font color="red"><form:errors path="oldPassword"></form:errors></font><br>
		<label>请输入原密码：</label>	<label><form:password path="oldPassword"/></label>
		<br>
		<font color="red"><form:errors path="newPassword"></form:errors></font><br>
		<label>请输入新密码：</label>	<label><form:password path="newPassword"/></label>
		<br>
		<font color="red"><form:errors path="confirmPassword"></form:errors></font><br>
		<label>请再次输入新密码：</label>	<label><form:password path="confirmPassword"/></label>
		<br>
		<input type="submit" value="重置密码">
	</form:form>
	<hr>
	<br>
		<c:url var="home" value="/"/>
		<a href="${home}">返回主页</a>
		<c:url var="account" value="/account"/>
		<a href="${account}">返回个人中心</a>
	<hr>
</body>
</html>