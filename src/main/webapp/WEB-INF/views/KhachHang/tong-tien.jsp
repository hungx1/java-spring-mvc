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



			<table class="table">
				<thead>
					<c:if test="${list==null}">
						<caption>Không có kết quả phù hợp</caption>
					</c:if>
					<tr>
						<th scope="col">Mã Khách hàng</th>
						<th scope="col">Tên Khách hàng</th>
						<th scope="col">Mã máy</th>
						<th scope="col">Vị trí máy</th>
						<th scope="col">Trạng thái máy</th>
						<th scope="col">Ngày bắt đầu sử dụng</th>
						<th scope="col">Giờ bắt đầu sử dụng</th>
						<th scope="col">Thời gian sử dụng</th>
						<th scope="col">Mã dịch vụ</th>
						<th scope="col">Ngày sử dụng</th>
						<th scope="col">Giờ sử dụng</th>
						<th scope="col">Số lượng</th>
						<th scope="col">1Tổng tiền</th>



					</tr>
				</thead>

				<tbody>
					<c:forEach items="${list}" var="item" varStatus="theCount">
						<tr>
							<th scope="row">${item.maKH}</th>
							<td>${ item.tenKH}</td>
							<td>${ item.maMay}</td>
							<td>${ item.viTriMay}</td>
							<td>${ item.trangThai}</td>
							<td>${ item.ngayBatDauSuDung}</td>
							<td>${ item.gioBatDauSuDung}</td>
							<td>${ item.thoiGianSuDung}</td>
							<td>${ item.maDV}</td>
							<td>${ item.ngaySuDung}</td>
							<td>${ item.gioSuDung}</td>
							<td>${ item.soLuong}</td>
							<td>${ item.tongTien}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>



		<c:if
			test="${ curentpage != null and totalpage !=null and list.size()>0 }">
			<ul class="pagination pagination-sm">
				<c:if test="${curentpage !=1 }">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/khach-hang/tong-tien/${curentpage-1}">Trang
							trước</a></li>
				</c:if>
				<c:forEach begin="1" end="${totalpage}" var="page_index">
					<c:if test="${curentpage == page_index }">
						<li class="page-item"><a class="page-link" href=""
							class="active">${page_index}</a></li>
					</c:if>
					<c:if test="${curentpage != page_index }">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/khach-hang/tong-tien/${page_index}">${page_index}</a></li>
					</c:if>

				</c:forEach>
				<c:if test="${totalpage != curentpage and totalpage>0}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/khach-hang/tong-tien/${curentpage + 1}">Trang
							Sau</a></li>
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