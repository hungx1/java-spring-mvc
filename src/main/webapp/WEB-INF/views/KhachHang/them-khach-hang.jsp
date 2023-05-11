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
		<h1 class="text-center">Thêm Khách Hàng</h1>
		<form:form action="${CP}/khach-hang/them-khach-hang" method="post" id="addKhachHang"
			modelAttribute="them-khach-hang">

			<div class="form-group">
				<label for="maKH">Mã Khách Hàng<span style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="maKH"
					name="maKH" path="maKH"></form:input>
				<form:errors path="maKH" cssClass="error"></form:errors>
				<div class="invalid-feedback  "></div>
			</div>

			<div class="form-group">
				<label for="tenKH">Tên Khách Hàng<span style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="tenKH"
					name="tenKH" path="tenKH"></form:input>
				<form:errors path="tenKH" cssClass="error"></form:errors>
				<div class="invalid-feedback  "></div>
			</div>

			<div class="form-group">
				<label for="diaChi">Địa chỉ</label>
				<form:textarea cssClass="form-control" id="diaChi" name="diaChi"
					path="diaChi" cols="50" rows="5"></form:textarea>
				<form:errors path="diaChi" cssClass="error"></form:errors>
				<div class="invalid-feedback  "></div>
			</div>

			<div class="form-group">
				<label for="soDienThoai">Số điện thoại</label>
				<form:input type="text" cssClass="form-control" id="soDienThoai"
					name="soDienThoai" path="soDienThoai"></form:input>
				<form:errors path="soDienThoai" cssClass="error"></form:errors>
				<div class="invalid-feedback  "></div>
			</div>

			<div class="form-group">
				<label for="diaChiEmail">Địa chỉ Emai</label>
				<form:input type="text" cssClass="form-control" id="diaChiEmail"
					name="diaChiEmail" path="diaChiEmail"></form:input>
				<form:errors path="diaChiEmail" cssClass="error"></form:errors>
				<div class="invalid-feedback  "></div>
			</div>




			<button type="submit" class="btn btn-success text-white">
				<i class="fa-solid fa-plus"></i>Thêm Khách Hàng
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
	<script>
	const regexHoTen = /^[a-zA-Z\s]{1,255}$/;
	const regexMaKH = /^KH[0-9]{5}$/;
	const regexSdt = /^((090)|(091)|(\(84\)\+90)|(\(84\)\+91))[0-9]{7}$/;
	const regexEmail = /^[a-z]{1}[a-z_\-0-9]+@[a-z]{2,}(\.[a-z]{2,})+$/;
	//==============================================================
	function isEmpty(value) {
	    return !value || !value.trim()
	}
	//=========================================================
	$(`#addKhachHang`).on("submit", function (e) {


	    if (!validateKhachHang_id() ||
	        !validateKhachHang_ten() ||
	        !validateKhachHang_diachi() ||
	        !validateKhachHang_sdt() ||
	        !validateKhachHang_email()) {
	        e.preventDefault();
	    } else {
	        $(`#addKhachHang`).submit();
	    }

	})
	//=========================================================
	function validateKhachHang_ten() {
	    let khachhang_ten = $('input:text[name="tenKH"]').val();
	    if (isEmpty(khachhang_ten) == true || regexHoTen.test(khachhang_ten) == false) {

	        $('#tenKH').next().text(`Tên Khách hàng không được để trống hoặc chứa số`);
	        $('#tenKH').addClass("is-invalid")
	        return false;
	    } else {
	        $('#tenKH').next().text(``);
	        $('#tenKH').removeClass("is-invalid")
	        return true;
	    }
	}
	//=========================================================
	function validateKhachHang_diachi() {
	    let khachhang_diachi = $('textarea[name="diaChi"]').val();
	    if (isEmpty(khachhang_diachi) == true) {

	        $('#diaChi').next().text(`Địa chỉ Khách hàng không được để trống`);
	        $('#diaChi').addClass("is-invalid")
	        return false;
	    } else {
	        $('#diaChi').next().text(``);
	        $('#diaChi').removeClass("is-invalid")
	        return true;
	    }
	}
	//=========================================================
	function validateKhachHang_sdt() {
	    let khachhang_sdt = $('input:text[name="soDienThoai"]').val();
	    if (isEmpty(khachhang_sdt) == true || regexSdt.test(khachhang_sdt) == false) {

	        $('#soDienThoai').next().text(`Số điện thoại Khách hàng không được để trống hoặc bắt đầu 090|091|(84)+90|(84)+91`);
	        $('#soDienThoai').addClass("is-invalid")
	        return false;
	    } else {
	        $('#soDienThoai').next().text(``);
	        $('#soDienThoai').removeClass("is-invalid")
	        return true;
	    }
	}
	//=========================================================
	function validateKhachHang_email() {
	    let khachhang_email = $('input:text[name="diaChiEmail"]').val();
	    if (isEmpty(khachhang_email) == true || regexEmail.test(khachhang_email) == false) {

	        $('#diaChiEmail').next().text(`Email Khách hàng không được để trống hoặc sai cú pháp email`);
	        $('#diaChiEmail').addClass("is-invalid")
	        return false;
	    } else {
	        $('#diaChiEmail').next().text(``);
	        $('#diaChiEmail').removeClass("is-invalid")
	        return true;
	    }
	}
	//=========================================================
	function validateKhachHang_id() {
	    let khachhang_id = $('input:text[name="maKH"]').val();
	    if (isEmpty(khachhang_id) == true || regexMaKH.test(khachhang_id) == false) {

	        $('#maKH').next().text(`Mã Khách hàng không được để trống hoặc có format là KHxxxxx (x: ký tự số)`);
	        $('#maKH').addClass("is-invalid")
	        return false;
	    } else {
	        $('#maKH').next().text(``);
	        $('#maKH').removeClass("is-invalid")
	        return true;
	    }
	}
	//=========================================================
	//=========================================================
	//=========================================================
	//=========================================================

	</script>
</body>
</html>