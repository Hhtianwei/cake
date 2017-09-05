<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Server Error</title>
	
	<c:url value="/" var="homePageUrl" />
</head>
<body>
<div class="system_msg">
   <div class="info cf">
   		<div class="col-md-6 col-sm-12">
   			<p class="imgError"><img src="${homePageUrl}/ui/images/error/error500.jpg" alt="server error page"></p>
   		</div>
   		<div class="col-md-6 col-sm-12">
	   		<div class="errorDetails">
	   			<spring:message code="first.message"/>
	          <p class="errorCode">500</p>
	          <p class="errorMessage">服务器错误</p>
	          <a href="${homePageUrl}" title="button" class="sorryBtn waitBtn">返回主页</a>
	       </div>
   		</div>
   </div>
 </div>
	<p>Server Error</p>
</body>
</html>
