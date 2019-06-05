<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Available Rents</title>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>

<c:if test="${prenotations ne null}">
	<table>
		<thead>
			<tr>
			<td>IdVehicle</td>
			<td>IdPrenotation</td>
			<td></td>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${prenotations}" var="itemlist">
			<tr>
			<td>&nbsp;${itemlist.vehicle.idvehicle}&nbsp;</td>
			<td>&nbsp;${itemlist.idprenotation}&nbsp;</td>
			<td>&nbsp;<a href="${pageContext.request.contextPath}/insertPrenotation?idvehicle=${itemlist.vehicle.idvehicle}&licenseplate=${itemlist.vehicle.licenseplate}">RENT&nbsp;</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</c:if>
	<br><br>
	<a href="${pageContext.request.contextPath}/login?username=${sessionScope.username}&password=${sessionScope.password}&alert=no">SEARCH
		AGAIN</a> &emsp;
	<a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
	
</body>
</html>