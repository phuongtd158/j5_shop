<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="profile-wrapper"
	style="margin-top: 250px; margin-bottom: 300px">
	<div class="container ">
		<div class="row">
			<div class="col-4">
				<img src="${pageContext.request.contextPath}/upload/${sessionScope.account.photo}" alt="" class="img-fluid" />
			</div>
			<div class="col-8 ">
				<div class="row ">
					<div class="col d-flex justify-content-end">
						<a class="name" data-bs-target="#profile" data-bs-toggle="modal"
							style="cursor: pointer;">Chỉnh sửa thông tin</a>
					</div>
				</div>
				<div class="row">
					<div class="col mt-3">

						<p class="active" style="margin-right: 20px;">Thông tin cá
							nhân</p>

						<div class="tab-content mt-3">
							<div id="info" class="tab-pane fade in active show">
								<ul class="list-unstyled">
									<li class="d-flex justify-content-between mt-4"><span
										style="font-weight: 600;"> Username: </span> <span>${sessionScope.account.username }</span>
									</li>
									<li class="d-flex justify-content-between mt-4"><span
										style="font-weight: 600;"> Họ và tên: </span> <span>${sessionScope.account.fullName }</span>
									</li>
									<li class="d-flex justify-content-between mt-4"><span
										style="font-weight: 600;">Email:</span> <span>${sessionScope.account.email }</span>
									</li>
									<li class="d-flex justify-content-between mt-4"><span
										style="font-weight: 600;">Role:</span> <span>${sessionScope.account.admin == 1 ? 'Admin':'User' }</span>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" aria-hidden="true" aria-labelledby="profile"
		tabindex="-1">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col form-login">
							<h1 class="form-title text-center">Chỉnh sửa thông tin</h1>
							<form>
								<div class="form-group mt-3">
									<input type="text" class="form-control" placeholder="Tài khoản">
								</div>
								<div class="form-group mt-3">
									<input type="text" class="form-control "
										placeholder="Họ và tên">
								</div>
								<div class="form-group mt-3">
									<input type="email" class="form-control " placeholder="Email">
								</div>
								<div class="form-login-button">
									<button class="btn btn-sign-up form-btn form-control"
										data-bs-dismiss="modal">Chỉnh sửa</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- form-sign-up -->
</div>