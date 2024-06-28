<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Task</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<main class="container my-3">
		<form:form action="/tasks/update" method="post" modelAttribute="task">
			<h1>Edit ${task.name}</h1>
			<div>
				<form:label path="name">Task:</form:label>
				<form:input path="name" class="form-control" />
				<form:errors path="name" class="text-danger" />
			</div>
			<div>
				<form:label path="assignee">Assignee:</form:label>
				<form:select path="assignee" id="assignee" class="form-select">
					 <form:options items="${users}" itemValue="id" itemLabel="name" />
				</form:select>
			</div>
			<div>
				<form:label path="priority">Priority:</form:label>
				<form:select path="priority" class="form-select">
					<form:option value="1">High</form:option>
					<form:option value="2">Medium</form:option>
					<form:option value="3">Low</form:option>
				</form:select>
			</div>
			<form:hidden path="id" value="${task.id}" />
			<form:hidden path="creator" value="${userInSession.id}" />
			<form:hidden path="status" value="${task.status}" />
			<input type="hidden" value="put" name="_method">
			<input type="submit" class="mt-2 me-1 btn btn-primary" value="Edit">
			<a href="/tasks" class="mt-2 btn btn-secondary">Cancel</a>
		</form:form>
	</main>
</body>
</html>