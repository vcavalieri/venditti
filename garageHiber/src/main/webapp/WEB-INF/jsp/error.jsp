<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="title.error" /></title>
</head>
<body>

	<h1>${errormessage}</h1>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/login"><spring:message code="link.login" /></a>

</body>
</html>