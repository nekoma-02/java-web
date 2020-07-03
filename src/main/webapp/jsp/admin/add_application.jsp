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

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
<script src="resources/js/ajax-request.js" type="text/javascript"></script>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local/local" var="loc" />


<title>Анкета</title>
</head>
<body>
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/part/header_nav.jsp"></jsp:include>

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
							<label for="input1" class="col-form-label">Фамилия:</label>
							<input type="text" class="form-control" id="input1" name="secondname" value="${requestScope.user_info.secondName}">
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Имя:</label>
							
								<input type="text" class="form-control" id="input1" name="name" value="${requestScope.user_info.name}">

						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Отчество:</label>

								<input type="text" class="form-control" id="input1" name="lastname" value="${requestScope.user_info.lastName}">

						</div>
 						<div class="form-group">
    						<label for="inputDate" class=" col-form-label">Дата рождения:</label>
    				
    							<input type="date" class="form-control" id="inputDate" name="date_of_birth" value="${requestScope.user_info.dateOfBirth}">
    					
						</div>
						<div class="form-group">
    						<label class=" col-form-label" for="inlineFormCustomSelect">Пол:</label>
    						
      							<select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="gender">
        							<option selected value="Мужской">Мужской</option>
        							<option value="Женский">Женский</option>
      							</select>
    						
						</div>
						<div class="form-group">
    						<label class="col-sm-5 col-form-label" for="inlineFormCustomSelect">Семейное положение:</label>
    						
      							<select class="form-control" id="inlineFormCustomSelect" name="marital_status">
        							<option selected value="Холост/Не замужем">Холост/Не замужем</option>
        							<option value="Женат/Замужем">Женат/Замужем</option>
      							</select>
    						
						</div>
					</div>
					<div class="col">
					<legend>2.Документ удостоверяющий личность</legend>
					<div class="form-group">
    						<label class=" col-form-label text-right" for="inlineFormCustomSelect">Тип документа:</label>
    						
      							<select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="type_document">
        							<option selected value="Паспорт гражданина РБ">Паспорт гражданина РБ</option>
      							</select>
    					
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Ид. номер:</label>
						
								<input type="text" class="form-control" id="input1" name="id_document" value="${requestScope.application.idDocument}">
						
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Серия:</label>
						
								<input type="text" class="form-control" id="input1" name="series_passport" value="${requestScope.application.seriesPassport}">
						
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Номер:</label>
					
								<input type="text" class="form-control" id="input1" name="number_passport" value="${requestScope.application.numberPassport}"> 
						
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Кем выдан:</label>
						
								<input type="text" class="form-control" id="input1" name="issued_by" value="${requestScope.application.issuedBy}">
						
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<legend>3.Образование</legend>
						<div class="form-group">
    						<label class=" col-form-label" for="inlineFormCustomSelect">Учебное учреждение:</label>
    					
      							<select class="form-control" id="inlineFormCustomSelect" name="school">
      								<option value="${requestScope.school.name}" selected></option>
        							<c:forEach items="${requestScope.schools}" var="item">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
      							</select>
    						
						</div>
						<div class="form-group">
    						<label for="inputDate" class=" col-form-label">Дата окончания:</label>
    						
    							<input type="date" class="form-control" id="inputDate" name="end_study_date" value="${requestScope.application.endStudyDate}">
    				
						</div>
						<div class="form-group">
							<label for="input1" class=" col-form-label">Ср. балл:</label>
							
								<input type="text" class="form-control" id="input1" name="certificate" value="${requestScope.application.certificate}">
							
						</div>
					</div>
					<div class="col">
					<legend>4.Адрес</legend>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Адрес:</label>
						
							<input type="text" class="form-control" id="input1" name="adres">
					
					</div>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Место рождения:</label>
						
							<input type="text" class="form-control" id="input1" name="place_of_birth">
				
					</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
					<legend>5.Специальность</legend>
					<div class="form-group">
    						<label class=" col-form-label" for="faculty_list">Выберете факультет:</label>
      							<select class="form-control" id="faculty_list" name="faculty">
      								<option value="0" selected></option>
      								<c:forEach items="${requestScope.faculty}" var="item">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
      							</select>
    					
						</div>
						
						<div class="form-group">
    						<label class=" col-form-label" for="type_study_list">Выберете тип обучения:</label>
      							<select class="form-control" id="type_study_list" name="type_study">
      								<option value="0" selected></option>
      								<c:forEach items="${requestScope.type_study}" var="item">
										<option value="${item.id}">${item.typeName}</option>
									</c:forEach>
      							</select>
    					
						</div>
					<div class="form-group">
    						<label class=" col-form-label" for="specialty_list">Выберете специальность:</label>
      							<select class="form-control specialties" id="specialty_list" name="specialty">
      								<option value="0" selected></option>
      							</select>
    					
						</div>
					</div>
					<div class="col">
					<legend>6.Прочее</legend>
					<div class="form-group">
						<label for="input1" class=" col-form-label">Почтовый ящик:</label>
						
							<input type="text" class="form-control" id="input1" name="email" value="${requestScope.user_info.email}">
					
					</div>
					<div class="form-group">
    						<label class=" col-form-label" for="inlineFormCustomSelect">Привилегия:</label>
    						
      							<select class="form-control" id="inlineFormCustomSelect" name="privilege">
      								<option value="0" selected></option>
        							<c:forEach items="${requestScope.privileges}" var="priv">
										<option value="${priv.id}">${priv.name}</option>
									</c:forEach>
      							</select>
    						
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