<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,400;0,700;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap"
	rel="stylesheet">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/animate.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery.fancybox.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fonts/icomoon/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fonts/flaticon/font/flaticon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/aos.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

<title>Insert title here</title>
<style type="text/css">
a {
	text-decoration: none;
}
</style>
</head>
<body>

	<!-- Menu -->
	<div class="search-form" id="search-form">
		<form action="">
			<input type="search" class="form-control"
				placeholder="Enter keyword to search...">
			<button class="button">
				<svg width="1em" height="1em" viewBox="0 0 16 16"
					class="bi bi-search" fill="currentColor"
					xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd"
						d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
					<path fill-rule="evenodd"
						d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
				</svg>
			</button>
			<button class="button">
				<div class="close-search">
					<span class="icofont-close js-close-search"></span>
				</div>
			</button>

		</form>
	</div>

	<div class="site-mobile-menu">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close">
				<span class="icofont-close js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>



	<nav class="site-nav mb-5">
		<div class="sticky-nav js-sticky-header">

			<div class="container position-relative">
				<div class="site-navigation text-center dark">
					<a href="${pageContext.request.contextPath}/home"
						class="logo menu-absolute m-0">UntreeStore<span
						class="text-primary">.</span></a>

					<ul class="js-clone-nav pl-0 d-none d-lg-inline-block site-menu">
						<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
						<li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
						<li><a href="${pageContext.request.contextPath}/about">About</a></li>
						<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
					</ul>
					<div class="menu-icons">
						<c:if test="${!empty sessionScope.account}">
							<ul
								class="user-profile js-clone-nav pl-0 d-none d-lg-inline-block site-menu">
								<li class="has-children">
									<a href="${pageContext.request.contextPath}/profile">${sessionScope.account.fullName}</a>
									<ul class="dropdown">
									<c:if test="${sessionScope.account.admin == 1}">
										<li><a href="${pageContext.request.contextPath}/admin/home">Admin page</a></li>
									</c:if>
										<li><a href="${pageContext.request.contextPath}/order-history">Order history</a></li>
										<li><a href="${pageContext.request.contextPath}/change-password">Change password</a></li>
										<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
									</ul>
								</li>
							</ul>
						</c:if>
						<c:if test="${empty sessionScope.account}">
							<ul
								class="user-profile js-clone-nav pl-0 d-none d-lg-inline-block site-menu">
								<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
							</ul>
						</c:if>

						<a href="${pageContext.request.contextPath}/shopping-cart"
							class="cart"> <span class="item-in-cart"> <c:if
									test="${!empty sessionScope.count}">
							${sessionScope.count}
							</c:if> <c:if test="${empty sessionScope.count}">
							0
							</c:if>

						</span> <svg width="1em" height="1em" viewBox="0 0 16 16"
								class="bi bi-cart" fill="currentColor"
								xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd"
									d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z" />
							</svg>
						</a>

					</div>

					<a href="#"
						class="burger ml-auto float-right site-menu-toggle js-menu-toggle d-inline-block d-lg-none"
						data-toggle="collapse" data-target="#main-navbar"> <span></span>
					</a>

				</div>
			</div>
		</div>
	</nav>


	<!--------------------------Menu-------------------------------->



	<!--------------------------View-------------------------------->
	<jsp:include page="${view}" />

	<!--------------------------View-------------------------------->


	<!--  Footer-->
	<div class="untree_co-section bg-light">
		<div class="container">
			<div class="row align-items-stretch">
				<div class="col-12 col-sm-6 col-md-4 mb-3 mb-md-0">
					<div class="feature h-100">
						<div class="icon mb-4">
							<svg width="1em" height="1em" viewBox="0 0 16 16"
								class="bi bi-truck" fill="currentColor"
								xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd"
									d="M0 3.5A1.5 1.5 0 0 1 1.5 2h9A1.5 1.5 0 0 1 12 3.5v7h-1v-7a.5.5 0 0 0-.5-.5h-9a.5.5 0 0 0-.5.5v7a.5.5 0 0 0 .5.5v1A1.5 1.5 0 0 1 0 10.5v-7zM4.5 11h6v1h-6v-1z" />
								<path fill-rule="evenodd"
									d="M11 5h2.02a1.5 1.5 0 0 1 1.17.563l1.481 1.85a1.5 1.5 0 0 1 .329.938V10.5a1.5 1.5 0 0 1-1.5 1.5h-1v-1h1a.5.5 0 0 0 .5-.5V8.35a.5.5 0 0 0-.11-.312l-1.48-1.85A.5.5 0 0 0 13.02 6H12v4.5h-1V5zm-8 8a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 1a2 2 0 1 0 0-4 2 2 0 0 0 0 4z" />
								<path fill-rule="evenodd"
									d="M12 13a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 1a2 2 0 1 0 0-4 2 2 0 0 0 0 4z" />
							</svg>
						</div>
						<h3>Worldwide Delivery</h3>
						<p>Far far away, behind the word mountains, far from the
							countries.</p>
					</div>
				</div>
				<div class="col-12 col-sm-6 col-md-4 mb-3 mb-md-0">
					<div class="feature h-100">
						<div class="icon mb-4">
							<svg width="1em" height="1em" viewBox="0 0 16 16"
								class="bi bi-shield-lock" fill="currentColor"
								xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd"
									d="M5.443 1.991a60.17 60.17 0 0 0-2.725.802.454.454 0 0 0-.315.366C1.87 7.056 3.1 9.9 4.567 11.773c.736.94 1.533 1.636 2.197 2.093.333.228.626.394.857.5.116.053.21.089.282.11A.73.73 0 0 0 8 14.5c.007-.001.038-.005.097-.023.072-.022.166-.058.282-.111.23-.106.525-.272.857-.5a10.197 10.197 0 0 0 2.197-2.093C12.9 9.9 14.13 7.056 13.597 3.159a.454.454 0 0 0-.315-.366c-.626-.2-1.682-.526-2.725-.802C9.491 1.71 8.51 1.5 8 1.5c-.51 0-1.49.21-2.557.491zm-.256-.966C6.23.749 7.337.5 8 .5c.662 0 1.77.249 2.813.525a61.09 61.09 0 0 1 2.772.815c.528.168.926.623 1.003 1.184.573 4.197-.756 7.307-2.367 9.365a11.191 11.191 0 0 1-2.418 2.3 6.942 6.942 0 0 1-1.007.586c-.27.124-.558.225-.796.225s-.526-.101-.796-.225a6.908 6.908 0 0 1-1.007-.586 11.192 11.192 0 0 1-2.417-2.3C2.167 10.331.839 7.221 1.412 3.024A1.454 1.454 0 0 1 2.415 1.84a61.11 61.11 0 0 1 2.772-.815z" />
								<path d="M9.5 6.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z" />
								<path
									d="M7.411 8.034a.5.5 0 0 1 .493-.417h.156a.5.5 0 0 1 .492.414l.347 2a.5.5 0 0 1-.493.585h-.835a.5.5 0 0 1-.493-.582l.333-2z" />
							</svg>
						</div>
						<h3>Secure Payments</h3>
						<p>Far far away, behind the word mountains, far from the
							countries.</p>
					</div>
				</div>
				<div class="col-12 col-sm-6 col-md-4 mb-3 mb-md-0">
					<div class="feature h-100">
						<div class="icon mb-4">
							<svg width="1em" height="1em" viewBox="0 0 16 16"
								class="bi bi-arrow-counterclockwise" fill="currentColor"
								xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd"
									d="M12.83 6.706a5 5 0 0 0-7.103-3.16.5.5 0 1 1-.454-.892A6 6 0 1 1 2.545 5.5a.5.5 0 1 1 .91.417 5 5 0 1 0 9.375.789z" />
								<path fill-rule="evenodd"
									d="M7.854.146a.5.5 0 0 0-.708 0l-2.5 2.5a.5.5 0 0 0 0 .708l2.5 2.5a.5.5 0 1 0 .708-.708L5.707 3 7.854.854a.5.5 0 0 0 0-.708z" />
							</svg>
						</div>
						<h3>Simple Returns</h3>
						<p>Far far away, behind the word mountains, far from the
							countries.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.untree_co-section -->

	<div class="site-footer">


		<div class="container">
			<div class="row justify-content-between">
				<div class="col-lg-5">
					<div class="widget mb-4">
						<h3 class="mb-2">About UntreeStore</h3>
						<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
							Voluptate modi cumque rem recusandae quaerat at asperiores beatae
							saepe repudiandae quam rerum aspernatur dolores et ipsa obcaecati
							voluptates libero</p>
					</div>
					<div class="widget">
						<h3>Join our mailing list and receive exclusives</h3>
						<form action="#" class="subscribe">
							<div class="d-flex">
								<input type="email" class="form-control"
									placeholder="Email address"> <input type="submit"
									class="btn btn-black" value="Subscribe">
							</div>
						</form>


					</div>
				</div>
				<div class="col-lg-2">
					<div class="widget">
						<h3>Help</h3>
						<ul class="list-unstyled">
							<li><a href="#">Contact us</a></li>
							<li><a href="#">Account</a></li>
							<li><a href="#">Shipping</a></li>
							<li><a href="#">Returns</a></li>
							<li><a href="#">FAQ</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="widget">
						<h3>About</h3>
						<ul class="list-unstyled">
							<li><a href="#">About us</a></li>
							<li><a href="#">Press</a></li>
							<li><a href="#">Careers</a></li>
							<li><a href="#">Team</a></li>
							<li><a href="#">FAQ</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="widget">
						<h3>Shop</h3>
						<ul class="list-unstyled">
							<li><a href="#">Store</a></li>
							<li><a href="#">Gift Cards</a></li>
							<li><a href="#">Student Discount</a></li>
						</ul>
					</div>
				</div>

			</div>


			<div class="row mt-5">
				<div class="col-12 text-center">
					<ul class="list-unstyled social">
						<li><a href="#"><span class="icon-facebook"></span></a></li>
						<li><a href="#"><span class="icon-instagram"></span></a></li>
						<li><a href="#"><span class="icon-linkedin"></span></a></li>
						<li><a href="#"><span class="icon-twitter"></span></a></li>
					</ul>
				</div>
				<div class="col-12 text-center copyright">
					<p>
						Copyright &copy;
						<script>
							document.write(new Date().getFullYear());
						</script>
						. All Rights Reserved. &mdash; Designed with love by <a
							href="https://untree.co">Untree.co</a>
						<!-- License information: https://untree.co/license/ -->
					</p>

				</div>
			</div>
		</div>
		<!-- /.container -->
	</div>
	<!-- /.site-footer -->

	<div id="overlayer"></div>
	<div class="loader">
		<div class="spinner-border" role="status">
			<span class="sr-only">Loading...</span>
		</div>
	</div>

	<!--  Footer-->
	<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.animateNumber.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.waypoints.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.fancybox.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.sticky.js"></script>
	<script src="${pageContext.request.contextPath}/js/aos.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>

</body>
</html>