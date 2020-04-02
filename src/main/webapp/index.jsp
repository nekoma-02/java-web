<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/style.css" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />
<fmt:message bundle="${loc}" key="local.message.mainpage" var="mainpage" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />

<title>Index page</title>
</head>
<body>
	<div>
		<ul class="nav justify-content-center">
			<li class="nav-item"><a class="nav-link active" href="#">${mainpage}</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">Информация</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">Контакты</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
			</li>
			<li class="nav-item">
				<form action="Controller" method="post" id="form_local">
					<input type="hidden" name="local" value="ru" /> <input
						type="hidden" name="command" value="change_local" /> <input
						type="submit" value="${ru_button}" /><br />
				</form>
			</li>
			<li class="nav-item">
				<form action="Controller" method="post" id="form_local">
					<input type="hidden" name="local" value="en" /> <input
						type="hidden" name="command" value="change_local" /> <input
						type="submit" value="${en_button}" /><br />
				</form>
			</li>
		</ul>
	</div>

	<div class="content">
		<a href="login.jsp" class="btn btn-primary btn-lg active"
			role="button" aria-pressed="true">Авторизация</a> <a
			href="registration.jsp" class="btn btn-primary btn-lg active"
			role="button" aria-pressed="true">Регистрация</a>
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
