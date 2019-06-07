<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<head>
<title><spring:message code="title.search" /></title>

</head>

<body>

	<h2><spring:message code="value.h2.search" /></h2>
	<br>

	<form id="searchform" method="GET" action="showVehicle">
		<label for="idvehicle"> <spring:message code="label.search.idvehicle" /> </label> <input type="text"
			name="idvehicle" id="idvehicle"><br> <br> <label
			for="licenseplate"><spring:message code="label.search.licenseplate" /></label> <input type="text"
			name="licenseplate" id="licenseplate"><br> <br> <label
			for="brand"><spring:message code="label.search.brand" /> </label> <input type="text" name="brand"
			id="brand"><br> <br> <input type="submit"
			value="<spring:message code="button.search.vehicles" />" /> &emsp;
	</form>

	<br>
	<br>

	<form id="searchpren" method="GET" action="available">
		<label for="rentend"> <spring:message code="label.search.rentstart" /></label> <br>
		<input type="text" name="rentstart" id="rentstart"><br> <br>
		<label for="rentstart"> <spring:message code="label.search.rentend" /> </label> <br>
		<input type ="text" name="rentend" id ="rentend"><br><br>
		<input type="submit" value="<spring:message code="button.search.pren" />" /> &emsp;
	</form>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/myRentedVehicles?p=1"><spring:message code="link.search.mypren" /></a> &emsp;
	<a href="${pageContext.request.contextPath}/logout"><spring:message code="link.logout" /></a>

	<script>
		$(function() {
			$("#rentend").datepicker();
		});
		
		$(function() {
			$("#rentstart").datepicker();
		});
	</script>
</body>
</html>