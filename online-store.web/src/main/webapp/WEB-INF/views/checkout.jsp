<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <shop:css-imports-main/>
</head>
<body>

<shop:header/>

<div class="product-page-main">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="prod-page-title">
                    <h2><spring:message code="checkout" /></h2>
                </div>
            </div>
        </div>
       <div class="row d-flex align-items-start">
   
    <c:choose>
     
        <c:when test="${not empty productsFromPurchase}">
            <div class="col-md-8">
                <shop:checkout-table/>
            </div>
            <!-- Right: cart summary with buttons -->
            <div class="col-md-4 d-flex flex-column align-items-end">
               <h5>${orderStatus}</h5>
               <c:remove var="orderStatus"/>
            </div>
            
        </c:when>

        <c:otherwise>
            <div class="col-md-12">
                <div class="alert alert-warning" role="alert">
                    <spring:message code="checkout.not.items.purchased" />
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
