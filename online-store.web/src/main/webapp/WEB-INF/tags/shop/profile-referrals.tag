<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="tab-pane fade" id="tab2default">
	<div class="about-box">
		<h2><spring:message code="profile.my.referrals" /></h2>

		<c:if test="${not empty referrals}">
			<table class="order-table">
				<tr>
					<th><spring:message code="profile.first.name" /></th>
					<th><spring:message code="profile.last.name" /></th>
					<th><spring:message code="profile.email" /></th>
				</tr>
				<c:forEach items="${referrals}" var="referral">
					<tr>
						<td>${referral.firstName}</td>
						<td>${referral.lastName}</td>
						<td>${referral.email}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		
		<c:if test="${empty referrals}">
			<spring:message code="profile.no.referrals" />
		</c:if>

	</div>
</div>