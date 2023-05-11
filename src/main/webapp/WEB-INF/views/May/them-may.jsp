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
		<h1 class="text-center">Thêm Máy</h1>
		<form:form action="${CP}/may/them-may" method="post"
			modelAttribute="them-may">

			<div class="form-group">
				<label for="maMay">Mã Máy<span style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="maMay"
					name="maMay" path="maMay"></form:input>
				<form:errors path="maMay" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="viTri">Vị trí<span style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="viTri"
					name="viTri" path="viTri"></form:input>
				<form:errors path="viTri" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="trangThai">Trạng thái<span style="color: red;">(*)</span></label>
				<form:select path="trangThai" id="trangThai" name="trangThai"
					cssClass="form-control">

					<form:option value="ranh" label="Rãnh"></form:option>
					<form:option value="ban" label="Bận"></form:option>
					<form:option value="dung duoc" label="Dùng được"></form:option>
					<form:option value="dang sua chua" label="Đang sửa chữa"></form:option>

				</form:select>
				<form:errors path="trangThai" cssClass="error"></form:errors>
			</div>






			<button type="submit" class="btn btn-success text-white">
				<i class="fa-solid fa-plus"></i>Thêm Máy
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