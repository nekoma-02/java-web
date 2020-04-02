<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/style.css" />

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />
<fmt:message bundle="${loc}" key="local.namepage.mainpage"
	var="mainpage" />
<fmt:message bundle="${loc}" key="local.namepage.infopage"
	var="infopage" />
<fmt:message bundle="${loc}" key="local.namepage.contactpage"
	var="contactpage" />
<fmt:message bundle="${loc}" key="local.namepage.contactpage"
	var="contactpage" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<title>Авторизация</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
			<a class="navbar-brand" href="#">BRAND</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarCollapse" aria-controls="navbarCollapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link" href="index.jsp">${mainpage}
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/Controller?command=show_specialties">${infopage}</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">${contactpage}</a>
					</li>
				</ul>
				<c:if test="${not empty sessionScope.user_id}">
					<form id="navlogin" class="form-inline mt-2 mt-md-0"
						action="Controller" method="post">
						<input type="hidden" name="command" value="sign_out" /> <span
							class="navbar-text">${sessionScope.user_login} </span>
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Выйти</button>
					</form>
				</c:if>
				<form class="form-inline mt-2 mt-md-0" action="Controller"
					method="post">
					<input type="hidden" name="local" value="ru" /> <input
						type="hidden" name="command" value="change_local" />
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">${ru_button}</button>
				</form>
				<form class="form-inline mt-2 mt-md-0" action="Controller"
					method="post">
					<input type="hidden" name="local" value="en" /> <input
						type="hidden" name="command" value="change_local" />
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">${en_button}</button>
				</form>
			</div>
		</nav>
	</div>

	<div>
		<h1 id="Auth_text">Авторизация</h1>

		<c:if test="${not empty message }">
			<div class="alert alert-warning" role="alert" id="alert">
				<c:out value="${message}"></c:out>
			</div>
		</c:if>


		<form action="Controller" method="post" class="auth_form">
			<input type="hidden" name="command" value="sign_in">

			<div class="form-group">
				<label for="formGroupExampleInput">Логин</label> <input type="text"
					class="form-control" id="formGroupExampleInput" placeholder="Login"
					name="login">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Пароль</label> <input
					type="password" class="form-control" id="exampleInputPassword1"
					placeholder="Password" name="password">
			</div>
			<button type="submit" class="btn btn-primary">Войти</button>

		</form>
	</div>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
</body>
</html>