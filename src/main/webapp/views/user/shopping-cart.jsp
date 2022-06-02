<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-heading bg-light">
	<div class="container">
		<div class="row align-items-end text-center">
			<div class="col-lg-7 mx-auto">
				<h1>Cart</h1>
				<p class="mb-4">
					<a href="index.html">Home</a> / <strong>Cart</strong>
				</p>
			</div>
		</div>
	</div>
</div>



<div class="untree_co-section">
	<div class="container">
		<div class="row mb-5">
			<form class="col-md-12" method="post">
				<div class="site-blocks-table">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th class="product-thumbnail">Image</th>
								<th class="product-name">Product</th>
								<th class="product-price">Price</th>
								<th class="product-quantity">Quantity</th>
								<th class="product-total">Total</th>
								<th class="product-remove">Remove</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sessionScope.cart}" var="cart">
								<tr>
									<td class="product-thumbnail"><img
										src="${pageContext.request.contextPath}/upload/${cart.value.product.image}"
										alt="Image" class="img-fluid"></td>
									<td class="product-name">
										<h2 class="h5 text-black">${cart.value.product.name}</h2>
									</td>
									<td><fmt:formatNumber value="${cart.value.product.price}"
											pattern="#,###,###" /></td>
									<td>
										<div class="input-group mb-3" style="max-width: 120px;">
											<div class="input-group-prepend">
												<button class="btn btn-outline-black js-btn-minus"
													type="button">&minus;</button>
											</div>
											<input type="text" class="form-control text-center"
												value=" ${cart.value.quantity}" placeholder=""
												aria-label="Example text with button addon"
												aria-describedby="button-addon1">
											<div class="input-group-append">
												<button class="btn btn-outline-black js-btn-plus"
													type="button">&plus;</button>
											</div>
										</div>

									</td>
									<td><fmt:formatNumber
											value=" ${cart.value.quantity * cart.value.product.price}"
											pattern="#,###,###" /></td>
									<td><a href="#" class="btn btn-black btn-sm">X</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</form>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div class="row mb-5">
					<div class="col-md-6 mb-3 mb-md-0">
						<button class="btn btn-black btn-sm btn-block">Update
							Cart</button>
					</div>
					<div class="col-md-6">
						<button class="btn btn-outline-black btn-sm btn-block">Continue
							Shopping</button>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<label class="text-black h4" for="coupon">Coupon</label>
						<p>Enter your coupon code if you have one.</p>
					</div>
					<div class="col-md-8 mb-3 mb-md-0">
						<input type="text" class="form-control py-3" id="coupon"
							placeholder="Coupon Code">
					</div>
					<div class="col-md-4">
						<button class="btn btn-black">Apply Coupon</button>
					</div>
				</div>
			</div>
			<div class="col-md-6 pl-5">
				<div class="row justify-content-end">
					<div class="col-md-7">
						<div class="row">
							<div class="col-md-12 text-right border-bottom mb-5">
								<h3 class="text-black h4 text-uppercase">Cart Totals</h3>
							</div>
						</div>
						<div class="row mb-5">
							<div class="col-md-6">
								<span class="text-black">Total</span>
							</div>
							<div class="col-md-6 text-right">
								<strong class="text-black"> <fmt:formatNumber
										value="${sessionScope.totalPrice}" pattern="#,###,###" />
								</strong>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<a href="${pageContext.request.contextPath}/check-out"
									class="btn btn-black btn-lg py-3 btn-block"
									onclick="window.location='checkout.html'">Proceed To
									Checkout</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>