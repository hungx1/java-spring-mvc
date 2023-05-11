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
		<h1 class="text-center">Thêm Dịch Vụ</h1>
		<form:form action="${CP}/dich-vu/them-dich-vu" method="post"
			modelAttribute="them-dich-vu">

			<div class="form-group">
				<label for="maDV">Mã Dịch vụ<span style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="maDV"
					name="maDV" path="maDV"></form:input>
				<form:errors path="maDV" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="tenDV">Tên Dịch vụ</label>
				<form:input type="text" cssClass="form-control" id="tenDV"
					name="tenDV" path="tenDV"></form:input>
				<form:errors path="tenDV" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="donViTinh">Đơn vị tính<span style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="donViTinh"
					name="donViTinh" path="donViTinh"></form:input>
				<form:errors path="donViTinh" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="donGia">Đơn giá<span style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="donGia"
					name="donGia" path="donGia"></form:input>
				<form:errors path="donGia" cssClass="error"></form:errors>
			</div>




			<button type="submit" class="btn btn-success text-white">
				<i class="fa-solid fa-plus"></i>Thêm Dịch Vụ
			</button>
			<button type="button" class="btn btn-primary text-white"
				onclick="window.location.href='${CP}/trang-chu';">
				<i class="fa-solid fa-backward"></i>Quay lại trang chủ
			</button>
			<button type="reset"
				onclick="return confirm('Bạn có chắc chắn muốn xóa')"
				class="btn btn-danger text-white">
				<i class="fa-solid fa-arrow-rotate-left"></i>Xóa
			</button>
		</form:form>




	</div>

	<!-- footer -->
	<%@ include file="/common/web/footer.jsp"%>
	<!-- java script -->
	<%@ include file="/common/web/script.jsp"%>
</body>
</html>