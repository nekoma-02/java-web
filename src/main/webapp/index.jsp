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
<fmt:message bundle="${loc}" key="local.locbutton.name.sign"
	var="sign_in_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.registr"
	var="regist_button" />

<title>Index page</title>
</head>
<body>
	<jsp:include page="WEB-INF/jsp/part/header_nav.jsp"></jsp:include>
	
	<div class="content">
	<div class="row">
    <div class="col">
            <legend>                
            <h2 style="text-align: center;">Личный кабинет абитуриента</h2>
            </legend>
            <div class="row">
                <div class="col">
                    <p>Если у Вас ещё нет личного кабинета абитуриента БГУИР, то Вам необходимо</p>
                </div>
                <div class="col" style="border-left: 1px dotted lightgray;">
                    <p>Если Вы уже зарегистрировались, то можете</p>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div>
                        <a class="btn btn-lg btn-primary" href="" style="width: 100%;">Создать кабинет</a>
                    </div>
                </div>
                <div class="col">
                    <div>
                        <a class="btn btn-lg btn-success" href="/" style="width: 100%;">Войти в кабинет</a>
                    </div>
                </div>
            </div>
    </div>
</div>
</div>	
	
	<!--  	<c:if test="${not empty sessionScope.user_id}">
		
			<c:if test="${not empty sessionScope.application_id}">
			<a id="btn_account" href="${pageContext.request.contextPath}/Controller?command=###"
				class="btn btn-primary btn-lg btn-block active" role="button"
				aria-pressed="true">Перейти к заявлению</a>
			</c:if>
			<c:if test="${empty sessionScope.application_id}">
			<a id="btn_account" href="${pageContext.request.contextPath}/Controller?command=show_addapplication_page"
				class="btn btn-primary btn-lg btn-block active" role="button"
				aria-pressed="true">Перейти к заполнению заявления</a>
			</c:if>
		</c:if>
		<c:if test="${empty sessionScope.user_id}">
			<a id="btn_aure" href="${pageContext.request.contextPath}/Controller?command=show_login_page"
				class="btn btn-primary btn-lg active" role="button"
				aria-pressed="true">${sign_in_button}</a>
			<a id="btn_aure" href="${pageContext.request.contextPath}/Controller?command=show_registration_page"
				class="btn btn-primary btn-lg active" role="button"
				aria-pressed="true">${regist_button}</a>
		</c:if>
	  -->

<!-- Footer -->
<footer class="page-footer font-small black">

  <div class="footer-copyright text-center py-3">© 2020 Copyright:
    <a href="/"> MDBootstrap.com</a>
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
