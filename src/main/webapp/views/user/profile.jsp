<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="profile-wrapper"
	style="margin-top: 250px; margin-bottom: 300px">
	<div class="container ">
		<form:form action="${pageContext.request.contextPath}/update-profile"
			method="POST" modelAttribute="accountModel"
			enctype="multipart/form-data">
			<div class="row">

				<div class="col-4">
					<img class="img-fluid" id="imgPreview"
						src="${pageContext.request.contextPath}/upload/${sessionScope.account.photo}"
						alt="" style="border: 1px solid #ccc; margin-top: 90px;">
					<form:input type="file" path="imageFile" />
					<form:errors path="photo" element="span"
						cssClass="text-danger d-block" />
				</div>
				<div class="col-8 ">
					<div class="row">
						<div class="col mt-3">
							<p class="active" style="margin-right: 20px;">Thông tin cá
								nhân</p>

							<div class=" mt-3">
								<ul class="list-unstyled">
									<li class=" mt-4"><span style="font-weight: 600;">
											Username: </span> <form:input type="text" class="form-control"
											value="${sessionScope.account.username }" path="username" />
										<form:errors path="username" element="span"
											cssClass="text-danger d-block" /></li>
									<li class="mt-4"><span style="font-weight: 600;">
											Họ và tên: </span> <form:input type="text" class="form-control"
											value="${sessionScope.account.fullName }" path="fullname" />
										<form:errors path="fullname" element="span"
											cssClass="text-danger d-block" /></li>
									<li class="mt-4"><span style="font-weight: 600;">Email:</span>
										<form:input type="email" class="form-control"
											value="${sessionScope.account.email }" path="email" /> <form:errors
											path="email" element="span" cssClass="text-danger d-block" />
									</li>
									<li class=" mt-4"><span style="font-weight: 600;">Role:</span>
										<span>${sessionScope.account.admin == 1 ? 'Admin':'User' }</span>
									</li>

								</ul>
								<button class="btn btn-dark" style="float: right">Submit</button>
							</div>
						</div>
					</div>
				</div>


			</div>
		</form:form>
	</div>
</div>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
$(document).ready(() => {
    $('#imageFile').change(function () {
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
