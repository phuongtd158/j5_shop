<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--========== BOX ICONS ==========-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin_styles.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin_styles.scss">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.css"
	integrity="sha512-oe8OpYjBaDWPt2VmSFR+qYOdnTjeV9QPLJUeqZyprDEQvQLJ9C5PCFclxwNuvb/GQgQngdCXzKSFltuHD3eCxA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<style type="text/css">
a {
	text-decoration: none;
}
</style>
</head>
<body>

	<!--========== HEADER ==========-->
	<header class="header">
		<div class="header__container">
			<%--        <img src="/Assignment_Java4/assets/admin/img/perfil.jpg" alt="" class="header__img">--%>
			<%-- 	<a href="#" class="header__logo">Admin</a> <span>Xin chào,
				${sessionScope.user.fullName}</span> --%>
			<div class="header__toggle">
				<i class='bx bx-menu' id="header-toggle"></i>
			</div>
		</div>
	</header>

	<!--========== NAV ==========-->
	<div class="nav1" id="navbar">
		<nav class="nav__container">
			<div>
				<a href="#" class="nav__link nav__logo"> <i
					class='bx bxs-disc nav__icon'></i> <span class="nav__logo-name">Admin</span>
				</a>

				<div class="nav__list">
					<div class="nav__items">
						<h3 class="nav__subtitle">Quản lý</h3>
						<a href="${pageContext.request.contextPath}/admin/home"
							class="nav__link active"> <i class='bx bx-home nav__icon'></i>
							<span class="nav__name">Trang chủ</span>
						</a>

						<div class="nav__dropdown">
							<a href="#" class="nav__link"> <i
								class='bx bx-package nav__icon'></i> <span class="nav__name">Sản
									phẩm</span> <i class='bx bx-chevron-down nav__icon nav__dropdown-icon'></i>
							</a>

							<div class="nav__dropdown-collapse">
								<div class="nav__dropdown-content">
									<a
										href="${pageContext.request.contextPath}/admin/category/index"
										class="nav__dropdown-item">Danh mục</a> <a
										href="${pageContext.request.contextPath}/admin/product/index"
										class="nav__dropdown-item">Sản phẩm</a>
								</div>
							</div>
						</div>

						<a href="${pageContext.request.contextPath}/admin/account/index"
							class="nav__link"> <i class='bx bx-user nav__icon'></i> <span
							class="nav__name">Người dùng</span>
						</a> <a href="${pageContext.request.contextPath}/admin/order/index"
							class="nav__link"> <i class='bx bx-cart nav__icon'></i> <span
							class="nav__name">Đơn hàng</span>
						</a>
					</div>
				</div>
			</div>

			<a href="/Assignment_Java4/logout" class="nav__link nav__logout">
				<i class='bx bx-log-out nav__icon'></i> <span class="nav__name">Đăng
					xuất</span>
			</a>
		</nav>
	</div>

	<!--========== CONTENTS ==========-->
	<main style="margin-top: 50px">
		<div class="row">
			<h3 class="p-0">Quản lý đơn hàng</h3>
		</div>
		<div class="row mt-2 mb-4">
			<div class="col-7 p-0">
				<div class="header__search">
					<input type="search" placeholder="Tìm kiếm" class="header__input">
					<i class='bx bx-search header__icon mt-1'></i>
				</div>
				<div class="header__toggle">
					<i class='bx bx-menu' id="header-toggle"></i>
				</div>
			</div>
			<div class="col-5 justify-content-end">
				<div class="row align-items-center">
					<div class="col-5">
						<div class="form-group">
							<select class="form-select" name="filter-categories"
								id="filter-categories">
								<option selected disabled value="none">Bộ lọc</option>
								<option value="1">312321</option>
							</select>
						</div>
					</div>
					<div class="col-4">
						<i class='bx bx-box fs-5'></i> Tổng đơn hàng <span
							class='text-danger'>${count}</span>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-6 border mb-5" style="margin-left: 11px">
				<h5 class="border-bottom mb-3 mt-3">Thông tin thanh toán</h5>
				<label>Tên khách hàng:</label> <input type="text"
					value="${account.fullName}" class="form-control" disabled="disabled">
				<label>Địa chỉ:</label> <input type="text" value="${order.address}"
					class="form-control" disabled="disabled">
				<div class="row mb-3">
					<div class="col-6">
						<label>Email:</label> <input type="email" value="${account.email}"
							class="form-control" disabled="disabled">
					</div>
					<div class="col-6">
						<label>Thời gian tạo:</label> <input type="text" value="${order.createDate}"
							class="form-control" disabled="disabled">
					</div>
				</div>
			</div>
			<div class="col border mb-5" style="margin-left: 40px">
				<h5 class="border-bottom mb-3 mt-3">Thông báo về việc gửi hàng</h5>
				<label>Tên khách hàng:</label> <input type="text"
					value="${account.fullName}" class="form-control" disabled="disabled">
				<label>Địa chỉ:</label> <input type="text" value="${order.address}"
					class="form-control" disabled="disabled"> <label>SĐT:</label>
				<input type="text" value="0385606568" class="form-control"
					disabled="disabled">
			</div>
		</div>
		
		<h5>Chi tiết đơn hàng</h5>
		
		<table class="table">
			<tr class="table-dark">
				<th>STT</th>
				<th>Sản phẩm</th>
				<th>Hình ảnh</th>
				<th>Số lượng</th>
				<th>Đơn giá</th>
				<th>Thành tiền</th>
			</tr>
			<c:forEach items="${listOrderDetails}" var="orderDetail"
				varStatus="counter">
				<tr>
					<td>${counter.count}</td>
					<td>${orderDetail.productById.name}</td>
					<td><img
						src="${pageContext.request.contextPath}/upload/${orderDetail.productById.image}"
						width="70px" /></td>
					<td>${orderDetail.quantity}</td>
					<td><fmt:formatNumber value="${orderDetail.productById.price}"
							pattern="#,###,###" /></td>
					<td><fmt:formatNumber value="${orderDetail.price}"
							pattern="#,###,###" /></td>
				</tr>
			</c:forEach>
		</table>
	</main>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
		integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"
		integrity="sha512-lbwH47l/tPXJYG9AcFNoJaTMhGvYWhVM9YI43CT+uteTRRaiLCui8snIgyAN8XWgNjNhCqlAUdzZptso6OCoFQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>
