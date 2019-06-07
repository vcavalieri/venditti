<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html SYSTEM "about:legacy-compat">

<html>
<link rel="stylesheet"
	href=C:\Users\salvatore.venditti\eclipse-workspace\Garage\WebContent\WEB-INF\style.css />

<head> 
<title><spring:message code="title.login" /></title>
</head>

<body>

	<h3>Login</h3>
	<form id="login" method="GET" action="login">
		<label for="username"><spring:message code="input.login.username" /> </label> <input type="text"
			id="username" name="username"><br> <br> <label
			for="password"><spring:message code="input.login.password" /> </label> <input type="password"
			id="password" name="password"><br> <br>
		<button class="btn" type="submit" value="Login"><spring:message code="button.login.submit" /></button>
		&emsp; <a href="${pageContext.request.contextPath}/register"><spring:message code="link.signup" /></a>
	</form>

</body>
</html>
