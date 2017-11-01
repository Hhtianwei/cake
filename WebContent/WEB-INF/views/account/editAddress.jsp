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
<title>cake edit address</title>
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
		<c:url var="addAddress" value="/my-account/editAddress"/>
		<form action="${addAddress }" method="post">
			<input type="hidden" name="id" value="${addressData.id }"/>
			收件人：<font color="red">*</font><input type="text" name="recipient" value="${addressData.recipient }"/><br>
			联系电话：<font color="red">*</font><input type="text" name="tel" value="${addressData.tel }"/><br>
			
			地区选择：<font color="red">*</font><br>
			
			省：<select id="provinceForEdit" name="provienceCode">
				<option value="">---请选择---</option>
				<c:forEach var="province" items="${provinceDatas }">
					<c:choose>
						<c:when test="${addressData.provinceCode eq province.code}">
							<option value="${province.code }" selected="selected">${province.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${province.code }">${province.name }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>&nbsp;
			市：<select id="city" name="cityCode">
				<option value="">---请选择---</option>
				<c:forEach var="cityData" items="${cityDatas }">
					<c:choose>
						<c:when test="${addressData.cityCode eq cityData.code}">
							<option value="${cityData.code }" selected="selected">${cityData.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${cityData.code }">${cityData.name }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>&nbsp;
			区：<select id="area" name="areaCode">
				<option value="">---请选择---</option>
				<c:forEach var="areaData" items="${areaDatas }">
					<c:choose>
						<c:when test="${addressData.areaCode eq areaData.code}">
							<option value="${areaData.code }" selected="selected">${areaData.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${areaData.code }">${areaData.name }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>&nbsp;
			<br>
			
			详细地址：<font color="red">*</font><input type="text"  name="street" value="${addressData.street }"  style="width: 400px;"/><br>
			
			<c:set var="isCheck" value="false"/>
			<c:if test="${addressData.isDefault }">
				<c:set var="isCheck" value="true"/>
			</c:if>
			
			是否设置为默认：<font color="red">*</font><input type="radio"  name="isDefault" ${isCheck?'checked':'' }/>是<input type="radio" name="isDefault" ${isCheck?'':'checked' }/>否<br>
			标记：<input type="text" name="tagName" value="${addressData.tagName}"/><br><br>
			<input type="submit" value="更新"/>
			
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