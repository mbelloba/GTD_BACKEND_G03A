<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Users List</title>
</head>
<body>
	<div class="container text-center">
		<h1>Users List</h1>
	</div>
	<div>
		<table class="table table-bordered">
			<thead class="thead-dark">
				<tr>
					<th>#</th>
					<th>Email</th>
					<th>Login</th>
					<th>Password</th>
					<th>Status</th>
					<th>Block</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.email}</td>
						<td>${user.login}</td>
						<td>${user.password}</td>
						<td>${user.status}</td>

						<td><form:form method="POST"
								action="/gtd-g03/block/${user.id}">
								<%
										String blocked = "block";
										String status = "${user.status}";

										if (status == "DISABLED") {
											blocked = "unblock";
										}
										%>
								<input  class="btn btn-warning" type="submit" value="block" />
							</form:form></td>
						<td><form:form method="POST"
								action="/gtd-g03/delete/${user.id}">
								<input  class="btn btn-danger" type="submit" onclick="return confirm('Are you sure you want to continue?')" value="Delete" />
							</form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <a href="/gtd-g03/">Add new user</a>
	</div>
</body>
</html>