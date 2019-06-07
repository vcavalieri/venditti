<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="title.insvehicle" /></title>
</head>

<body>

	<h3><spring:message code="value.h3.insvehicle" /></h3>
	<br>
	<form id="insert" method="GET" action="insertVehicle">
		<label for="licenseplate"><spring:message code="label.insvehicle.licenseplate" /> </label> <input type="text"
			name="licenseplate" id="licenseplate"><br> <br> <label
			for="brand"><spring:message code="label.insvehicle.brand" /> </label> <input type="text" name="brand"
			id="brand"><br> <br> <label for="category"><spring:message
				code="label.insvehicle.category" /> </label> <select id="category" form="insert" name="type">
			<c:forEach items="${list}" var="type">
				<option value="${type.description}+${type.vehicletype}">${type.description}</option>
			</c:forEach>
		</select><br> <br> <input type="submit" value="Insert">&emsp;
		<a
			href="${pageContext.request.contextPath}/login?username=${sessionScope.username}&password=${sessionScope.password}&alert=no"><spring:message code="link.search" /></a> &emsp; 
			<a href="${pageContext.request.contextPath}/logout"><spring:message code="link.logout" /></a>
	</form>

</body>
</html>