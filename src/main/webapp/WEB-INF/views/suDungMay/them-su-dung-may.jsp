<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/web/css.jsp" %>
</head>
<body>
	<%@ include file="/common/web/header.jsp" %>
	
	<div class = "container body_container">
	<h1 class="text-center">Them su dung may</h1>
		<form:form action="${CP}/su-dung-may/them-su-dung-may" method="POST"
			modelAttribute = "them_su_dung_may">
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
				<label for="may">Máy<span style="color: red;">(*)</span></label>
				<form:select path="may" id="may" name="may" cssClass="form-control">
					<c:forEach items="${danhSachMay }" var="item">
						<form:option value="${item.maMay}" label="${item.maMay}"></form:option>
					</c:forEach>
				</form:select>
				<form:errors path="may" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="ngayBatDauSuDung">Ngày bắt đầu sử dụng<span
					style="color: red;">(*)</span></label>
				<form:input type="date" cssClass="form-control"
					id="ngayBatDauSuDung" name="ngayBatDauSuDung"
					path="ngayBatDauSuDung"></form:input>
				<form:errors path="ngayBatDauSuDung" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="gioBatDauSuDung">Giờ bắt đầu sử dụng<span
					style="color: red;">(*)</span></label>
				<form:input type="time" value="00:00:00" step="1"
					cssClass="form-control" id="gioBatDauSuDung" name="gioBatDauSuDung"
					path="gioBatDauSuDung"></form:input>
				<form:errors path="gioBatDauSuDung" cssClass="error"></form:errors>
			</div>

			<div class="form-group">
				<label for="thoiGianSuDung">Thời gian sử dụng (phút)<span
					style="color: red;">(*)</span></label>
				<form:input type="text" cssClass="form-control" id="thoiGianSuDung"
					name="thoiGianSuDung" path="thoiGianSuDung"></form:input>
				<form:errors path="thoiGianSuDung" cssClass="error"></form:errors>
			</div>
			
			<button type="submit" class="btn btn-success text-white">
				<i class="fa-solid fa-plus"></i>Thêm Sử dụng máy
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
	
	
	<%@ include file="/common/web/footer.jsp" %>
	<%@ include file="/common/web/script.jsp"%>
</body>
</html>