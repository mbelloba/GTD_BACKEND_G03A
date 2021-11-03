<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Task</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<div class="container text-center">
		<div><h1>Register new Task</h1></div>
	
		<div>
			<form:form method="post" action="/gtd-g03/task/create" 
			modelAttribute="task">
			<form:label path="title">Title:</form:label>
			<form:input path="Title" />
			<br />
			<form:label path="comments">Comments:</form:label>
			<form:input path="comments" />
			<br />
			<form:label path="planned">Planned Date:</form:label>
			<form:input path="planned" />
			<br />
			<input type="hidden" name="created" value="${createdDate }"/>
			<input type="hidden" name="finished" value="false" />
			<input type="submit" value="Submit" class="btn btn-primary"/>
		</form:form>
		</div>
	</div>
	
</body>
</html>