<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Loi he thong</h1>

	<c:if test="${errorMsg != null}">Message: ${errorMsg}<br />
	</c:if>

	<c:if test="${errorCode !=null}">Code: ${errorCode}<br />
	</c:if>


	<c:if test="${ExceptionMsg!=null }">
	Exception Message:${ExceptionMsg}
	</c:if>
</body>
</html>