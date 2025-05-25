<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="tab-pane fade in active" id="tab1default">
	<div class="product-box-main row">
		<div class="col-md-12 col-sm-18">
			<table class="my-profile-table">
				<tr>
					<td><b><spring:message code="profile.first.name" />:</b></td>
					<td>${loggedInUser.firstName}</td>
				</tr>
				<tr>
					<td><b><spring:message code="profile.last.name" />:</b></td>
					<td>${loggedInUser.lastName}</td>
				</tr>
				<tr>
					<td><b><spring:message code="profile.email" />:</b></td>
					<td>${loggedInUser.email}</td>
				</tr>
				<tr>
					<td><b><spring:message code="profile.money" />:</b></td>
					<td>${loggedInUser.money}</td>
				</tr>
				<tr>
					<td><b><spring:message code="profile.partner.code" />:</b></td>
					<td>${loggedInUser.partnerCode}</td>
				</tr>
				<tr>
					<td><b><spring:message code="profile.partner.link" />:</b></td>
					<td><a href="${partnerLink}">${partnerLink}</a></td>
				</tr>
				
				
			</table>
			
		</div>

	</div>

</div>