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
	<title>用户信息更新 </title>
	<link href="<%=path%>/ui/css/main.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/ui/javascript/jquery/jquery-2.1.1.min.js"></script>
</head>
<body>
	<h2>用户资料修改</h2>
	<br>
	<c:url var="update" value="/updateCustomerInfo"/>
	
	<form:form action="${update }" method="post" commandName="customerForm">
	
		<form:hidden path="id"/>
		
		<form:hidden path="update"/>

		<font color="red"><form:errors path="name"/></font><br>
		用户名：<form:input path="name" readonly="true"/><br>
		
		<font color="red"><form:errors path="nickName"/></font><br>
		昵    称：<form:input path="nickName"/><br>
		
		<font color="red"><form:errors path="mobile"/></font><br>
		手机号：<form:input path="mobile"/><br>
		
		<font color="red"><form:errors path="email"/></font><br>
		邮    箱：<form:input path="email"/><br>
		<br>
		<input type="submit" value="更新"/>
	</form:form>
	<hr>
	<c:url var="account" value="/account"/>
	<a href="${account }">返回个人信息中心</a>
</body>
</html>