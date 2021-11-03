<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tasks List</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container text-center"><h1>Tasks List</h1></div>
	<div>
		<table class="table table-bordered">
		<thead class="thead-dark">
			<tr>
				<th>#</th>
				<th>Title</th>
				<th>Comments</th>
				<th>Created</th>
				<th>Planned</th>
				<th>Finished</th>
				<th>Category</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tasks}" var="task">
				<tr>
					<td>${task.id}</td>
					<td>${task.title}</td>
					<td>${task.comments}</td>
					<td>${task.created}</td>
					<td>${task.planned}</td>
					<td>${task.finished}</td>
					<td>${task.category.name }</td>
					<td><a href="edit/${task.id}" class="btn btn-warning">Edit</a></td>
					<td><a href="delete/${task.id}" class="btn btn-danger" onclick="return confirm('Are you sure you want to continue?')">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<br />
	<a href="/gtd-g03/task/new">Add new Task</a>
</body>
</html>