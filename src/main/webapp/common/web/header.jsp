<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/trang-chu">General
			Assignment</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExample07" aria-controls="navbarsExample07"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExample07">
			<ul class="navbar-nav mr-auto">
				<c:if test="${user.userName !=null }">
					<li class="nav-item"><a class="nav-link" href="#">Xin chào
							${user.userName}</a></li>
				</c:if>
				<c:if test="${user==null }">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/login">Đăng nhập</a></li>
				</c:if>
				<c:if test="${user!=null }">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/logout">Đăng xuất</a></li>
				</c:if>
				
			
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="dropdown07"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dịch
						vụ</a>
					<div class="dropdown-menu" aria-labelledby="dropdown07">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/dich-vu/them-dich-vu">Thêm</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/dich-vu/danh-sach-dich-vu">Danh
							sách</a>

					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="dropdown08"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Máy</a>
					<div class="dropdown-menu" aria-labelledby="dropdown07">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/may/them-may">Thêm</a> <a
							class="dropdown-item"
							href="<%=request.getContextPath()%>/may/danh-sach-may">Danh
							sách</a>

					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="dropdown09"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Khách
						Hàng</a>
					<div class="dropdown-menu" aria-labelledby="dropdown07">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/khach-hang/them-khach-hang">Thêm</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/khach-hang/danh-sach-khach-hang">Danh
							sách</a>
						 <a class="dropdown-item"
							href="<%=request.getContextPath()%>/khach-hang/tong-tien">Tổng
							tiền</a>

					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="dropdown10"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Sử
						dụng máy</a>
					<div class="dropdown-menu" aria-labelledby="dropdown07">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/su-dung-may/them-su-dung-may">Thêm</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/su-dung-may/danh-sach-su-dung-may">Danh
							sách</a>

					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="dropdown11"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Sử
						dụng dịch vụ</a>
					<div class="dropdown-menu" aria-labelledby="dropdown07">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/su-dung-dich-vu/them-su-dung-dich-vu">Thêm</a>


					</div></li>


			</ul>

		</div>
	</div>
</nav>