<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="profile-pro-left">
	<div class="left-profile-box-m">
		<div class="pro-img">
			<img src="images/150x150.png" alt="#" />
		</div>
		<div class="pof-text">
			<h3>${loggedInUser.firstName} ${loggedInUser.lastName}</h3>
		</div>
		<a href="edit-profile"><spring:message code="profile.edit" /></a>
	</div>
</div>