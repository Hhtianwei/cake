<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<title>cake 后台管理</title>
	<script type="text/javascript" src="<%=path%>/ui/javascript/jquery/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/ui/javascript/product.js"></script>
	<style type="text/css">
		.hidden{
		display:none
		}
		
		.listProductDiv{
			display: none;

			position: absolute;
			
			width:650px;
			
			height:550px;
			
			background-color:gray;
			
			text-align: center;
		}
	</style>
</head>

<body>
	<c:set var="hasLogin" value="false"/>
	<c:if test="${not empty sessionScope.customerData }">
		<c:set var="hasLogin" value="true"/>
	</c:if>
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
		<a href="${login }">登录</a>&nbsp;<a href="${register }">注册</a>
	</c:if>
	<br>
	<input type="radio" name="addType" checked="checked" value="parent"/>父级产品
	<input type="radio" name="addType" value="sub"/>子级产品
	<hr>
	
	<c:url var="addProduct" value="/admin/addProduct"/>
	<c:url var="queryAllParent" value="/admin/queryAllParent"/>
	
	<form:form action="${addProduct }" method="post" commandName="productForm">
	
		<form:hidden path="flag" id="flag"/>
		
		<div id="superAttrDiv">
			<font color="red"><form:errors path="name"/></font><br>
			产品名:<form:input path="name"/><br>
			
			<font color="red"><form:errors path="longName"/></font><br>
			长名称:<form:input path="longName"/><br>
			
			<font color="red"><form:errors path="location"/></font><br>
			产地:<form:input path="location"/><br>
			
			<font color="red"><form:errors path="shape"/></font><br>
			形状:<form:input path="shape"/><br>
			
			<font color="red"><form:errors path="shape"/></font><br>
				上传图片:<input type="file" name="productImg"/>
			<br>
		</div>
		
		<div id="subAttrDiv" class="hidden">
			<font color="red"><form:errors path="price"/></font><br>
			价格:<form:input path="price"/><br>
					
			<font color="red"><form:errors path="size"/></font><br>
			尺寸:<form:input path="size"/><br>		
			
			<font color="red"><form:errors path="pid"/></font><br>
			父级产品:<form:input path="pid" readonly="readonly"/><a href="javascript:void(0)" id="showProducts">查询</a><br>
			
			<font color="red"><form:errors path="stock"/></font><br>
			库存:<form:input path="stock"/><br>
		</div>
		
		<input type="submit" value="增加"/><br>
	</form:form>
	<hr>
	<div id="listProductDiv" class="listProductDiv">
		<br>
			<table id="productTab">
				<thead>
					<tr>
						<th>序号</th>
						<th>编号</th>
						<th>名称</th>
						<th>长名</th>
						<th>形状</th>
						<th>图片</th>
					</tr>
				</thead>
				<tbody id="queryProductTbody">
					
				</tbody>
			</table>
			<a id="closeDiv">关闭</a>
		<br>
	</div>
</body>
</html>