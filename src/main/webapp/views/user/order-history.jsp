<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="container" style="margin-top: 150px; margin-bottom: 200px">
	<div class="row justify-content-center">
		<div class="text-center mb-3">
			<h3>Order history</h3>
		</div>
	</div>

	<c:if test="${empty listOrders}">
		<h4 class="text-center mt-5">Order is empty</h4>
		<div class="text-center">
			<a href="${pageContext.request.contextPath}/shop"> Return </a>
		</div>
	</c:if>
	<c:if test="${!empty listOrders}">
		<table class="table">
			<tr class="table-dark">
				<th>Mã hóa đơn</th>
				<th>Ngày tạo</th>
				<th>Địa chỉ</th>
				<th>Trạng thái</th>
				<th>Hành động</th>
			</tr>
			<c:forEach items="${listOrders}" var="order">
				<tr>
					<td>${order.id}</td>
					<td>${order.createDate}</td>
					<td>${order.address}</td>
					<td>
						<c:if test="${order.status == 0}">
                            Chờ xác nhận
                        </c:if> <c:if test="${order.status == 1}">
                            Đã xác nhận
                        </c:if> <c:if test="${order.status == 2 }">
                            Đã hủy
                        </c:if>
                    </td>
					<td><a class="btn btn-warning"
						href="${pageContext.request.contextPath}/order-detail/${order.id}">
							Chi tiết </a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</div>