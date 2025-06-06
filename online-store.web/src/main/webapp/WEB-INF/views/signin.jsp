<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="OnlineShopResourceBundle" var="rb"/>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Sign In</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<shop:css-imports-main/>
	<shop:css-imports-signin/>
	
</head>
<body>

	<shop:header/>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
			
				<c:if test="${UNSUCCESSFUL_LOGIN_COUNT == null || UNSUCCESSFUL_LOGIN_COUNT < 3}">
					<form class="login100-form validate-form" action="perform_login" method="POST">
						<span class="login100-form-title p-b-26">
							<fmt:message key="signin.welcome" bundle="${rb}"/>
						</span>
	
						<div class="wrap-input100 validate-input" data-validate = "Valid email is: a@b.c">
							<input class="input100 has-val" type="text" name="email">
							<span class="focus-input100" data-placeholder="Email"></span>
						</div>
	
						<div class="wrap-input100 validate-input" data-validate="Enter password">
							<span class="btn-show-pass">
								<i class="zmdi zmdi-eye"></i>
							</span>
							<input class="input100 has-val" type="password" name="password">
							<span class="focus-input100" data-placeholder="Password"></span>
						</div>
	
								<!--  Remember Checkbox -->
								
						<input  id="remember" type="checkbox" name="remember">
						  <label for="remember" >Remember Me</label>	
	
	
						<div class="container-login100-form-btn">
							<div class="wrap-login100-form-btn">
								<div class="login100-form-bgbtn"></div>
								<button class="login100-form-btn">
							<fmt:message key="signin.button" bundle="${rb}"/>
								</button>
							</div>
							<c:if test="${param.error == 'true' }">
								<div>
									Error during logging!
								</div>
							</c:if>
							
						</div>
	
						<div class="text-center p-t-115">
							<span class="txt1">
								Don't have an account?
							</span>
	
							<a class="txt2" href="signup">
								Sign Up
							</a>
						</div>
					</form>
				
				</c:if>
				
				<c:if test="${UNSUCCESSFUL_LOGIN_COUNT >= 3}">
					<div>
						There were 3 unsuccessful attempts to login into the account. Please, try to sign in later
					</div>
				</c:if>
				
				
			</div>
		</div>
	</div>
	
	<shop:footer/>
	<shop:js-imports-signin/>

</body>
</html>