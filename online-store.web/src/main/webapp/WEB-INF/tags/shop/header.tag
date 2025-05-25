<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.locale}" scope="session" />


<header id="header" class="top-head">

	<!-- Static navbar -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 col-sm-12 left-rs">
				
					<div class="navbar-header">
						<button type="button" id="top-menu"
							class="navbar-toggle collapsed" data-toggle="collapse"
							data-target="#navbar" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a href="/online-store.web/homepage" class="navbar-brand"><img
							src="/online-store.web/images/logo.png" alt="" /></a>
					</div>
					<form class="navbar-form navbar-left web-sh" action="search"
						method="GET">
						<div class="form">
						<spring:message code='search.placeholder' var="searchPlaceholder"/>
							<input type="text" class="form-control" name="searchQuery"
								placeholder="${searchPlaceholder}"> 
							<input type="hidden" name="page" value="1">
						</div>
					</form>

				</div>
				
				<div class="col-md-8 col-sm-12">							
					<div class="right-nav">
					
					<!-- My orders button -->
					<c:choose>
						    <c:when test="${loggedInUser != null}">
						        <a href="/online-store.web/my-orders" class="btn btn-default cart-btn position-relative">
						            <span class="glyphicon glyphicon-list-alt"></span>
						            <spring:message code="my.orders.btn" />
						        </a>
						    </c:when>
						    
						    <c:otherwise>
						        <a href="/online-store.web/signin" class="btn btn-default cart-btn">
						            <span class="glyphicon glyphicon-list-alt"></span>
						            <spring:message code="my.orders.btn" />
						        </a>
						    </c:otherwise>
						</c:choose>
					
					
					<!-- Cart button with badge -->
						<c:choose>
						    <c:when test="${loggedInUser != null}">
						        <a href="/online-store.web/cart" class="btn btn-default cart-btn position-relative">
						            <span class="glyphicon glyphicon-shopping-cart"></span>
						            <spring:message code="cart.btn" />
						            <c:if test="${cartItemCount > 0}">
						                <span class="badge cart-badge">${cartItemCount}</span>
						            </c:if>
						        </a>
						    </c:when>
						    <c:otherwise>
						        <a href="/online-store.web/signin" class="btn btn-default cart-btn">
						            <span class="glyphicon glyphicon-shopping-cart"></span>
						            <spring:message code="cart.btn" />
						        </a>
						    </c:otherwise>
						</c:choose>
					
						<div class="login-sr">
							<div class="login-signup">
								<ul>
									<c:if test="${loggedInUser != null}">
										<li><a href="/online-store.web/my-profile"><spring:message code="welcome.lbl"/> ${loggedInUser.firstName}</a></li>
										<li><a class="custom-b" href="/online-store.web/perform_logout"><spring:message code="signout.btn"/></a></li>
									</c:if>
									<c:if test="${loggedInUser == null}">
										<li><a href="/online-store.web/signin"><spring:message code="signin.btn"/></a></li>
										<li><a class="custom-b" href="/online-store.web/signup"><spring:message code="signup.btn"/></a></li>
									</c:if>
								</ul>
							</div>
						</div>
						
						<!-- Locale / Language -->
						
						<div class="help-r hidden-xs">
							<div class="help-box">
								<ul>
									<li><a data-toggle="modal" data-target="#myModal" href="#">
											<span><spring:message code="change.lang.lbl"/> </span> 
											
									</a></li>

									<c:if test="${not empty loggedInUser}">
										<li><a href="/online-store.web/my-profile"><spring:message code="my.profile.lbl"/></a></li>
									</c:if>

								</ul>
							</div>
						</div>
						<div class="nav-b hidden-xs"></div>
					</div>
				</div>
			</div>
		</div>
		<!--/.container-fluid -->
	</nav>
</header>

<!-- Modal -->
<div class="modal fade lug" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title"><spring:message code="change"/></h4>
			</div>
			<div class="modal-body">
				<ul>
					<li><a href="?lang=en"><img src="/online-store.web/images/flag-up-1.png" alt="" />
							United States</a></li>
					<li><a href="?lang=fr"><img src="/online-store.web/images/flag-up-2.png" alt="" />
							France </a></li>
				</ul>
			</div>

		</div>
	</div>
</div>
<div id="sidebar" class="top-nav">
	<ul id="sidebar-nav" class="sidebar-nav">
		<li><a href="#"><spring:message code="help"/></a></li>
		<li><a href="#"><spring:message code="how.it.works"/></a></li>
		<li><a href="#"><spring:message code="chamb.for.business"/></a></li>
	</ul>
</div>