<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <shop:css-imports-main/>
</head>
<body>

<shop:header/>

<div class="product-page-main">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="prod-page-title">
                    <h2>Cart</h2>
                </div>
            </div>
        </div>
       <div class="row d-flex align-items-start">
    <c:choose>
        <c:when test="${not empty cart.products}">
            <div class="col-md-8">
                <shop:cart-table/>
            </div>
            <!-- Right: cart summary with buttons -->
            <div class="col-md-4 d-flex flex-column align-items-end">
                <div class="cart-summary-box" style="width: 100%;">
                    <h4>Summary</h4>
                    <p>Total:
                        <strong><fmt:formatNumber value="${price}" type="number"/></strong>
                    </p>
                    <form action="place-order" method="POST" style="width: 100%;">
                        <input type="hidden" name="cartId" value="${cart.id}" />
                        <input type="submit" class="btn btn-success btn-order" value="Place Order"/>
                    </form>
                    <form action="clear-cart" method="POST" style="width: 100%; margin-top: 8px;">
                        <input type="hidden" name="cartId" value="${cart.id}" />
                        <input type="submit" class="btn btn-danger btn-clear" value="Clear Cart"/>
                    </form>
                </div>
            </div>
        </c:when>

        <c:otherwise>
            <div class="col-md-12">
                <div class="alert alert-warning" role="alert">
                    Your cart is empty!
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

    </div>
</div>

<shop:footer/>
<shop:js-imports-main/>

</body>
</html>
