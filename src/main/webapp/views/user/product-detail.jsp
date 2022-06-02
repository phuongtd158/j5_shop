<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-heading bg-light">
	<div class="container">
		<div class="row align-items-end text-center">
			<div class="col-lg-7 mx-auto">
				<h1>Shop</h1>
				<p class="mb-4">
					<a href="index.html">Home</a> / <strong>Shop</strong>
				</p>
			</div>
		</div>
	</div>
</div>
<div class="untree_co-section pt-3">
	<div class="container">
		<div class="row">
			<div class="col-md-6 text-center">
				<div class="product-image mt-3">
					<img class="img-fluid"
						src="${pageContext.request.contextPath}/upload/${product.image}">
				</div>

			</div>
			<div class="col-md-6 mt-5 mt-md-2 text-md-left">
				<h2 class="mb-3 mt-0">${product.name}</h2>
				<p class="lead mt-2 mb-3 primary-color">
					<fmt:formatNumber value="${product.price}" pattern="#,###,###" />
				</p>
				<h5 class="mt-4">Description</h5>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Vivamus venenatis velit vestibulum massa sollicitudin auctor. Cras
					ac venenatis orci. Ut consequat, purus ut ultrices ultricies, nisi
					sem ornare quam, sed ultricies mi nisl sit amet purus.</p>
				<p>Suspendisse potenti. Nunc in libero luctus, sagittis leo sit
					amet, volutpat lacus. Quisque a porta risus. Integer aliquet nibh
					vitae vestibulum accumsan</p>
				<h5 class="mt-5">Care Instructions</h5>
				<p>Suspendisse cursus erat sed sem sagittis cursus. Etiam porta
					sem malesuada magna mollis euismod.</p>

				<form action="${pageContext.request.contextPath}/add-to-cart" method="post">
					<input type="hidden" name="id" value="${product.id}">
					Quantity: <input type="text" class="form-control quantity mb-4"
						name="quantity" value="1">
					<button class="btn btn-full-width btn-lg btn-outline-primary">Add
						to cart</button>
				</form>
			</div>
		</div>
	</div>
	<!-- /.untree_co-section -->

	<div class="untree_co-section mt-5">
		<div class="container">
			<div class="row mb-5 align-items-center">
				<div class="col-md-6">
					<h2 class="h3">Popular Items</h2>
				</div>
				<div class="col-sm-6 carousel-nav text-sm-right">
					<a href="#" class="prev js-custom-prev-v2"> <svg width="1em"
							height="1em" viewBox="0 0 16 16" class="bi bi-arrow-left-circle"
							fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
								d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                <path fill-rule="evenodd"
								d="M8.354 11.354a.5.5 0 0 0 0-.708L5.707 8l2.647-2.646a.5.5 0 1 0-.708-.708l-3 3a.5.5 0 0 0 0 .708l3 3a.5.5 0 0 0 .708 0z" />
                                <path fill-rule="evenodd"
								d="M11.5 8a.5.5 0 0 0-.5-.5H6a.5.5 0 0 0 0 1h5a.5.5 0 0 0 .5-.5z" />
                            </svg>
					</a> <a href="#" class="next js-custom-next-v2"> <svg width="1em"
							height="1em" viewBox="0 0 16 16" class="bi bi-arrow-right-circle"
							fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
								d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                <path fill-rule="evenodd"
								d="M7.646 11.354a.5.5 0 0 1 0-.708L10.293 8 7.646 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0z" />
                                <path fill-rule="evenodd"
								d="M4.5 8a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1H5a.5.5 0 0 1-.5-.5z" />
                            </svg>
					</a>
				</div>
			</div>
			<!-- /.heading -->
			<div class="owl-3-slider owl-carousel">
				<div class="item">
					<div class="product-item">
						<a href="shop-single.html" class="product-img">
							<div class="label sale top-right">
								<div class='content'>Sale</div>
							</div> <img src="images/products/watch-1-min.jpg" alt="Image"
							class="img-fluid">
						</a>
						<h3 class="title">
							<a href="#">The Murray</a>
						</h3>
						<div class="price">
							<del>£99.00</del>
							&mdash; <span>£69.00</span>
						</div>
					</div>
				</div>
				<!-- /.item -->


				<div class="item">
					<div class="product-item">
						<a href="shop-single.html" class="product-img">

							<div class="label new top-right">
								<div class='content'>New</div>
							</div> <img src="images/products/jacket-1-min.jpg" alt="Image"
							class="img-fluid">
						</a>
						<h3 class="title">
							<a href="#">Dark Jacket</a>
						</h3>
						<div class="price">
							<span>£69.00</span>
						</div>
					</div>
				</div>
				<!-- /.item -->


				<div class="item">
					<div class="product-item">
						<a href="shop-single.html" class="product-img">
							<div class="label new top-right">
								<div class='content'>New</div>
							</div>

							<div class="label sale top-right second">
								<div class='content'>Sale</div>
							</div> <img src="images/products/bottoms-1-min.jpg" alt="Image"
							class="img-fluid">
						</a>
						<h3 class="title">
							<a href="#">Chino Bottoms</a>
						</h3>
						<div class="price">
							<del>£99.00</del>
							&mdash; <span>£69.00</span>
						</div>
					</div>
				</div>
				<!-- /.item -->

				<div class="item">
					<div class="product-item">
						<a href="shop-single.html" class="product-img"> <img
							src="images/products/sock-1-min.jpg" alt="Image"
							class="img-fluid">
						</a>
						<h3 class="title">
							<a href="#">The Modern Sock</a>
						</h3>
						<div class="price">
							<span>£29.00</span>
						</div>
					</div>
				</div>
				<!-- /.item -->

			</div>
		</div>
		<!-- /.container -->
	</div>
</div>
<!-- /.untree_co-section -->