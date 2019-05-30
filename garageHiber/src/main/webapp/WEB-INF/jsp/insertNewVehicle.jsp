<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert New Vehicle!</title>
</head>

<body>

	<h3>Insert New Vehicle's Data</h3>
	<br>
	<form id="insert" method="GET" action="insertVehicle">
		<label for="licenseplate">License Plate: </label> 
		<input type="text" name="licenseplate" id="licenseplate"><br> <br> 
		<label for="brand">Brand: </label> 
		<input type="text" name="brand" id="brand"><br> <br> 
		<label for="category">Select a Category: </label>
		 
		<select id="category" form="insert" name="type">
			<c:forEach items="${list}" var="type">
				<option value="${type.description}+${type.vehicletype}">${type.description}</option>
			</c:forEach>
		</select><br> <br>  
		
		<input type="submit" value="Insert">&emsp;
		<a href="${pageContext.request.contextPath}/login?username=${sessionScope.username}&password=${sessionScope.password}&alert=no">SEARCH AGAIN</a> &emsp; 
		<a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
	</form>

	<c:if test="${not empty message}">
		<c:if test = "${param.alert ne 'no'}">
			<script>
				var message = "${message}";
				alert(message);
			</script>
		</c:if>
	</c:if>

</body>
</html>