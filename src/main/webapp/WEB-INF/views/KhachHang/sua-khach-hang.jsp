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
		<h1 class="text-center">Sửa Khách hàng</h1>
		<form:form action="${CP}/khach-hang/sua-chua/${sua_khach_hang.maKH }"
			method="post" modelAttribute="sua_khach_hang">

			<div class="form-group">
				<label for="maKH">Mã Khách Hàng<span style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="maKH"
					name="maKH" path="maKH"></form:input>
				<form:errors path="maKH" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="tenKH">Tên Khách Hàng<span style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="tenKH"
					name="tenKH" path="tenKH"></form:input>
				<form:errors path="tenKH" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="diaChi">Địa chỉ</label>
				<form:textarea cssClass="form-control" id="diaChi" name="diaChi"
					path="diaChi" cols="50" rows="5"></form:textarea>
				<form:errors path="diaChi" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="soDienThoai">Số điện thoại</label>
				<form:input type="text" cssClass="form-control" id="soDienThoai"
					name="soDienThoai" path="soDienThoai"></form:input>
				<form:errors path="soDienThoai" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="diaChiEmail">Địa chỉ Emai</label>
				<form:input type="text" cssClass="form-control" id="diaChiEmail"
					name="diaChiEmail" path="diaChiEmail"></form:input>
				<form:errors path="diaChiEmail" cssClass="error"></form:errors>
			</div>




			<button type="submit"
				onclick="return confirm('Bạn có chắc chắn muốn sửa')"
				class="btn btn-success text-white">
				<i class="fa-solid fa-plus"></i>Sửa Khách hàng
			</button>
			<button type="button" class="btn btn-primary text-white"
				onclick="window.location.href='${CP}/khach-hang/danh-sach-khach-hang';">
				<i class="fa-solid fa-backward"></i>Quay lại danh sách
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