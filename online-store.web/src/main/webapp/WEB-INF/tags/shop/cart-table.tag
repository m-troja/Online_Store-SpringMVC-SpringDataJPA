<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="col-md-8">
    <table class="table order-table">
        <thead>
            <tr>
                <th>Product name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Remove from cart</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="total" value="0" scope="page" />
            <c:forEach items="${cart.items}" var="item">
                <tr>
                    <td>${item.product.productName}</td>
                    <td>${item.quantity}</td>
                    <td>
                        <fmt:formatNumber value="${item.product.price}" type="number" />
                        <c:set var="total" value="${total}" scope="page" />
                    </td>
                    <td class="text-center align-middle">
                        <form action="cart/remove" method="POST">
                            <input type="hidden" name="itemId" value="${item.id}" />
                            <input type="hidden" name="cartId" value="${cart.id}" />
                            <button type="submit" class="btn btn-danger btn-remove">
							    Remove
							</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
