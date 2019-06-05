<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert New Prenotation</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body>

	<form id="formpren" method="GET" action="insertPrenotation">
		Date Format : yyyy-MM-dd<br> <br> <label for="datepicker1">
			Date of Rent Start : <b>${sessionScope.rentstartdate}</b>
		</label> <input id="datepicker1" type="hidden"
			value="${sessionScope.rentstartdate}" name="rentstart"><br>
		<br> <label for="datepicker2">Date of Rent End:</label> <input
			type="text" id="datepicker2" name="rentend"><br> <br>

		Selected Vehicle: ${param.licenseplate} <input type="hidden"
			value="${param.idvehicle}" name="idvehicle"> <br> <br>
		<input id="btnSubmit" type="submit" value="Rent"> &emsp; <a
			href="${pageContext.request.contextPath}/login?username=${sessionScope.username}&password=${sessionScope.password}&alert=no">SEARCH
			AGAIN</a> &emsp; <a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
	</form>

	<script>
		$(function() {
			$("#datepicker2").datepicker();
		});
	</script>

</body>
</html>