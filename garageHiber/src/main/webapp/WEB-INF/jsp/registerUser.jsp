<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
<head>
<title>Register a new User!</title>
</head>

<body>

	<h2>Insert your New User's Datas</h2>


	<form method="GET" action="register">
		<label for="Username"> Username: </label> <input type="text"
			id="Username" name="username" /> <br>
		<br> <label for="Firstname"> First Name: </label> <input
			type="text" id="Firstname" name="firstname" /> <br>
		<br> <label for="Lastname"> Last Name: </label> <input
			type="text" id="Lastname" name="lastname" /> <br>
		<br> <label for="Password"> Password: </label> <input
			type="password" id="Password" name="password" /> <br>
		<br> <input type="submit" value="Send" name="submit" /> &emsp; <a
			href="${pageContext.request.contextPath}/login"> LOGIN </a>
	</form>

</body>
</html>