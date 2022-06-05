<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="container" style="margin-top: 110px; margin-bottom: 400px">
	<div class="row justify-content-center">
		<div class="text-center mb-3">
			<h3>Order history detail</h3>
		</div>
	</div>
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
				<td><img src="${pageContext.request.contextPath}/upload/${orderDetail.productById.image}" width="70px"/></td>
				<td>${orderDetail.quantity}</td>
				<td><fmt:formatNumber value="${orderDetail.productById.price}" pattern="#,###,###" /></td>
				<td><fmt:formatNumber value="${orderDetail.price}" pattern="#,###,###" /> </td>
			</tr>
		</c:forEach>
	</table>
</div>