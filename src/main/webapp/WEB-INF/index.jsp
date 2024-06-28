<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Manager</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<header class="container my-3 mx-auto text-center">
		<h1>Task Manager</h1>
	</header>
	<main class="container">
		<div class="row">
			<div class="col-6">
				<h2>Register</h2>
				<form:form action="/register" method="POST" modelAttribute="newUser">
					<div>
						<form:label path="name">Name:</form:label>
						<form:input path="name" class="form-control" />
						<form:errors path="name" class="text-danger" />
					</div>
					<div>
						<form:label path="email">Email:</form:label>
						<form:input path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div>
						<form:label path="password">Password:</form:label>
						<form:password path="password" class="form-control" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div>
						<form:label path="confirm">Password Conf:</form:label>
						<form:password path="confirm" class="form-control" />
						<form:errors path="confirm" class="text-danger" />
					</div>
					<input type="submit" class="btn btn-secondary fw-semibold" value="Register">
				</form:form>
			</div>
			<div class="col-6">
				<h2>Login</h2>
				<form action="/login" method="POST">
					<div>
						<label>Email:</label>
						<input type="email" class="form-control" name="email">
					</div>
					<div>
						<label>Password:</label>
						<input type="password" class="form-control" name="password">
					</div>
					<input type="submit" class="btn btn-secondary mt-3 fw-semibold" value="Login">
				</form>
				<p class="text-danger">${errorLogin}</p>
			</div>
		</div>
	</main>
</body>
</html>