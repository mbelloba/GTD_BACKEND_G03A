<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category Form</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container text-center">
		<h1>Register new Category</h1>
	
		<form:form method="post" action="/gtd-g03/category/create" 
			modelAttribute="category">
			<form:label path="name">Name:</form:label>
			<form:input path="name" />
			<form:errors path="name"/>
			<br />
			<input type="submit" value="Submit" class="btn btn-primary" />
		</form:form>
	</div>
	<a href="/gtd-g03/category/list">Category List</a>
</body>
</html>