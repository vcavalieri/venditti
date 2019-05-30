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

	<c:choose>
		<c:when test="${message == null}">
			<h3>Vehicles:</h3>
		</c:when>

		<c:when test="${message == 'There are no Results!'}">
			<h3>${message}</h3>
		</c:when>

		<c:when test="${message == 'You have no Rented Vehicles!'}">
			<h3>${message}</h3>
		</c:when>

		<c:otherwise>

		</c:otherwise>

	</c:choose>



	<c:if test="${vehicles ne null}">

		<table>

			<c:choose>
				<c:when test="${sessionScope.username == 'admin'}">
					<c:choose>
						<c:when test="${param.p == '1'}">
							<thead>
								<tr>
									<td><b>&nbsp;Id Vehicle&nbsp;</b></td>
									<td><b>&nbsp;LicensePlate&nbsp;</b></td>
									<td><b>&nbsp;Brand&nbsp;</b></td>
									<td><b>&nbsp;Description&nbsp;</b></td>
									<td><b>&nbsp;RentStart&nbsp;</b></td>
									<td><b>&nbsp;RentEnd&nbsp;</b></td>
									<td><a
										href="${pageContext.request.contextPath}/insertPrenotation">INSERT
											NEW PRENOTATION</a></td>
									<c:if test="${sessionScope.rentcheck == true}">
										<td><b>&nbsp;RENTABLE&nbsp;</b></td>
									</c:if>
								</tr>
							</thead>
						</c:when>
						<c:otherwise>
							<thead>
								<tr>
									<td><b>&nbsp;Id Vehicle&nbsp;</b></td>
									<td><b>&nbsp;LicensePlate&nbsp;</b></td>
									<td><b>&nbsp;Brand&nbsp;</b></td>
									<td><b>&nbsp;Description&nbsp;</b></td>
									<td><a
										href="${pageContext.request.contextPath}/insertVehicle?alert=no">INSERT
											NEW VEHICLE</a></td>
									<c:if test="${sessionScope.rentcheck == true}">
										<td><b>&nbsp;RENTABLE&nbsp;</b></td>
									</c:if>

								</tr>
							</thead>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${sessionScope.username == null}">
					<c:set var="errormessage"
						value="You can't visit this page.. please login!" scope="session" />
					<c:redirect url="${request.contextPath}/error" />
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${param.p == '1'}">
							<thead>
								<tr>
									<td><b>&nbsp;Id Vehicle&nbsp;</b></td>
									<td><b>&nbsp;LicensePlate&nbsp;</b></td>
									<td><b>&nbsp;Brand&nbsp;</b></td>
									<td><b>&nbsp;Description&nbsp;</b></td>
									<td><b>&nbsp;RentStart&nbsp;</b></td>
									<td><b>&nbsp;RentEnd&nbsp;</b></td>
									<td><b>&nbsp;DELETE RENT&nbsp;</b></td>
									<c:if test="${sessionScope.rentcheck == true}">
										<td><b>&nbsp;RENTABLE&nbsp;</b></td>
									</c:if>
								</tr>
							</thead>
						</c:when>
						<c:otherwise>
							<thead>
								<tr>
									<td><b>&nbsp;Id Vehicle&nbsp;</b></td>
									<td><b>&nbsp;LicensePlate&nbsp;</b></td>
									<td><b>&nbsp;Brand&nbsp;</b></td>
									<td><b>&nbsp;Description&nbsp;</b></td>
									<c:if test="${sessionScope.rentcheck == true}">
										<td><b>&nbsp;RENTABLE&nbsp;</b></td>
									</c:if>

								</tr>
							</thead>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>


			<tbody>

				<c:choose>
					<c:when test="${sessionScope.username == 'admin'}">
						<c:choose>
							<c:when test="${param.p == '1'}">
								<c:forEach items="${vehicles}" var="itemlist">
									<tr>
										<td>&nbsp;${itemlist.vehicle.idvehicle}&nbsp;</td>
										<td>&nbsp;${itemlist.vehicle.licenseplate}&nbsp;</td>
										<td>&nbsp;${itemlist.vehicle.brand}&nbsp;</td>
										<td>&nbsp;${itemlist.vehicle.vehicleinfo.description}&nbsp;</td>
										<td>&nbsp;${itemlist.rentStart}&nbsp;</td>
										<td>&nbsp;${itemlist.rentEnd}&nbsp;</td>
										<td><a
											href="${pageContext.request.contextPath}/deletePrenotation?idprenotation=${itemlist.idPrenotation}">DELETE
												PRENOTATION</a></td>
										<c:if test="${sessionScope.rentcheck == true}">
											<td><a
												href="${pageContext.request.contextPath}/insertPrenotation?idvehicle=${itemlist.licenseplate}&licenseplate=${itemlist.licenseplate}">RENT</a></td>
										</c:if>

									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach items="${vehicles}" var="itemlist">
									<tr>
										<td>&nbsp;${itemlist.idvehicle}&nbsp;</td>
										<td>&nbsp;${itemlist.licenseplate}&nbsp;</td>
										<td>&nbsp;${itemlist.brand}&nbsp;</td>
										<td>&nbsp;${itemlist.vehicleinfo.description}&nbsp;</td>
										<td><a
											href="${pageContext.request.contextPath}/deleteVehicle?licenseplate=${itemlist.licenseplate}">DELETE
												VEHICLE</a></td>
										<c:if test="${sessionScope.rentcheck == true}">
											<td><a
												href="${pageContext.request.contextPath}/insertPrenotation?idvehicle=${itemlist.licenseplate}&licenseplate=${itemlist.licenseplate}">RENT</a></td>
										</c:if>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${sessionScope.username == null}">
						<c:set var="errormessage"
							value="You can't visit this page.. please login!" scope="session" />
						<c:redirect url="${request.contextPath}/error" />
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${param.p == '1'}">
								<c:forEach items="${vehicles}" var="itemlist">
									<tr>
										<td>&nbsp;${itemlist.vehicle.idvehicle}&nbsp;</td>
										<td>&nbsp;${itemlist.vehicle.licenseplate}&nbsp;</td>
										<td>&nbsp;${itemlist.vehicle.brand}&nbsp;</td>
										<td>&nbsp;${itemlist.vehicle.vehicleinfo.description}&nbsp;</td>
										<td>&nbsp;${itemlist.rentStart}&nbsp;</td>
										<td>&nbsp;${itemlist.rentEnd}&nbsp;</td>
										<td><a
											href="${pageContext.request.contextPath}/deletePrenotation?idprenotation=${itemlist.idPrenotation}">DELETE
												PRENOTATION</a></td>
										<c:if test="${sessionScope.rentcheck == true}">
											<td><a
												href="${pageContext.request.contextPath}/insertPrenotation?idvehicle=${itemlist.licenseplate}&licenseplate=${itemlist.licenseplate}">RENT</a></td>
										</c:if>

									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach items="${vehicles}" var="itemlist">
									<tr>
										<td>&nbsp;${itemlist.idvehicle}&nbsp;</td>
										<td>&nbsp;${itemlist.licenseplate}&nbsp;</td>
										<td>&nbsp;${itemlist.brand}&nbsp;</td>
										<td>&nbsp;${itemlist.vehicleinfo.description}&nbsp;</td>
										<c:if test="${sessionScope.rentcheck == true}">
											<td><a
												href="${pageContext.request.contextPath}/insertPrenotation?idvehicle=${itemlist.licenseplate}&licenseplate=${itemlist.licenseplate}">RENT</a></td>
										</c:if>
									</tr>
								</c:forEach>
							</c:otherwise>  
						</c:choose>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<br>
	</c:if>
	<c:if test="${sessionScope.rentcheck == true }">
		<c:set var="rentcheck" value="false" scope="session" />
	</c:if>
	<a
		href="${pageContext.request.contextPath}/login?username=${sessionScope.username}&password=${sessionScope.password}&alert=no">SEARCH
		AGAIN</a> &emsp;
	<a href="${pageContext.request.contextPath}/logout">LOGOUT</a>

	<c:if test="${not empty message}">
		<script>
			var message = "${message}";
			alert(message);
		</script>
	</c:if>

</body>
</html>
