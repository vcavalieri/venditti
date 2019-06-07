<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
<head>
<title><spring:message code="title.register" /></title>
</head>

<body>

	<h2><spring:message code="value.h2.register" /></h2>


	<form method="GET" action="register">
		<label for="Username"> <spring:message code="label.register.username" /> </label> <input type="text"
			id="Username" name="username" /> <br>
		<br> <label for="Firstname"><spring:message code="label.register.firstname" /> </label> <input
			type="text" id="Firstname" name="firstname" /> <br>
		<br> <label for="Lastname"> <spring:message code="label.register.lastname" /> </label> <input
			type="text" id="Lastname" name="lastname" /> <br>
		<br> <label for="Password"> <spring:message code="label.register.password" /> </label> <input
			type="password" id="Password" name="password" /> <br>
		<br> <input type="submit" value="<spring:message code="button.register.submit" />" name="submit" /> &emsp; <a
			href="${pageContext.request.contextPath}/login"><spring:message code="link.login" /></a>
	</form>

</body>
</html>