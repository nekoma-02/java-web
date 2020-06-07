<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/style.css" />

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />

<title>Регистрация</title>
</head>
<body>
	<jsp:include page="../WEB-INF/jsp/part/header_nav.jsp"></jsp:include>

	<div>

		<h1 id="reg_text">Регистрация</h1>

		<c:if test="${not empty message}">
			<div class="alert alert-warning" role="alert" id="alert">
				<c:out value="${message}"></c:out>
			</div>
		</c:if>

		<form action="Controller" method="post" class="reg_form">

			<input type="hidden" name="command" value="registration">
			<input type="hidden" name="user_role" value="user">



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

			<div class="form-group">
				<label for="formGroupExampleInput">Имя</label> <input type="text"
					class="form-control" id="formGroupExampleInput" placeholder="Name"
					name="name">
			</div>

			<div class="form-group">
				<label for="formGroupExampleInput">Фамилия</label> <input
					type="text" class="form-control" id="formGroupExampleInput"
					placeholder="second name" name="secondname">
			</div>

			<div class="form-group">
				<label for="formGroupExampleInput">Отчество</label> <input
					type="text" class="form-control" id="formGroupExampleInput"
					placeholder="last name" name="lastname">
			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">Почтовый ящик</label> <input
					type="email" class="form-control" id="exampleInputEmail1"
					placeholder="Enter email" name="email">
			</div>

			<button type="submit" class="btn btn-primary">Зарегистрироваться</button>

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