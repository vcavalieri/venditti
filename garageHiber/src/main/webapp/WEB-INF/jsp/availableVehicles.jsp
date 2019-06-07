<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="title.available" /></title>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>

	<c:if test="${vehicles ne null}">
		<table>
			<thead>
				<tr>
					<td><spring:message code="td.available.idvehicle" /></td>
					<td><spring:message code="td.available.licenseplate" /></td>
					<td><spring:message code="td.available.brand" /></td>
					<td></td>
				</tr>
			</thead>
 
			<tbody>
				<c:forEach items="${vehicles}" var="itemlist">
					<tr>
						<td>&nbsp;${itemlist.idvehicle}&nbsp;</td>
						<td>&nbsp;${itemlist.licenseplate}&nbsp;</td>
						<td>&nbsp;${itemlist.brand}&nbsp;</td>
						<td>
						<form id="rentform" action="insertPrenotation">
						<input type="hidden" name="rentstart" id="rentstart" value="${sessionScope.rentstartdate}">
						<input type="hidden" name="rentend" id="rentend" value="${sessionScope.rentenddate}">
						<input type="hidden" name="idvehicle" id="idvehicle" value="${itemlist.idvehicle}">
						<input type="submit" value="<spring:message code="button.available.submit" />">
						</form>
						</td>
						
						
				<!-- 		<td>&nbsp;<a
							href="${pageContext.request.contextPath}/insertPrenotation?idvehicle=${itemlist.idvehicle}&licenseplate=${itemlist.licenseplate}">RENT&nbsp;</a></td>
				 -->	</tr>
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