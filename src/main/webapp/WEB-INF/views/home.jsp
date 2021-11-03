<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container text-center">
	<h1>Register new User</h1>

	<p>Server Time: ${serverTime}</p>
	<form:form method="post" action="/gtd-g03/user" modelAttribute="user">
		<form:label path="email">Email:</form:label>
		<form:input path="email" />
		<form:errors path="email" />
		<br />
		<form:label path="login">Login:</form:label>
		<form:input path="login" />
		<form:errors path="login" />
		<br />
		<form:label path="password">Password:</form:label>
		<form:password path="password" />
		<br />
		<input class="btn btn-primary" type="submit" value="Submit" />
	</form:form>
	</div>
	<a href="/gtd-g03/users">Back to users list</a>
</body>
</html>