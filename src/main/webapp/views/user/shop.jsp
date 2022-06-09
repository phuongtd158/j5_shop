<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page-heading bg-light">
	<div class="container">
		<div class="row align-items-end text-center">
			<div class="col-lg-7 mx-auto">
				<h1>Shop</h1>
				<p class="mb-4">
					<a href="${pageContext.request.contextPath}/home">Home</a> / <strong>Shop</strong>
				</p>
			</div>
		</div>
	</div>
</div>

<div class="untree_co-section pt-3">
	<div class="container">

		<div class="row align-items-center mb-5">
			<div class="col-lg-8">
				<h2 class="mb-3 mb-lg-0">Category</h2>
			</div>
			<div class="col-lg-4">

				<div class="d-flex sort align-items-center justify-content-lg-end">
					<strong class="mr-3">Sort by:</strong>
					<form class="" action="${pageContext.request.contextPath}/shop">
						<select required name="sort-by" onchange="this.form.submit()">
							<option value="1" ${sortBy == '1' ? 'selected' : ''}>Newest Items</option>
							<option value="2" ${sortBy == '2' ? 'selected' : ''}>Price: Ascending</option>
							<option value="3" ${sortBy == '3' ? 'selected' : ''}>Price: Descending</option>
						</select>
					</form>
				</div>
			</div>
		</div>

		<div class="row">

			<div class="col-md-3">
				<ul class="list-unstyled categories">
					<li><a href="${pageContext.request.contextPath}/shop">All</a></li>
					<c:forEach items="${listCategories}" var="category">
						<li><a
							href="${pageContext.request.contextPath}/shop?category=${category.id}">${category.name}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-md-9">
				<div class="row">
					<c:if test="${empty listProducts }">
						<h3 class="text-center">Product is empty</h3>
					</c:if>

					<c:forEach items="${listProducts}" var="product">
						<div class="col-6 col-sm-6 col-md-6 mb-4 col-lg-4">
							<div class="product-item">
								<a
									href="${pageContext.request.contextPath}/product-detail/${product.id}"
									class="product-img">
									<div class="label sale top-right">
										<div class='content'>Sale</div>
									</div> <img
									src="${pageContext.request.contextPath}/upload/${product.image}"
									alt="Image" class="img-fluid">
								</a>
								<h3 class="title">
									<a href="#">${product.name}</a>
								</h3>
								<div class="price">
									<span><fmt:formatNumber value="${product.price}"
											pattern="#,###,###" /></span>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<div class="row mt-5 pb-5">
					<div class="col-lg-12 d-flex justify-content-center">
						<div class="custom-pagination">
							<ul class="list-unstyled">
								<li><a href="#"> <svg width="1em" height="1em"
											viewBox="0 0 16 16" class="bi bi-arrow-left"
											fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
												d="M5.854 4.646a.5.5 0 0 1 0 .708L3.207 8l2.647 2.646a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 0 1 .708 0z" />
                        <path fill-rule="evenodd"
												d="M2.5 8a.5.5 0 0 1 .5-.5h10.5a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
                      </svg>
								</a></li>
								<li class="active"><span>1</span></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#"> <svg width="1em" height="1em"
											viewBox="0 0 16 16" class="bi bi-arrow-right"
											fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
												d="M10.146 4.646a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L12.793 8l-2.647-2.646a.5.5 0 0 1 0-.708z" />
                        <path fill-rule="evenodd"
												d="M2 8a.5.5 0 0 1 .5-.5H13a.5.5 0 0 1 0 1H2.5A.5.5 0 0 1 2 8z" />
                      </svg>
								</a></li>
							</ul>
						</div>
					</div>
				</div>


			</div>
		</div>
	</div>
	<!-- /.untree_co-section -->

	<div class="untree_co-section">
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
	<!-- /.untree_co-section -->
</div>