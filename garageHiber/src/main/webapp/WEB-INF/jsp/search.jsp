<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<head>
<title>Search!</title>

</head>

<body>

	<h2>Search:</h2>
	<br>

	<form id="searchform" method="GET" action="showVehicle">
		<label for="idvehicle"> ID Vehicle: </label> <input type="text"
			name="idvehicle" id="idvehicle"><br> <br> <label
			for="licenseplate">License Plate: </label> <input type="text"
			name="licenseplate" id="licenseplate"><br> <br> <label
			for="brand"> Brand: </label> <input type="text" name="brand"
			id="brand"><br> <br> <input type="submit"
			value="Search for Vehicles" /> &emsp;
	</form>

	<br>
	<br>

	<form id="searchpren" method="GET" action="available">
		<label for="rentend"> Your Rent Will Starts On: </label> <br>
		<input type="text" name="rentend" id="rentend"><br> <br>
		<input type="submit" value="Search for Prenotations" /> &emsp;
	</form>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/myRentedVehicles?p=1">MY
		PRENOTATIONS</a> &emsp;
	<a href="${pageContext.request.contextPath}/logout">LOGOUT</a>

	<script>
		$(function() {
			$("#rentend").datepicker();
		});
	</script>
</body>
</html>