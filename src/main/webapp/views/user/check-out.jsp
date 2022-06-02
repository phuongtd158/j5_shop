<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-heading bg-light">
	<div class="container">
		<div class="row align-items-end text-center">
			<div class="col-lg-7 mx-auto">
				<h1>Checkout</h1>
				<p class="mb-4">
					<a href="index.html">Home</a> / <strong>Checkout</strong>
				</p>
			</div>
		</div>
	</div>
</div>



<div class="untree_co-section">
	<div class="container">
		<div class="row mb-5">
			<div class="col-md-12">
				<div class="border p-4 rounded" role="alert">
					Returning customer? <a href="#">Click here</a> to login
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 mb-5 mb-md-0">
				<h2 class="h3 mb-3 text-black">Billing Details</h2>
				<div class="p-3 p-lg-5 border">
					<div class="form-group row">
						<div class="col-md-6">
							<label for="c_fname" class="text-black">First Name <span
								class="text-danger">*</span></label> <input type="text"
								class="form-control" id="c_fname" name="c_fname">
						</div>
						<div class="col-md-6">
							<label for="c_lname" class="text-black">Last Name <span
								class="text-danger">*</span></label> <input type="text"
								class="form-control" id="c_lname" name="c_lname">
						</div>
					</div>


					<div class="form-group row">
						<div class="col-md-12">
							<label for="c_address" class="text-black">Address <span
								class="text-danger">*</span></label> <input type="text"
								class="form-control" id="c_address" name="c_address"
								placeholder="Street address">
						</div>
					</div>
					<div class="form-group row mb-5">
						<div class="col-md-6">
							<label for="c_email_address" class="text-black">Email
								Address <span class="text-danger">*</span>
							</label> <input type="text" class="form-control" id="c_email_address"
								name="c_email_address">
						</div>
						<div class="col-md-6">
							<label for="c_phone" class="text-black">Phone <span
								class="text-danger">*</span></label> <input type="text"
								class="form-control" id="c_phone" name="c_phone"
								placeholder="Phone Number">
						</div>
					</div>

					<div class="form-group">
						<label for="c_order_notes" class="text-black">Order Notes</label>
						<textarea name="c_order_notes" id="c_order_notes" cols="30"
							rows="5" class="form-control"
							placeholder="Write your notes here..."></textarea>
					</div>

				</div>
			</div>
			<div class="col-md-6">
				<div class="row mb-5">
					<div class="col-md-12">
						<h2 class="h3 mb-3 text-black">Your Order</h2>
						<div class="p-3 p-lg-5 border">
							<table class="table site-block-order-table mb-5">
								<thead>
									<th>Product</th>
									<th>Total</th>
								</thead>
								<tbody>
									<c:forEach items="${sessionScope.cart}" var="cart">
										<tr>
											<td>${cart.value.product.name}<strong class="mx-2">x</strong>
												${cart.value.quantity}
											</td>
											<td><fmt:formatNumber
													value=" ${cart.value.quantity * cart.value.product.price}"
													pattern="#,###,###" /></td>
										</tr>
									</c:forEach>

									<tr>
										<td class="text-black font-weight-bold"><strong>Order
												Total</strong></td>
										<td class="text-black font-weight-bold"><strong><fmt:formatNumber
													value="${sessionScope.totalPrice}" pattern="#,###,###" /></strong></td>
									</tr>
								</tbody>
							</table>
							<div class="form-group">
								<button class="btn btn-black btn-lg py-3 btn-block"
									onclick="window.location='thankyou.html'">Place Order</button>
							</div>

						</div>
					</div>
				</div>

			</div>
		</div>
		<!-- </form> -->
	</div>
</div>