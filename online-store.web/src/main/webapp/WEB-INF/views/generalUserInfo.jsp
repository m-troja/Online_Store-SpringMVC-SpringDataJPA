<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="general.user.info" /></title>
</head>
<body>

<p><spring:message code="profile.first.name" />: ${user.firstName}</p>
<p><spring:message code="last" />: ${user.lastName}</p>
<p><spring:message code="profile.email" />: ${user.email}</p>

</body>
</html>