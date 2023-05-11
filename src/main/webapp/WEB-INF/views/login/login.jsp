<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/web/css.jsp"%>
</head>
<body>
	<!-- header -->
	<%@ include file="/common/web/header.jsp"%>

	<!-- body -->
	<div class="container body_container">
		<h1 class="text-center">Đăng nhập</h1>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<div class="form-group">
				<label for="account">Account</label> <input type="text"
					class="form-control" id="account" name="account">
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" id="password" name="password">
			</div>

			<button type="submit" class="btn btn-primary">Đăng nhập</button>
			<button type="reset"
				onclick="return confirm('Bạn có chắc chắn muốn xóa')"
				class="btn btn-primary">Xóa</button>
		</form>




	</div>

	<!-- footer -->
	<%@ include file="/common/web/footer.jsp"%>
	<!-- java script -->
	<%@ include file="/common/web/script.jsp"%>
</body>
</html>