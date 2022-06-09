<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Title</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
.ftco-section {
	padding: 12rem 0;
}
</style>
</head>
<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-8 offset-4 m-auto"
					style="box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;">
					<div class="row">
						<div class="col login-wrap p-5">
							<div class="d-flex">
								<div class="w-100">
									<h3 class="mb-3 text-center">Change Password</h3>
								</div>
							</div>
							<p class="text-center text-danger">
								<c:if test="${!empty sessionScope.errorChangePassword}">
									${sessionScope.errorChangePassword}
								<c:remove var="errorChangePassword" scope="session" />
								</c:if>
							</p>
							<form action="${pageContext.request.contextPath}/change-password"
								method="post" class="login-form">
								<div class="form-group mb-3">
									<input type="password" class="form-control password"
										name="old-password" placeholder="Old Password" autocomplete="off"
										required>
								</div>
								<div class="form-group mb-3">
									<input type="password" class="form-control password"
										name="new-password" placeholder="New Password" autocomplete="off"
										required>
								</div>
								<div class="form-group mb-3">
									<input type="password" class="form-control"
										name="password-confirm" placeholder="Confirm"
										autocomplete="off" required
										oninput="checkPasswordMatch(this);">
								</div>
								<button class="btn btn-primary">Change password</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script type="text/javascript">
		function checkPasswordMatch(fieldConfirmPassword) {
			const password = document.querySelector(".new-password").value;
			if (fieldConfirmPassword.value != password) {
				fieldConfirmPassword.setCustomValidity("Password not match !")
			} else {
				fieldConfirmPassword.setCustomValidity("")
			}
		}
	</script>
</body>
</html>
