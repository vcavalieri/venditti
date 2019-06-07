<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="title.mypren" /></title>
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
					<td><spring:message code="td.mypren.idvehicle" /></td>
					<td><spring:message code="td.mypren.licenseplate" /></td>
					<td><spring:message code="td.mypren.brand" /></td>
					<td><spring:message code="td.mypren.idpren" /></td>
					<td><spring:message code="td.mypren.rentstart" /></td>
					<td><spring:message code="td.mypren.rentend" /></td>
					<td></td>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${prenotations}" var="itemlist">
					<tr>
						<td>&nbsp;${itemlist.vehicle.idvehicle}&nbsp;</td>
						<td>&nbsp;${itemlist.vehicle.licenseplate}&nbsp;</td>
						<td>&nbsp;${itemlist.vehicle.brand}&nbsp;</td>
						<td>&nbsp;${itemlist.idprenotation}&nbsp;</td>
						<td>&nbsp;${itemlist.rentstart}&nbsp;</td>
						<td>&nbsp;${itemlist.rentend}&nbsp;</td>
						<td><a
							href="${pageContext.request.contextPath}/deletePrenotation?idprenotation=${itemlist.idprenotation}&rentstart=${itemlist.rentstart}"><spring:message code="link.mypren.delpren" /></a></td>
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