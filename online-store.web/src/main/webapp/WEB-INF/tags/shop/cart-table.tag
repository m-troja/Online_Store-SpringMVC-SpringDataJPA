<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="col-md-8">
    <table class="table order-table">
        <thead>
            <tr>
                <th><spring:message code="cart.product.name" /></th>
                <th><spring:message code="cart.product.quantity" /></th>
                <th><spring:message code="cart.product.price" /></th>
                <th><spring:message code="cart.remove.from.cart" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cart.items}" var="item">
                <tr>
                    <td>${item.product.productName}</td>
                    <td>${item.quantity}</td>
                    <td>
                        <fmt:formatNumber value="${item.product.price}" type="currency" />
                    </td>
                    <td class="text-center align-middle">
                        <form action="cart/remove" method="POST">
                            <input type="hidden" name="itemId" value="${item.id}" />
                            <input type="hidden" name="cartId" value="${cart.id}" />
                            <button type="submit" class="btn btn-danger btn-remove">
							    <spring:message code="cart.remove" />
							</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
