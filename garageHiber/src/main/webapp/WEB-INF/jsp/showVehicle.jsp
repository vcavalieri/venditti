<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html SYSTEM "about:legacy-compat">

<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<title>Search Results</title>
</head>

<body>

	<c:if test="${vehicles ne null}">
		<table>
			<thead>
				<tr>
					<td><b>&nbsp;Id Vehicle&nbsp;</b></td>
					<td><b>&nbsp;LicensePlate&nbsp;</b></td>
					<td><b>&nbsp;Brand&nbsp;</b></td>
					<td><b>&nbsp;Description&nbsp;</b></td>
					<c:if test="${sessionScope.username == 'admin'}">
						<td><a
							href="${pageContext.request.contextPath}/insertVehicle?alert=no">INSERT
								NEW VEHICLE</a></td>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${vehicles}" var="itemlist">
					<tr>
						<td>&nbsp;${itemlist.idvehicle}&nbsp;</td>
						<td>&nbsp;${itemlist.licenseplate}&nbsp;</td>
						<td>&nbsp;${itemlist.brand}&nbsp;</td>
						<td>&nbsp;${itemlist.vehicleinfo.description}&nbsp;</td>
						<c:if test="${sessionScope.username == 'admin'}">
							<td><a
								href="${pageContext.request.contextPath}/deleteVehicle?licenseplate=${itemlist.licenseplate}">DELETE
									VEHICLE</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<br>
	<br>
	<a
		href="${pageContext.request.contextPath}/login?username=${sessionScope.username}&password=${sessionScope.password}&alert=no">SEARCH
		AGAIN</a> &emsp;
	<a href="${pageContext.request.contextPath}/logout">LOGOUT</a>

</body>
</html>