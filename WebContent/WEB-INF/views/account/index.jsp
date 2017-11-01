<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cake 后台管理</title>
</head>
<body>
	<c:set var="hasLogin" value="false"/>
	<c:if test="${not empty sessionScope.customerData }">
		<c:set var="hasLogin" value="true"/>
	</c:if>
	这个是个人中心，
	<c:if test="${hasLogin }">
		当前登录用户是<font color="red">&nbsp;${sessionScope.customerData.nickName }</font>
		<c:url var="logoutUrl" value="/logout"/>
		<form action="${logoutUrl}" method="post">
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    <input type="submit" value="Log out" />
		</form>
	</c:if>
	<br>
	<hr>
	<h1>功能列表</h1>
	
	<br>
	<div>用户个人信息</div>
	<hr>
	<div class="addressInfo">
		
		<c:url var="editAddress" value="/my-account/editAddress"/>
		<c:url var="addAddress" value="/my-account/addAddress"/>
		<a href="${addAddress }">新增地址</a>
		<div class="addressTitle">
			地址列表：
		</div>
		<div>
			<table border='1'>
				<thead>
					<tr>
						<th>序号</th>
						<th>标签</th>
						<th>收件人</th>
						<th>联系电话</th>
						<th>详细地址</th>
						<th colspan="2">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${addresses }" var="address" varStatus="status">
						<tr>
							<td>${status.index+1 }</td>
							<td>${address.tagName }</td>
							<td>${address.recipient }</td>
							<td>${address.tel }</td>
							
							<td>${address.provinceName}${address.cityName}${address.areaName}${address.street}</td>
							<td style="white-space: nowrap;">
								<a href="${editAddress}?id=${address.id}">更新</a>
							</td>
							<td>
								<c:url var="delAddress" value="/my-account/delAddress/${address.id }"/>
								<a href="${delAddress}">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>	
	<hr>
	<div>订单信息</div>
	<hr>
</body>
</html>