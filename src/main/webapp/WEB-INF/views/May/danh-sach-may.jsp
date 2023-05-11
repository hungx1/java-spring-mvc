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
			<h1 class="text-center">Danh sách Máy</h1>

			<form:form class="form-inline"
				action="${CP}/may/danh-sach-may/tim-kiem"
				modelAttribute="may_TimKiem" method="post">

				<div class="input-group mb-2 mr-sm-2">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa-solid fa-magnifying-glass"></i>
						</div>
					</div>
					<c:if test="${may_TimKiem.tuKhoa != null }">
						<form:input type="text" class="form-control" id="tuKhoa"
							path="tuKhoa" value="${may_TimKiem.tuKhoa} " />
						<form:errors path="tuKhoa" cssClass="error"></form:errors>
					</c:if>
					<c:if test="${may_TimKiem.tuKhoa == null }">
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
						<form:option value="maMay">Mã Máy</form:option>
						<form:option value="viTri">Vị trí</form:option>
						<form:option value="trangThai">Trạng thái</form:option>
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
						<th scope="col">Mã Máy</th>
						<th scope="col">Vị Trí</th>
						<th scope="col">Trạng thái</th>
						<th scope="col" colspan="2">Tính năng</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="item" varStatus="theCount">
						<tr>
							<th scope="row">${item.maMay}</th>
							<td>${ item.viTri}</td>
							<td>${ item.trangThai}</td>

							<td><button type="button" class="btn btn-primary text-white"
									onclick="window.location.href='${CP}/may/sua-chua/${item.maMay}';">
									<i class="fa-solid fa-pen-to-square"></i>Sửa
								</button></td>

							<td><button type="button" class="btn btn-danger text-white"
									onclick="if(confirm('Bạn có chắc chắn muốn sửa')) window.location.href='${CP}/may/xoa/${item.maMay}';">
									<i class="fa-solid fa-trash"></i>Xóa
								</button></td>

						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>



		<c:if
			test="${ curentpage != null and totalpage !=null and may_TimKiem.tuKhoa == null }">
			<ul class="pagination pagination-sm">
				<c:if test="${curentpage !=1 }">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/may/danh-sach-may/${curentpage-1}">Trang
							trước</a></li>
				</c:if>
				<c:forEach begin="1" end="${totalpage}" var="page_index">
					<c:if test="${curentpage == page_index }">
						<li class="page-item"><a class="page-link" href=""
							class="active">${page_index}</a></li>
					</c:if>
					<c:if test="${curentpage != page_index }">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/may/danh-sach-may/${page_index}">${page_index}</a></li>
					</c:if>

				</c:forEach>
				<c:if test="${totalpage != curentpage and totalpage>0}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/may/danh-sach-may/${curentpage + 1}">Trang
							Sau</a></li>
				</c:if>
			</ul>
		</c:if>

		<c:if
			test="${ curentpage != null and totalpage !=null and may_TimKiem.tuKhoa != null}">
			<ul class="pagination pagination-sm">
				<c:if test="${curentpage !=1 }">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/may/danh-sach-may/tim-kiem/${curentpage-1}/${may_TimKiem.tuKhoa}/${may_TimKiem.truongTimKiem}">Trang
							trước</a></li>
				</c:if>
				<c:forEach begin="1" end="${totalpage}" var="page_index">
					<c:if test="${curentpage == page_index }">
						<li class="page-item"><a class="page-link" href=""
							class="active">${page_index}</a></li>
					</c:if>
					<c:if test="${curentpage != page_index }">
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/may/danh-sach-may/tim-kiem/${page_index}/${may_TimKiem.tuKhoa}/${may_TimKiem.truongTimKiem}">${page_index}</a></li>
					</c:if>

				</c:forEach>
				<c:if test="${totalpage != curentpage and totalpage>0}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/may/danh-sach-may/tim-kiem/${curentpage + 1}/${may_TimKiem.tuKhoa}/${may_TimKiem.truongTimKiem}">Trang
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