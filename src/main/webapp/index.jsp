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

<link rel="stylesheet" type="text/css" href="resources/css/style.css">

<meta http-equiv="Content-type" content="text/html; charset=utf-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />
<fmt:message bundle="${loc}" key="local.button.name.sign_in"
	var="sign_in" />
<fmt:message bundle="${loc}" key="local.button.name.sign_up"
	var="sign_up" />

<title>Главная страница</title>
</head>
<body>
	<jsp:include page="WEB-INF/jsp/part/header_nav.jsp"></jsp:include>


	<div class="content">
		<div class="row" id="accaunt-row">
			<div class="col">
				<legend>
					<h2 style="text-align: center;">Личный кабинет абитуриента</h2>
				</legend>

				<c:if test="${not empty sessionScope.user_id}">

					<c:if
						test="${not empty sessionScope.application_id && sessionScope.user_role != 'ADMIN'}">
						<div class="row">
							<div class="col">
								<a class="btn btn-lg btn-success"
									href="${pageContext.request.contextPath}/Controller?command=show_userpage"
									style="width: 100%;">Войти в кабинет</a>
							</div>
						</div>
					</c:if>
					<c:if test="${sessionScope.user_role == 'ADMIN'}">
						<div class="row">
							<div class="col">
								<a class="btn btn-lg btn-success"
									href="${pageContext.request.contextPath}/Controller?command=admin_page"
									style="width: 100%;">Админка</a>
							</div>
						</div>
					</c:if>
					<c:if test="${empty sessionScope.application_id}">

						<c:if test="${sessionScope.user_role != 'ADMIN'}">
							<div class="row">
								<div class="col">
									<a class="btn btn-lg btn-success"
										href="${pageContext.request.contextPath}/Controller?command=show_addapplication_page"
										style="width: 100%;">Перейти к заполнению кабинета</a>
								</div>
							</div>
						</c:if>
					</c:if>

				</c:if>

				<c:if test="${empty sessionScope.user_id}">
					<div class="row">
						<div class="col">
							<p>Если у Вас ещё нет личного кабинета абитуриента БГУА, то
								Вам необходимо</p>
						</div>
						<div class="col" style="border-left: 1px dotted lightgray;">
							<p>Если Вы уже зарегистрировались, то можете</p>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div>
								<a class="btn btn-lg btn-primary" href="jsp/registration.jsp"
									style="width: 100%;">${sign_up}</a>
							</div>
						</div>
						<div class="col">
							<div>
								<a class="btn btn-lg btn-success" href="jsp/login.jsp"
									style="width: 100%;">${sign_in}</a>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col">

				<h3 style="margin-top: 10px;">Белорусский государственный
					университет Александра</h3>
				<p style="font-size: medium; text-align: justify;">
					БГУА - это крупный учебно-научно-инновационный комплекс, в
					структуру которого входят <strong>10 факультетов</strong>, 38
					кафедр, научно-исследовательская часть, Институт информационных
					технологий (2 факультета и 3 кафедры). Университет осуществляет
					подготовку специалистов по <strong>38 специальностям</strong>,
					охватывающим все актуальные направления современной информатики,
					радиоэлектроники и телекоммуникаций.
				</p>
				<br />
				<div class="row" id="img-content">
					<p style="font-size: smaller; text-align: justify;"
						class="col-sm-4">
						<img src="resources/images/1.jpg" /> <br /> В вузе обучается
						около <strong>17 тысяч студентов, магистрантов и
							аспирантов</strong>, в том числе более 500 — из других стран. Ведется
						подготовка иностранных и белорусских студентов на английском
						языке. Активно развивается дистанционное обучение. Весь процесс
						обучения ведется с использованием современных информационных
						технологий, мультимедийной техники, электронных
						учебно-методических комплексов. За годы работы университет
						выпустил более 50 тысяч инженеров, которые успешно работают в
						наиболее наукоемких отраслях экономики.
					</p>
					<p style="font-size: smaller; text-align: justify;"
						class="col-sm-4">
						<img src="resources/images/3.jpg" /><br /> Научные школы БГУА
						являются признанными лидерами отечественной и мировой науки в
						области информатики и радиоэлектроники, что позволяет осуществлять
						подготовку кадров высшей научной квалификации по <strong>
							29 специальностям аспирантуры и 9 специальностям докторантуры </strong>,
						успешно развивать фундаментальные и прикладные исследования. За
						годы своего существования университет подготовил более 850
						кандидатов наук и 90 докторов наук.
					</p>
					<p style="font-size: smaller; text-align: justify;"
						class="col-sm-4">
						<img src="resources/images/2.jpg" /><br /> БГУА располагает
						современной лабораторной базой и учебно-научной инфраструктурой,
						позволяющей проводить научные исследования по основным
						направлениям университета.
					</p>
				</div>
			</div>
		</div>
	</div>





	<!-- Footer -->
	<footer class="page-footer font-small black">

		<div class="footer-copyright text-center py-3">
			© 2020 Copyright: <a href="/"> MDBootstrap.com</a>
		</div>

	</footer>
	<!-- Footer -->

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
