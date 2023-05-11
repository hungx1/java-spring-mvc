<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chu</title>
<link href="<c:url value='/resources/css/bootstrap.min.css'/> "
	rel="stylesheet">
<link href="<c:url value='/resources/css/all.min.css'/> "
	rel="stylesheet">
</head>
<body>
	<!-- header -->
	<%@ include file="/common/web/header.jsp"%>
	<!-- body -->

	<div class="container ">
		<h1>Hello</h1>
		<c:redirect url="/trang-chu"></c:redirect>
	</div>

	<!-- footer -->
	<%@ include file="/common/web/footer.jsp"%>
	<!-- java script -->
	<script src="<c:url value='/resources/js/jquery.min.js'/> "></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/> "></script>
	<script src="<c:url value='/resources/js/popper.min.js'/> "></script>
	<script src="<c:url value='/resources/js/all.min.js'/> "></script>
</body>
</html>