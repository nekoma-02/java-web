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


<title>Анкета</title>
</head>
<body>
	<jsp:include page="header_nav.jsp"></jsp:include>

	<div>

		<h1 id="account_text">Заявление:</h1>
		
		<form action="//" method="post" class="add_application_form">

			<input type="hidden" name="command" value="add_application">

			<div class="form-group">
				<label for="formGroupExampleInput">Адрес:</label> 
				<input type="text" 
					   class="form-control" id="formGroupExampleInput" 
					   name="adres">
			</div>
			
			<div class="form-group">
				<label for="formGroupExampleInput">Средний балл аттестата:</label> 
				<input type="text" 
					   class="form-control" id="formGroupExampleInput" 
					   name="certificate">
			</div>
			
			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <label class="input-group-text" for="inputGroupSelect01">Льготы:</label>
			  </div>
			  <select class="custom-select" id="inputGroupSelect01">
			    <option selected>Choose...</option>
			    <c:forEach items="${requestScope.privileges}" var="priv">
					<option value="${priv.id}">${priv.name}</option>
				</c:forEach>
			  </select>
			</div>
			
			<div class="form-group">
				<label for="formGroupExampleInput">Имя</label> <input type="text"
					class="form-control" id="formGroupExampleInput" placeholder="Name"
					name="name" value="${requestScope.user_info.name}">
			</div>

			<div class="form-group">
				<label for="formGroupExampleInput">Фамилия</label> <input
					type="text" class="form-control" id="formGroupExampleInput"
					placeholder="second name" name="secondname"
					value="${requestScope.user_info.secondName}">
			</div>

			<div class="form-group">
				<label for="formGroupExampleInput">Отчество</label> <input
					type="text" class="form-control" id="formGroupExampleInput"
					placeholder="last name" name="lastname"
					value="${requestScope.user_info.lastName}">
			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">Почтовый ящик</label> <input
					type="email" class="form-control" id="exampleInputEmail1"
					placeholder="Enter email" name="email"
					value="${requestScope.user_info.email}">
			</div>
			
			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <label class="input-group-text" for="inputGroupSelect01">Учебное заведение:</label>
			  </div>
			  <select class="custom-select" id="inputGroupSelect01">
			    <option selected>Choose...</option>
			    <c:forEach items="${requestScope.schools}" var="school">
					<option value="${school.id}">${school.name}</option>
				</c:forEach>
			  </select>
			</div>
			
			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <label class="input-group-text" for="inputGroupSelect01">Специальность:</label>
			  </div>
			  <select class="custom-select" id="inputGroupSelect01">
			    <option selected>Choose...</option>
			    <c:forEach items="${requestScope.specialties}" var="spec">
					<option value="${spec.id}">${spec.name} (${spec.typeStudy.typeName})</option>
				</c:forEach>
			  </select>
			</div>
			
			<div class="form-check">
    			<input type="checkbox" class="form-check-input" id="exampleCheck1">
    			<label class="form-check-label" for="exampleCheck1">Необходимость общежития:</label>
  			</div>


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