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
<title>cake add address</title>
<script type="text/javascript" src="<%=path%>/ui/javascript/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/ui/javascript/address.js"></script>
</head>
<body>
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
	<br>
	<div>地址信息</div>
	<hr>
	<div class="addressInfo">
		<c:url var="addAddress" value="/my-account/addAddress"/>
		<form action="${addAddress }" method="post">
			收件人：<font color="red">*</font><input type="text" name="recipient"/><br>
			联系电话：<font color="red">*</font><input type="text" name="tel"/><br>
			
			地区选择：<font color="red">*</font><br>
			省：<select id="province" name="provienceCode">
				<option value="">---请选择---</option>
			</select>&nbsp;
			市：<select id="city" name="cityCode">
				<option value="">---请选择---</option>
			</select>&nbsp;
			区：<select id="area" name="areaCode">
				<option value="">---请选择---</option>
			</select>&nbsp;
			<br>
			
			详细地址：<font color="red">*</font><input type="text"  name="street"  style="width: 400px;"/><br>
			是否设置为默认：<font color="red">*</font><input type="radio" name="isDefault"/>是<input type="radio" name="isDefault" checked/>否<br>
			标记：<input type="text" name="tagName" value="住宅"/><br><br>
			<input type="submit" value="新增"/>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
		<br>
		<br>
	</div>	
	<hr>
	<div>订单信息</div>
	<hr>
</body>
</html>