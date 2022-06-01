<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css"
	rel="stylesheet" />
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

			<a href="${pageContext.request.contextPath}/logout"
				class="nav__link nav__logout"> <i
				class='bx bx-log-out nav__icon'></i> <span class="nav__name">Đăng
					xuất</span>
			</a>
		</nav>
	</div>

	<!--========== CONTENTS ==========-->
	<main style="margin-top: 50px">
		<div class="container">
			<div class="row">
				<h3 class="text-center">Thêm người dùng</h3>
				<form:form
					action="${pageContext.request.contextPath}/admin/account/store"
					modelAttribute="accountModel">
					<div class="row">
						<div class="col-3">
							<img class="img-fluid" id="imgPreview" src="" alt=""
								style="border: 1px solid #ccc;"> <label>Hình ảnh</label>
							<form:input type="file" path="photo" />
							<form:errors path="photo" element="span"
								cssClass="text-danger d-block" />
						</div>
						<div class="col-9">
							<div class="form-group">
								<label>Tên tài khoản</label>
								<form:input path="username" class="form-control" />
								<form:errors path="username" element="span"
									cssClass="text-danger d-block" />
							</div>
							<div class="form-group">
								<label>Tên người dùng</label>
								<form:input path="fullname" class="form-control" />
								<form:errors path="fullname" element="span"
									cssClass="text-danger d-block" />
							</div>
							<div class="form-group">
								<label>Password</label>
								<form:password path="password" class="form-control" />
								<form:errors path="password" element="span"
									cssClass="text-danger d-block" />
							</div>
							<div class="form-group">
								<label>Email</label>
								<form:input type="email" path="email" class="form-control" />
								<form:errors path="email" element="span"
									cssClass="text-danger d-block" />
							</div>
							<div class="form-group">
								<label>Activated</label>
								<form:select path="activated" class="form-select">
									<option value="1">Activated</option>
									<option value="0">Inactivated</option>
								</form:select>
								<form:errors path="activated" element="span"
									cssClass="text-danger d-block" />
							</div>
							<div class="form-group">
								<label>Vai trò</label>
								<form:select path="admin" class="form-select">
									<option value="1">Admin</option>
									<option value="0">User</option>
								</form:select>
								<form:errors path="admin" element="span"
									cssClass="text-danger d-block" />
							</div>
							<button class="btn btn-primary mt-3">Thêm</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
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
		src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"
		integrity="sha512-lbwH47l/tPXJYG9AcFNoJaTMhGvYWhVM9YI43CT+uteTRRaiLCui8snIgyAN8XWgNjNhCqlAUdzZptso6OCoFQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script type="text/javascript">
		 $(document).ready(() => {
		        $('#photo').change(function () {
		            const file = this.files[0];
		            if (file) {
		                let reader = new FileReader();
		                reader.onload = function (event) {
		                    console.log(event.target.result);
		                    $('#imgPreview').attr('src', event.target.result);
		                }
		                reader.readAsDataURL(file);
		            }
		        });
		    });
		</script>
</body>
</html>