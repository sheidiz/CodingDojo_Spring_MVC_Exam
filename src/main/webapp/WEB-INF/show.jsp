<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${task.name}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container my-3">
		<h1>Task: ${task.name}</h1>
		<div class="pt-2 d-flex flex-column gap-1">
			<p class="fw-medium">Creator: ${task.creator.name}</p>
			<p class="fw-medium">Assignee: ${task.assignee.name}</p>
			<p class="fw-medium">Priority: <c:if test="${task.priority==1}">High </c:if> <c:if test="${task.priority==2}">Medium </c:if> <c:if
					test="${task.priority==3}">Low </c:if>
			</p>
			<div class="mt-2 d-flex gap-2">
				<c:if test="${task.creator.id==user.id}">
					<a href="/tasks/${task.id}/edit" class="btn btn-warning fw-semibold">Edit</a>
					<form action="/tasks/delete/${task.id}" method="post">
						<input type="hidden" name="_method" value="DELETE">
						<input type="submit" value="Delete" class="btn btn-danger fw-semibold">
					</form>
				</c:if>
			</div>
		</div>
		<div class="mt-2">
			<c:if test="${task.assignee.id==user.id}">
				<form action="/tasks/completed/${task.id}" method="post">
					<input type="hidden" name="_method" value="PUT">
					<input type="submit" value="Completed" class="btn btn-success fw-semibold">
				</form>
			</c:if>
			<a href="/tasks" class="mt-2 btn btn-secondary">Go back to Tasks</a>
		</div>
	</div>
</body>
</html>