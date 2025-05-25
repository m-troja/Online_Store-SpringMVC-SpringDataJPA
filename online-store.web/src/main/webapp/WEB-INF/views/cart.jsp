<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
                    <h2><spring:message code="cart" /></h2>
                </div>
            </div>
        </div>
       <div class="row d-flex align-items-start">
   
    <c:choose>
     
        <c:when test="${not empty cart.items}">
            <div class="col-md-8">
                <shop:cart-table/>
            </div>
            
            <!-- Right side: cart summary with buttons -->
            
            <div class="col-md-4 d-flex flex-column align-items-end">
                <div class="cart-summary-box" style="width: 100%;">
                    <h4><spring:message code="cart.summary" /></h4>
                    <p><spring:message code="cart.total" />:
                        <strong><fmt:formatNumber value="${price}" type="currency"/></strong>
                    </p>
                    
                    <!--  Place order button -->
                    
                    <form action="checkout" method="POST" style="width: 100%;">
                        <input type="hidden" name="cartId" value="${cart.id}" />
                       
                        <spring:message code="cart.place.order" var="placeOrderLabel"/>
                       <input type="submit" class="btn btn-success btn-order" value="${placeOrderLabel}"/>
                   
                    </form>
                    
                    <!--  Clear cart button -->
                    <form action="cart/clear" method="POST" style="width: 100%; margin-top: 8px;">
                        <input type="hidden" name="cartId" value="${cart.id}" />
                       
                       <spring:message code="cart.clear.cart" var="clearCartLabel"/>
                       <input type="submit" class="btn btn-danger btn-clear" value="${clearCartLabel}"/>
                       
                   
                    </form>
                </div>
            </div>
        </c:when>

        <c:otherwise>
            <div class="col-md-12">
                <div class="alert alert-warning" role="alert">
                    <spring:message code="cart.is.empty" />
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
