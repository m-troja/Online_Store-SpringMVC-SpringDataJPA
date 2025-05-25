<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="col-md-8">
    <table class="table order-table">
        <thead>
            <tr>
                <th><spring:message code="checkout.product.name" /></th>
                <th><spring:message code="checkout.price" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${productsFromPurchase}" var="product">
                <tr>
                    <td>${product.productName}</td>
                    <td>
                        <fmt:formatNumber value="${product.price}" type="currency" />
                    </td>
                   
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
