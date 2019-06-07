<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<!DOCTYPE html SYSTEM "about:legacy-compat">

<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<title><spring:message code="title.show" /></title>
</head>

<body>

	<c:if test="${vehicles ne null}">
		<table>
			<thead>
				<tr>
					<td><b>&nbsp;<spring:message code="td.show.idvehicle" />&nbsp;</b></td>
					<td><b>&nbsp;<spring:message code="td.show.licenseplate" />&nbsp;</b></td>
					<td><b>&nbsp;<spring:message code="td.show.brand" />&nbsp;</b></td>
					<td><b>&nbsp;<spring:message code="td.show.description" />&nbsp;</b></td>
					<c:if test="${sessionScope.username == 'admin'}">
						<td><a
							href="${pageContext.request.contextPath}/insertVehicle?alert=no"><spring:message code="link.show.insert" /></a></td>
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
								href="${pageContext.request.contextPath}/deleteVehicle?idvehicle=${itemlist.idvehicle}&licenseplate=${itemlist.licenseplate}"><spring:message code="link.show.delete" /></a></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<br>
	<br>
	<a
		href="${pageContext.request.contextPath}/login?username=${sessionScope.username}&password=${sessionScope.password}&alert=no"><spring:message code="link.search" /></a> &emsp;
	<a href="${pageContext.request.contextPath}/logout"><spring:message code="link.logout" /></a>

</body>
</html>