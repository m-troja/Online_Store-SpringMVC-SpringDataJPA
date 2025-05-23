<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="col-md-8">
    <table class="table order-table">
        <thead>
            <tr>
                <th>Product name</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="total" value="0" scope="page" />
            <c:forEach items="${cart.products}" var="product">
                <tr>
                    <td>${product.productName}</td>
                    <td>
                        <fmt:formatNumber value="${product.price}" type="number" />
                        <c:set var="total" value="${total + product.price}" scope="page" />
                    </td>
                    <td class="text-center align-middle">
                        <form action="cart/remove" method="POST">
                            <input type="hidden" name="productId" value="${product.id}" />
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
