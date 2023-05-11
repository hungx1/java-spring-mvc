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
		<h1 class="text-center">Thêm Sử Dụng Dịch Vụ</h1>
		<form:form action="${CP}/su-dung-dich-vu/them-su-dung-dich-vu"
			method="post" modelAttribute="them_su_dung_dich_vu">

			<div class="form-group">
				<label for="khachHang">Khách Hàng<span style="color: red;">(*)</span></label>
				<form:select path="khachHang" id="khachHang" name="khachHang"
					cssClass="form-control">
					<c:forEach items="${danhSachKH }" var="item">
						<form:option value="${item.maKH}" label="${item.tenKH}"></form:option>
					</c:forEach>
				</form:select>
				<form:errors path="khachHang" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="dichVu">Dịch Vụ<span style="color: red;">(*)</span></label>
				<form:select path="dichVu" id="may" name="dichVu"
					cssClass="form-control">
					<c:forEach items="${danhSachDV }" var="item">
						<form:option value="${item.maDV}" label="${item.tenDV}"></form:option>
					</c:forEach>
				</form:select>
				<form:errors path="dichVu" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="ngaySuDung">Ngày sử dụng<span
					style="color: red;">(*)</span></label>
				<form:input type="date" cssClass="form-control" id="ngaySuDung"
					name="ngaySuDung" path="ngaySuDung"></form:input>
				<form:errors path="ngaySuDung" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="gioSuDung">Giờ sử dụng<span style="color: red;">(*)</span></label>
				<form:input type="time" step="1" cssClass="form-control"
					id="gioSuDung" name="gioSuDung" path="gioSuDung"></form:input>
				<form:errors path="gioSuDung" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="soLuong">Số lượng<span style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="soLuong"
					name="soLuong" path="soLuong"></form:input>
				<form:errors path="soLuong" cssClass="error"></form:errors>
			</div>






			<button type="submit" class="btn btn-success text-white">
				<i class="fa-solid fa-plus"></i>Thêm Sử dụng Dịch Vụ
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

	<!-- modal 2  static-->
	<div class="modal" tabindex="-1" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Thông báo</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>${thongBao}</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Đóng</button>
					<!-- <button type="button" class="btn btn-primary">Save changes</button> -->
				</div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<%@ include file="/common/web/footer.jsp"%>
	<!-- java script -->
	<%@ include file="/common/web/script.jsp"%>

	<c:if test="${thongBao != null}">
		<script>
			$(`#myModal`).modal({
				show : true
			});
		</script>
	</c:if>
</body>
</html>