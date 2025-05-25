<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="col-md-12 col-sm-24">
	<div class="md-prod-page">
		<div class="description-box">
			<div class="dex-a">
				<table class="order-table">
				  <tr>
				    <th><spring:message code="orders.orderid" /></th>
				    <th><spring:message code="orders.products" /></th>
				    <th><spring:message code="orders.status" /></th>
				  </tr>
				
				  <c:forEach items="${purchases}" var="purchase">
				    <tr>
				      <td>${purchase.id}</td>
				      <td>
				        <c:forEach items="${purchase.products}" var="product" varStatus="status">
				          ${product.productName}<c:if test="${!status.last}">, </c:if>
				        </c:forEach>
				      </td>
				      <td>${purchase.purchaseStatus.statusName}</td>
				    </tr>
				  </c:forEach>
				</table>
			</div>

		</div>
	</div>
</div>