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
	<h2>用户信息</h2>
	<label>用户名</label>	<label>${sessionScope.customerData.name }</label>
	<br>
	<label>昵称</label>	<label>${sessionScope.customerData.nickName }</label>
	<br>
	<label>手机号</label>	<label>${sessionScope.customerData.mobile }</label>
	<br>
	<label>邮箱</label>	<label>${sessionScope.customerData.email }</label>
	<br>
	<label>注册日期</label>	<label>${sessionScope.customerData.createDate }</label>
	<br>
	<hr>
	<c:url var="updateCustomerInfo" value="/updateCustomerInfo"/>
	<c:url var="updatePassword" value="/updatePassword"/>
	<h3>功能:<a href="${updateCustomerInfo }">修改用户信息</a>&nbsp;<a href="${updatePassword }">修改密码</a></h3>
	<br>
		<c:url var="home" value="/"/>
	<a href="${home}">返回主页</a>
</body>
</html>