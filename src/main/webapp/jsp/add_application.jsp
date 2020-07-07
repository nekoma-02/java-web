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

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"
	type="text/javascript"></script>
<script src="resources/js/ajax-request.js" type="text/javascript"></script>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />


<title>Анкета</title>
</head>
<body>
	<jsp:include page="../WEB-INF/jsp/part/header_nav.jsp"></jsp:include>

	<c:if test="${not empty message}">
		<div class="alert alert-warning" role="alert" id="alert">
			<c:out value="${message}"></c:out>
		</div>
	</c:if>

	<div>
		<form>
			<input type="hidden" name="command" value="add_application">
			<div class="container">
				<div class="row">
					<div class="col">
						<legend>1.ФИО</legend>
						<div class="form-group">
							<label for="input1" class="col-form-label">Фамилия:</label> <input
								type="text" class="form-control" id="input1" 
								name="secondname" aria-describedby="inputMutedtext" 
								value="${requestScope.user_info.secondName}">
							<c:if test="${not empty invalid_second_name}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_second_name}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Имя:</label> <input
								type="text" class="form-control" id="input1" required
								name="name"  aria-describedby="inputMutedtext" value="${requestScope.user_info.name}">
							<c:if test="${not empty invalid_first_name}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_first_name}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Отчество:</label> <input
								type="text" class="form-control" id="input1" required
								name="lastname"  aria-describedby="inputMutedtext" value="${requestScope.user_info.lastName}">
							<c:if test="${not empty invalid_last_name}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_last_name}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="inputDate" class=" col-form-label">Дата
								рождения:</label> <input type="date" class="form-control" id="inputDate"
								required name="date_of_birth"  aria-describedby="inputMutedtext" 
								value="${requestScope.user_info.dateOfBirth}">
							<c:if test="${not empty message}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${message}"></c:out>
								</small>
							</c:if>

						</div>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">Пол:</label>

							<select class="custom-select mr-sm-2" id="inlineFormCustomSelect"
								required name="gender" aria-describedby="inputMutedtext" >
								<option selected value="Мужской">Мужской</option>
								<option value="Женский">Женский</option>
							</select>
							<c:if test="${not empty invalid_gender}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_gender}"></c:out>
								</small>
							</c:if>

						</div>
						<div class="form-group">
							<label class="col-sm-5 col-form-label"
								for="inlineFormCustomSelect">Семейное положение:</label> <select
								class="form-control" id="inlineFormCustomSelect" required
								name="marital_status" aria-describedby="inputMutedtext" >
								<option selected value="Холост/Не замужем">Холост/Не
									замужем</option>
								<option value="Женат/Замужем">Женат/Замужем</option>
							</select>
							<c:if test="${not empty invalid_marital_status}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_marital_status}"></c:out>
								</small>
							</c:if>

						</div>
					</div>
					<div class="col">
						<legend>2.Документ удостоверяющий личность</legend>
						<div class="form-group">
							<label class=" col-form-label text-right"
								for="inlineFormCustomSelect">Тип документа:</label> <select
								class="custom-select mr-sm-2" id="inlineFormCustomSelect"
								required name="type_document" aria-describedby="inputMutedtext" >
								<option selected value="Паспорт гражданина РБ">Паспорт
									гражданина РБ</option>
							</select>
							<c:if test="${not empty invalid_type_document}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_type_document}"></c:out>
								</small>
							</c:if>

						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Ид. номер:</label> <input
								type="text" class="form-control" id="input1" required
								name="id_document" aria-describedby="inputMutedtext" 
								value="${requestScope.application.idDocument}">
							<c:if test="${not empty invalid_id_document}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_id_document}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Серия:</label> <input
								type="text" class="form-control" id="input1" required
								name="series_passport" aria-describedby="inputMutedtext" 
								value="${requestScope.application.seriesPassport}">
							<c:if test="${not empty invalid_series_passport}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_series_passport}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Номер:</label> <input
								type="text" class="form-control" id="input1" required
								name="number_passport" aria-describedby="inputMutedtext" 
								value="${requestScope.application.numberPassport}">
							<c:if test="${not empty invalid_number_passport}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_number_passport}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Кем выдан:</label> <input
								type="text" class="form-control" id="input1" required
								name="issued_by"  aria-describedby="inputMutedtext" value="${requestScope.application.issuedBy}">
							<c:if test="${not empty invalid_issued_by}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_issued_by}"></c:out>
								</small>
							</c:if>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<legend>3.Образование</legend>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">Учебное
								учреждение:</label> <select class="form-control"
								id="inlineFormCustomSelect" required name="school"> aria-describedby="inputMutedtext" 
								<option value="${requestScope.school.name}" selected></option>
								<c:forEach items="${requestScope.schools}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty invalid_school}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_school}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="inputDate" class=" col-form-label">Дата
								окончания:</label> <input type="date" class="form-control"
								id="inputDate" required name="end_study_date" aria-describedby="inputMutedtext" 
								value="${requestScope.application.endStudyDate}">
							<c:if test="${not empty invalid_end_study_date}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_end_study_date}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Ср. балл:</label> <input
								type="text" class="form-control" id="input1" required
								name="certificate" aria-describedby="inputMutedtext" 
								value="${requestScope.application.certificate}">
							<c:if test="${not empty invalid_certificate}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_certificate}"></c:out>
								</small>
							</c:if>
						</div>
					</div>
					<div class="col">
						<legend>4.Адрес</legend>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Адрес:</label> <input
								type="text" class="form-control" id="input1" required
								name="adres"  aria-describedby="inputMutedtext" value="${requestScope.application.adress}">
							<c:if test="${not empty invalid_adress}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_adress}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Место
								рождения:</label> <input type="text" class="form-control" id="input1"
								required name="place_of_birth" aria-describedby="inputMutedtext" 
								value="${requestScope.user_info.placeOfBirth}">
							<c:if test="${not empty invalid_place_of_birth}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_place_of_birth}"></c:out>
								</small>
							</c:if>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<legend>5.Специальность</legend>
						<div class="form-group">
							<label class=" col-form-label" for="faculty_list">Выберете
								факультет:</label> <select class="form-control" id="faculty_list"
								required name="faculty" aria-describedby="inputMutedtext" >
								<option value="${requestScope.specialty.faculty.id}" selected>${requestScope.specialty.faculty.name}</option>
								<c:forEach items="${requestScope.faculty}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty invalid_faculty_name}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_faculty_name}"></c:out>
								</small>
							</c:if>
						</div>

						<div class="form-group">
							<label class=" col-form-label" for="type_study_list">Выберете
								тип обучения:</label> <select class="form-control" id="type_study_list"
								required name="type_study" aria-describedby="inputMutedtext" >
								<option value="${requestScope.specialty.typeStudy.id}" selected>${requestScope.specialty.typeStudy.typeName}</option>
								<c:forEach items="${requestScope.type_study}" var="item">
									<option value="${item.id}">${item.typeName}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty invalid_type_name}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_type_name}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label class=" col-form-label" for="specialty_list">Выберете
								специальность:</label> <select class="form-control specialties"
								id="specialty_list" required name="specialty" aria-describedby="inputMutedtext" >
								<option value="${requestScope.specialty.id}" selected>${requestScope.specialty.name}</option>
							</select>
							<c:if test="${not empty invalid_specialty}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_specialty}"></c:out>
								</small>
							</c:if>
						</div>
					</div>
					<div class="col">
						<legend>6.Прочее</legend>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Почтовый
								ящик:</label> <input type="text" class="form-control" id="input1"
								required name="email"  aria-describedby="inputMutedtext" value="${requestScope.user_info.email}">
							<c:if test="${not empty invalid_email}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_email}"></c:out>
								</small>
							</c:if>
						</div>
						<div class="form-group">
							<label class=" col-form-label" for="inlineFormCustomSelect">Привилегия:</label>

							<select class="form-control" id="inlineFormCustomSelect" required
								name="privilege" aria-describedby="inputMutedtext" >
								<option value="${requestScope.privilege.id}" selected>${requestScope.privilege.name}</option>
								<c:forEach items="${requestScope.privileges}" var="priv">
									<option value="${priv.id}">${priv.name}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty invalid_privilege}">
								<small id="inputMutedtext" class="form-text text-muted">
									<c:out value="${invalid_privilege}"></c:out>
								</small>
							</c:if>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<button type="submit" class="btn btn-primary btn-block">Сохранить</button>
					</div>
				</div>
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