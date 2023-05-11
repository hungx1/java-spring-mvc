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
		<div class="table-responsive">
			<h1 class="text-center">Danh sách Khách Hàng</h1>

			<form:form class="form-inline"
				action="${CP}/khach-hang/danh-sach-khach-hang/tim-kiem"
				modelAttribute="khachHang_TimKiem" method="post">

				<div class="input-group mb-2 mr-sm-2">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa-solid fa-magnifying-glass"></i>
						</div>
					</div>
					<c:if test="${khachHang_TimKiem.tuKhoa != null }">
						<form:input type="text" class="form-control" id="tuKhoa"
							path="tuKhoa" value="${khachHang_TimKiem.tuKhoa} " />
						<form:errors path="tuKhoa" cssClass="error"></form:errors>
					</c:if>
					<c:if test="${khachHang_TimKiem.tuKhoa == null }">
						<form:input type="text" class="form-control" id="tuKhoa"
							placeholder="từ khóa..." path="tuKhoa" />
						<form:errors path="tuKhoa" cssClass="error"></form:errors>
					</c:if>
				</div>
				<div class="input-group mb-2 mr-sm-2">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa-solid fa-filter"></i>Trường tìm kiếm
						</div>
					</div>
					<form:select class="form-control custom-select " id="truongTimKiem"
						path="truongTimKiem">
						<form:option value="maKH">Mã Khách hàng</form:option>
						<form:option value="tenKH">Tên Khách hàng</form:option>
						<form:option value="diaChi">Địa chỉ</form:option>
						<form:option value="soDienThoai">Số điện thoại</form:option>
						<form:option value="diaChiEmail">Địa chỉ Email</form:option>

					</form:select>
				</div>


				<button type="submit" class="btn btn-primary mb-2">Tìm kiếm</button>
			</form:form>

			<table class="table">
				<thead>
					<c:if test="${list==null}">
						<caption>Không có kết quả phù hợp</caption>
					</c:if>
					<tr>
						<th scope="col">Mã Khách hàng</th>
						<th scope="col">Tên Khách hàng</th>
						<th scope="col">Địa chỉ</th>
						<th scope="col">Số điện thoại</th>
						<th scope="col">Địa chỉ Email</th>
						<th scope="col" colspan="2">Tính năng</th>


					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="item" varStatus="theCount">
						<tr>
							<th scope="row">${item.maKH}</th>
							<td>${ item.tenKH}</td>
							<td>${ item.diaChi}</td>
							<td>${ item.soDienThoai}</td>
							<td>${ item.diaChiEmail}</td>

							<td><button type="button" class="btn btn-primary text-white"
									onclick="window.location.href='${CP}/khach-hang/sua-chua/${item.maKH}';">
									<i class="fa-solid fa-pen-to-square"></i>Sửa
								</button></td>

							<td><button type="button" class="btn btn-danger text-white"
									onclick="if(confirm('Bạn có chắc chắn muốn xoa')) window.location.href='${CP}/khach-hang/xoa/${item.maKH}';">
									<i class="fa-solid fa-trash"></i>Xóa
								</button></td>

						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>



		<c:if
			test="${ curentpage != null and totalpage !=null and khachHang_TimKiem.tuKhoa == null }">
			<ul class="pagination pagination-sm">
				<c:if test="${curentpage !=1 }">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/khach-hang/danh-sach-khach-hang/${curentpage-1}">Trang
							trước</a></li>
				</c:if>
				<c:forEach begin="1" end="${totalpage}" var="page_index">
					<c:if test="${curentpage == page_index }">
						<li class="page-item"><a class="page-link" href=""
							class="active">${page_index}</a></li>
					</c:if>
					<c:if test="${curentpage != page_index }">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/khach-hang/danh-sach-khach-hang/${page_index}">${page_index}</a></li>
					</c:if>

				</c:forEach>
				<c:if test="${totalpage != curentpage and totalpage>0}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/khach-hang/danh-sach-khach-hang/${curentpage + 1}">Trang
							Sau</a></li>
				</c:if>
			</ul>
		</c:if>

		<c:if
			test="${ curentpage != null and totalpage !=null and khachHang_TimKiem.tuKhoa != null}">
			<ul class="pagination pagination-sm">
				<c:if test="${curentpage !=1 }">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/khach-hang/danh-sach-khach-hang/tim-kiem/${curentpage-1}/${khachHang_TimKiem.tuKhoa}/${khachHang_TimKiem.truongTimKiem}">Trang
							trước</a></li>
				</c:if>
				<c:forEach begin="1" end="${totalpage}" var="page_index">
					<c:if test="${curentpage == page_index }">
						<li class="page-item"><a class="page-link" href=""
							class="active">${page_index}</a></li>
					</c:if>
					<c:if test="${curentpage != page_index }">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/khach-hang/danh-sach-khach-hang/tim-kiem/${page_index}/${khachHang_TimKiem.tuKhoa}/${khachHang_TimKiem.truongTimKiem}">${page_index}</a></li>
					</c:if>

				</c:forEach>
				<c:if test="${totalpage != curentpage and totalpage>0}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/khach-hang/danh-sach-khach-hang/tim-kiem/${curentpage + 1}/${khachHang_TimKiem.tuKhoa}/${khachHang_TimKiem.truongTimKiem}">Trang
							sau</a></li>
				</c:if>
			</ul>
		</c:if>
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