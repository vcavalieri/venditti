<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html SYSTEM "about:legacy-compat">

<html>
<link rel="stylesheet"
	href=C:\Users\salvatore.venditti\eclipse-workspace\Garage\WebContent\WEB-INF\style.css />

<head>
<title>Home</title>
</head>

<body>

	<h3>Login</h3>
	<form id="login" method="GET" action="login">
		<label for="username">Username: </label> <input type="text"
			id="username" name="username"><br> <br> <label
			for="password">Password: </label> <input type="password"
			id="password" name="password"><br> <br>
		<button class="btn" type="submit" value="Login">Login</button>
		&emsp; <a href="${pageContext.request.contextPath}/register">SIGN
			UP</a>
	</form>

</body>
</html>
