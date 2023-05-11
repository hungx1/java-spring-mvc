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
			<h1 class="text-center">Danh sách Sử dụng máy</h1>

			<form:form class="form-inline"
				action="${CP}/su-dung-may/tim-kiem"
				modelAttribute="suDungMay_TimKiem" method="post">

				<div class="input-group mb-2 mr-sm-2" id="dateSearch" hidden="true">
					<div class="input-group-prepend">
						<div class="input-group-text">Từ ngày:</div>
					</div>
					<form:input type="date" class="form-control"
						id="ngayBatDauSuDung_start" path="ngayBatDauSuDung_start" />

					<div class="input-group-prepend">
						<div class="input-group-text">Đến ngày:</div>
					</div>
					<form:input type="date" class="form-control"
						id="ngayBatDauSuDung_end" path="ngayBatDauSuDung_end" />
				</div>

				<div class="input-group mb-2 mr-sm-2" id="timeSearch" hidden="true">
					<div class="input-group-prepend">
						<div class="input-group-text">Từ lúc:</div>
					</div>
					<form:input type="time" class="form-control"
						id="gioBatDauSuDung_start" path="gioBatDauSuDung_start" step="1" />

					<div class="input-group-prepend">
						<div class="input-group-text">Đến lúc:</div>
					</div>
					<form:input type="time" class="form-control"
						id="gioBatDauSuDung_end" path="gioBatDauSuDung_end" step="1" />
				</div>

				<div class="input-group mb-2 mr-sm-2" id="intSearch" hidden="true">
					<div class="input-group-prepend">
						<div class="input-group-text">Min:</div>
					</div>
					<form:input type="text" class="form-control"
						id="thoiGianSuDung_start" path="thoiGianSuDung_start" />


					<div class="input-group-prepend">
						<div class="input-group-text">Max:</div>
					</div>
					<form:input type="text" class="form-control"
						id="thoiGianSuDung_end" path="thoiGianSuDung_end" />

				</div>

				<div class="input-group mb-2 mr-sm-2" id="textSearch">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa-solid fa-magnifying-glass"></i>
						</div>
					</div>
					<c:if test="${suDungMay_TimKiem.tuKhoa != null }">
						<form:input type="text" class="form-control" id="tuKhoa"
							path="tuKhoa" value="${suDungMay_TimKiem.tuKhoa} " />
					</c:if>
					<c:if test="${suDungMay_TimKiem.tuKhoa == null }">
						<form:input type="text" class="form-control" id="tuKhoa"
							placeholder="từ khóa..." path="tuKhoa" />
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
						<form:option value="maKH">Mã Khách Hàng</form:option>
						<form:option value="maMay">Mã Máy</form:option>
						<form:option value="ngayBatDauSuDung">Ngày bắt đầu sử dụng</form:option>
						<form:option value="gioBatDauSuDung">Giờ bắt đầu sử dụng</form:option>
						<form:option value="thoiGianSuDung">Thời gian sử dụng</form:option>
					</form:select>
				</div>


				<button type="submit" class="btn btn-primary mb-2">Tìm kiếm</button>

				<div class="container">
					<form:errors path="tuKhoa" cssClass="error"></form:errors>
					<%-- <form:errors path="thoiGianSuDung_end" cssClass="error"></form:errors> --%>
					<form:errors path="thoiGianSuDung_start" cssClass="error"></form:errors>
					<%-- <form:errors path="gioBatDauSuDung_end" cssClass="error"></form:errors> --%>
					<form:errors path="gioBatDauSuDung_start" cssClass="error"></form:errors>
					<%-- <form:errors path="ngayBatDauSuDung_end" cssClass="error"></form:errors> --%>
					<form:errors path="ngayBatDauSuDung_start" cssClass="error"></form:errors>
				</div>
			</form:form>

			<table class="table">
				<thead>
					<c:if test="${list==null}">
						<caption>Không có kết quả phù hợp</caption>
					</c:if>
					<tr>
						<th scope="col">Mã khách hàng</th>
						<th scope="col">Mã máy</th>
						<th scope="col">Ngày bắt đầu SD</th>
						<th scope="col">Giờ bắt đầu SD</th>
						<th scope="col">Thời gian SD</th>
						<th scope="col" colspan="2">Tính năng</th>


					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="item" varStatus="theCount">
						<tr>
							<th scope="row">${item.khachHang.maKH}</th>
							<td>${ item.may.maMay}</td>
							<td>${ item.ngayBatDauSuDung}</td>
							<td>${ item.gioBatDauSuDung}</td>
							<td>${ item.thoiGianSuDung}</td>

							<td><button type="button" class="btn btn-primary text-white"
									onclick="window.location.href='${CP}/su-dung-may/sua-chua/${item.id}';">
									<i class="fa-solid fa-pen-to-square"></i>Sửa
								</button></td>

							<td><button type="button" class="btn btn-danger text-white"
									onclick="if(confirm('Bạn có chắc chắn muốn sửa')) window.location.href='${CP}/su-dung-may/xoa/${item.id}';">
									<i class="fa-solid fa-trash"></i>Xóa
								</button></td>

						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>



		<c:if
			test="${ curentpage != null and totalpage !=null and timKiem == null }">
			<ul class="pagination pagination-sm">
				<c:if test="${curentpage !=1 }">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/su-dung-may/danh-sach-su-dung-may/${curentpage-1}">Trang
							trước</a></li>
				</c:if>
				<c:forEach begin="1" end="${totalpage}" var="page_index">
					<c:if test="${curentpage == page_index }">
						<li class="page-item"><a class="page-link" href="#"
							disabled=’disabled’>${page_index}</a></li>
					</c:if>
					<c:if test="${curentpage != page_index }">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/su-dung-may/danh-sach-su-dung-may/${page_index}">${page_index}</a></li>
					</c:if>

				</c:forEach>
				<c:if test="${totalpage != curentpage and totalpage>0}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/su-dung-may/danh-sach-su-dung-may/${curentpage + 1}">Trang
							Sau</a></li>
				</c:if>
			</ul>
		</c:if>

		<c:if
			test="${ curentpage != null and totalpage !=null and timKiem != null}">
			<ul class="pagination pagination-sm">
				<c:if test="${curentpage !=1 }">
					<li class="page-item"><form:form class="page-link"
							action="${CP}/su-dung-may/tim-kiem/${curentpage-1}"
							modelAttribute="suDungMay_TimKiem" method="post">
							<form:input type="text" class="form-control" id="tuKhoa"
								path="tuKhoa" value="${suDungMay_TimKiem.tuKhoa}"
								hidden="hidden" />
							<form:input type="text" class="form-control" id="truongTimKiem"
								path="truongTimKiem" value="${suDungMay_TimKiem.truongTimKiem}"
								hidden="hidden" />

							<form:input type="text" class="form-control"
								id="ngayBatDauSuDung_start" path="ngayBatDauSuDung_start"
								value="${suDungMay_TimKiem.ngayBatDauSuDung_start}"
								hidden="hidden" />

							<form:input type="text" class="form-control"
								id="ngayBatDauSuDung_end" path="ngayBatDauSuDung_end"
								value="${suDungMay_TimKiem.ngayBatDauSuDung_end}"
								hidden="hidden" />

							<form:input type="text" class="form-control"
								id="gioBatDauSuDung_start" path="gioBatDauSuDung_start"
								value="${suDungMay_TimKiem.gioBatDauSuDung_start}"
								hidden="hidden" />

							<form:input type="text" class="form-control"
								id="gioBatDauSuDung_end" path="gioBatDauSuDung_end"
								value="${suDungMay_TimKiem.gioBatDauSuDung_end}" hidden="hidden" />

							<form:input type="text" class="form-control"
								id="thoiGianSuDung_start" path="thoiGianSuDung_start"
								value="${suDungMay_TimKiem.thoiGianSuDung_start}"
								hidden="hidden" />

							<form:input type="text" class="form-control"
								id="thoiGianSuDung_end" path="thoiGianSuDung_end"
								value="${suDungMay_TimKiem.thoiGianSuDung_end}" hidden="hidden" />

							<button type="submit" class="btn text-primary active">Trang
								trước</button>
						</form:form></li>
				</c:if>
				<c:forEach begin="1" end="${totalpage}" var="page_index">
					<c:if test="${curentpage == page_index }">
						<li class="page-item"><form:form class="page-link" action="#"
								modelAttribute="suDungMay_TimKiem" method="post">
								<button type="submit" class="btn text-primary " disabled>${page_index}</button>
							</form:form></li>
					</c:if>
					<c:if test="${curentpage != page_index }">

						<li class="page-item"><form:form class="page-link"
								action="${CP}/su-dung-may/tim-kiem/${page_index}"
								modelAttribute="suDungMay_TimKiem" method="post">
								<form:input type="text" class="form-control" id="tuKhoa"
									path="tuKhoa" value="${suDungMay_TimKiem.tuKhoa}"
									hidden="hidden" />
								<form:input type="text" class="form-control" id="truongTimKiem"
									path="truongTimKiem" value="${suDungMay_TimKiem.truongTimKiem}"
									hidden="hidden" />

								<form:input type="text" class="form-control"
									id="ngayBatDauSuDung_start" path="ngayBatDauSuDung_start"
									value="${suDungMay_TimKiem.ngayBatDauSuDung_start}"
									hidden="hidden" />

								<form:input type="text" class="form-control"
									id="ngayBatDauSuDung_end" path="ngayBatDauSuDung_end"
									value="${suDungMay_TimKiem.ngayBatDauSuDung_end}"
									hidden="hidden" />

								<form:input type="text" class="form-control"
									id="gioBatDauSuDung_start" path="gioBatDauSuDung_start"
									value="${suDungMay_TimKiem.gioBatDauSuDung_start}"
									hidden="hidden" />

								<form:input type="text" class="form-control"
									id="gioBatDauSuDung_end" path="gioBatDauSuDung_end"
									value="${suDungMay_TimKiem.gioBatDauSuDung_end}"
									hidden="hidden" />

								<form:input type="text" class="form-control"
									id="thoiGianSuDung_start" path="thoiGianSuDung_start"
									value="${suDungMay_TimKiem.thoiGianSuDung_start}"
									hidden="hidden" />

								<form:input type="text" class="form-control"
									id="thoiGianSuDung_end" path="thoiGianSuDung_end"
									value="${suDungMay_TimKiem.thoiGianSuDung_end}" hidden="hidden" />

								<button type="submit" class="btn text-primary">${page_index}
								</button>
							</form:form></li>
					</c:if>

				</c:forEach>
				<c:if test="${totalpage != curentpage and totalpage>0}">
					<li class="page-item"><form:form class="page-link"
							action="${CP}/su-dung-may/tim-kiem/${curentpage+1}"
							modelAttribute="suDungMay_TimKiem" method="post">
							<form:input type="text" class="form-control" id="tuKhoa"
								path="tuKhoa" value="${suDungMay_TimKiem.tuKhoa}"
								hidden="hidden" />
							<form:input type="text" class="form-control" id="truongTimKiem"
								path="truongTimKiem" value="${suDungMay_TimKiem.truongTimKiem}"
								hidden="hidden" />

							<form:input type="text" class="form-control"
								id="ngayBatDauSuDung_start" path="ngayBatDauSuDung_start"
								value="${suDungMay_TimKiem.ngayBatDauSuDung_start}"
								hidden="hidden" />

							<form:input type="text" class="form-control"
								id="ngayBatDauSuDung_end" path="ngayBatDauSuDung_end"
								value="${suDungMay_TimKiem.ngayBatDauSuDung_end}"
								hidden="hidden" />

							<form:input type="text" class="form-control"
								id="gioBatDauSuDung_start" path="gioBatDauSuDung_start"
								value="${suDungMay_TimKiem.gioBatDauSuDung_start}"
								hidden="hidden" />

							<form:input type="text" class="form-control"
								id="gioBatDauSuDung_end" path="gioBatDauSuDung_end"
								value="${suDungMay_TimKiem.gioBatDauSuDung_end}" hidden="hidden" />

							<form:input type="text" class="form-control"
								id="thoiGianSuDung_start" path="thoiGianSuDung_start"
								value="${suDungMay_TimKiem.thoiGianSuDung_start}"
								hidden="hidden" />

							<form:input type="text" class="form-control"
								id="thoiGianSuDung_end" path="thoiGianSuDung_end"
								value="${suDungMay_TimKiem.thoiGianSuDung_end}" hidden="hidden" />

							<button type="submit" class="btn text-primary">Trang sau
							</button>
						</form:form></li>
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
	<c:if test="${ suDungMay_TimKiem.truongTimKiem =='maKH' }">
		<script>
			$(`#truongTimKiem`).find(`option[value="maKH"]`).attr("selected",
					"selected");
			$(`#textSearch`).removeAttr("hidden");
			$(`#dateSearch`).attr("hidden", "true");
			$(`#timeSearch`).attr("hidden", "true");
			$(`#intSearch`).attr("hidden", "true");
		</script>
	</c:if>
	<c:if test="${ suDungMay_TimKiem.truongTimKiem == 'maMay'}">
		<script>
			$(`#truongTimKiem`).find(`option[value="maMay"]`).attr("selected",
					"selected");
			$(`#textSearch`).attr("hidden", "true");
			$(`#dateSearch`).removeAttr("hidden");
			$(`#timeSearch`).attr("hidden", "true");
			$(`#intSearch`).attr("hidden", "true");
		</script>
	</c:if>
	<c:if test="${ suDungMay_TimKiem.truongTimKiem =='ngayBatDauSuDung'}">
		<script>
			$(`#truongTimKiem`).find(`option[value="ngayBatDauSuDung"]`).attr(
					"selected", "selected");
			$(`#textSearch`).attr("hidden", "true");
			$(`#dateSearch`).removeAttr("hidden");
			$(`#timeSearch`).attr("hidden", "true");
			$(`#intSearch`).attr("hidden", "true");
		</script>
	</c:if>
	<c:if test="${ suDungMay_TimKiem.truongTimKiem =='gioBatDauSuDung'}">
		<script>
			$(`#truongTimKiem`).find(`option[value="gioBatDauSuDung"]`).attr(
					"selected", "selected");
			$(`#textSearch`).attr("hidden", "true");
			$(`#timeSearch`).removeAttr("hidden");
			$(`#intSearch`).attr("hidden", "true");
			$(`#dateSearch`).attr("hidden", "true");
		</script>
	</c:if>
	<c:if test="${ suDungMay_TimKiem.truongTimKiem =='thoiGianSuDung'}">
		<script>
			$(`#truongTimKiem`).find(`option[value="thoiGianSuDung"]`).attr(
					"selected", "selected");
			$(`#textSearch`).attr("hidden", "true");
			$(`#intSearch`).removeAttr("hidden");
			$(`#dateSearch`).attr("hidden", "true");
			$(`#timeSearch`).attr("hidden", "true");
		</script>
	</c:if>
	<script>
		$(`#truongTimKiem`).on(
				"change",
				function() {
					if ($(`#truongTimKiem`).val() == "ngayBatDauSuDung") {
						$(`#textSearch`).attr("hidden", "true");
						$(`#dateSearch`).removeAttr("hidden");
						$(`#timeSearch`).attr("hidden", "true");
						$(`#intSearch`).attr("hidden", "true");

					}
					if ($(`#truongTimKiem`).val() == "gioBatDauSuDung") {
						$(`#textSearch`).attr("hidden", "true");
						$(`#timeSearch`).removeAttr("hidden");
						$(`#intSearch`).attr("hidden", "true");
						$(`#dateSearch`).attr("hidden", "true");

					}
					if ($(`#truongTimKiem`).val() == "thoiGianSuDung") {
						$(`#textSearch`).attr("hidden", "true");
						$(`#intSearch`).removeAttr("hidden");
						$(`#dateSearch`).attr("hidden", "true");
						$(`#timeSearch`).attr("hidden", "true");

					}
					if ($(`#truongTimKiem`).val() == "maKH"
							|| $(`#truongTimKiem`).val() == "maMay") {
						$(`#textSearch`).removeAttr("hidden");
						$(`#dateSearch`).attr("hidden", "true");
						$(`#timeSearch`).attr("hidden", "true");
						$(`#intSearch`).attr("hidden", "true");

					}
				})
	</script>
</body>
</html>