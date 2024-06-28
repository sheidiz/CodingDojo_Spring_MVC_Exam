<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Manager</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<header class="container my-3 d-flex justify-content-between align-items-center gap-2">
		<h1>Welcome, ${user.name}</h1>
		<div>
			<a href="/tasks/priority_high_low" class="me-2 link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Priority
				High-Low</a>
			<a href="/tasks/priority_low_high" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Priority
				Low-High</a>
		</div>
		<a class="btn btn-danger" href="/logout">Log out</a>
	</header>
	<main class="container">
		<table class="border table table-hover">
			<thead>
				<tr class="table-secondary">
					<th>Task</th>
					<th>Creator</th>
					<th>Assignee</th>
					<th>Priority</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tasks}" var="task">
					<tr>
						<td>
							<a href="/tasks/${task.id}">${task.name}</a>
						</td>
						<td>${task.creator.name}</td>
						<td>${task.assignee.name}</td>
						<td>
							<c:if test="${task.priority==1}">
								High
							</c:if>
							<c:if test="${task.priority==2}">
								Medium
							</c:if>
							<c:if test="${task.priority==3}">
								Low
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="float-end">
			<a class="btn btn-secondary" href="/tasks/new">Create Task</a>
		</div>
	</main>
</body>
</html>