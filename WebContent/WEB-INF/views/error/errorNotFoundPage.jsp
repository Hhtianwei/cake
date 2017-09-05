<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>404</title>
</head>
<body>

	<c:url value="/" var="homePageUrl" />
	<div class="item_container_holder_error">
		<div class="system_msg">
			<div class="info cf">
				<div class="col-md-6 col-sm-12">
					<p class="imgError">
						<img src="<%=path%>/ui/images/error/404-1.jpg">
					</p>
				</div>
				<div class="col-md-6 col-sm-12">
					<div class="errorDetails">
						<p class="imgSorry"></p>
						<p class="errorMessage">404</p>
						<a href="${homePageUrl}" title="button" class="sorryBtn">home
							page </a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>