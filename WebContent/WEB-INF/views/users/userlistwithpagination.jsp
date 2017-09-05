<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
<link href="<%=path%>/ui/css/main.css" rel="stylesheet">
<script type="text/javascript"
	src="<%=path%>/ui/javascript/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/ui/javascript/userlist.js"></script>

</head>
<body>
	所有用户信息,分页显示：
	<br>
	<c:forEach var="user" items="${users }">
		${user.id }<br>
	</c:forEach>


	<c:set var="pagination" value="${searchResult.pagination }" />
	<c:set var="results" value="${searchResult.results }" />

	<table border="1">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>盐值</th>
				<th>生日</th>
				<th>手机号</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="u" items="${results }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td width="50px">${u.name }</td>
					<td>${u.saltValue }</td>
					<td>${u.birthday }</td>
					<td>${u.mobile }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr/>
	
	<c:set var="currentPage" value="${pagination.currentPage}"/>
	<c:set var="totalPages" value="${pagination.totalPages}"/>
	总共<span>${totalPages }</span>页	,
	<c:if test="${currentPage>1 }">
		<c:url var="upPage" value="/userListWithPagination?currentPage=${currentPage-1}"/>	
		<a href="${upPage }">上一页</a>
	</c:if>
 <span>${currentPage }</span>
	<c:if test="${currentPage < totalPages}">
		<c:url var="downPage" value="/userListWithPagination?currentPage=${currentPage+1}"/>
		<a href="${downPage }">下一页</a>	
	</c:if>

</body>
</html>