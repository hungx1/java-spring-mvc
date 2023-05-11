<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "/common/web/css.jsp" %>
</head>
<body>
	<%@ include file="/common/web/header.jsp" %>
	
	<div class="container body_container">
		<h1>Quan ly Internet</h1>
		<c:if test="${user != null }">
			<p>Hello ${user.userName }
		</c:if>
		
		<c:if test="${user == null }">
			<p>Hello</p>
		</c:if>
		<c:if test="${msg != null }">
			<p> ${msg } </p>
		 </c:if>
		
		<c:if test="${msg1 != null }">
			<p> ${msg1 } </p>
		 </c:if>
		
	</div>
	
	<%@ include file="/common/web/footer.jsp"%>
	
	<%@ include file="/common/web/script.jsp"%>
	
</body>
</html>