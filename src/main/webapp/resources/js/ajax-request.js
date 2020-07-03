$(document).ready(
		function($) {

			$(document).on('click', '.specialties', function() {
				var idTypeStudy = $("#type_study_list").val();
				var idFaculty = $("#faculty_list").val();

				$.get('/university-project/AjaxController', {
					command : 'get_specialty',
					type_study : idTypeStudy,
					faculty : idFaculty
				}, createSpecialty);
			});

			function createSpecialty(data) {

				$('#specialty_list option').remove();

				let specialties = '';

				$.each(data, function(specialty, listSpec1) {

					specialties += '<option value="' + listSpec1.id + '">'
							+ listSpec1.name + '</option>';

				});

				$('#specialty_list').append(specialties);

			}

			$(document).on('click', '#show-app', function() {
				$(".table-filters").show();
				createApplicationCaption();
				var filter = $(this).val();

				$.get('/university-project/AjaxController', {
					command : 'get_application',
					application_filter : filter
				}, createApplication);
			});

			$(document).on('click', '#show-faculty', function() {
				$(".table-filters").hide();
				createFacultyCaption();
				var filter = $(this).val();

				$.get('/university-project/AjaxController', {
					command : 'get_faculty',
					faculty_filter : filter
				}, createFaculty);

			});

			$(document).on('click', '.radio-filter', function() {
				var filter = $(this).val();

				$.get('/university-project/AjaxController', {
					command : 'get_application',
					application_filter : filter
				}, createApplication);
			});

			function createApplication(data) {

				$('#table-list tr').remove();

				let applications = '';

				$.each(data, function(application, app) {

					applications += '<tr>' + '<td>' + app.user.secondName + ' '
							+ app.user.name + ' ' + app.user.lastName + '</td>'
							+ '<td>' + app.adress + '</td>' + '<td>'
							+ app.specialties.name + '</td>' + '<td>'
							+ app.specialties.faculty.name + '</td>' + '<td>'
							+ app.specialties.typeStudy.typeName + '</td>'
							+ '<td>' + app.confirmation + '</td>'
							+ '<td> Посмотреть </td>' + '</tr>';

				});

				$('#table-list').append(applications);

			}

			function createFaculty(data) {

				$('#table-list tr').remove();

				let applications = '';

				$.each(data, function(faculty, fac) {

					applications += '<tr>' + '<td>' + fac.name + '</td>'
							+ '</tr>';

				});

				$('#table-list').append(applications);

			}

			function createApplicationCaption() {

				$('#table-caption tr').remove();

				let thcap = '';

				thcap = '<tr>' + '<th scope="col">' + 'FIO' + '</th>'
						+ '<th scope="col">' + 'Adres' + '</th>'
						+ '<th scope="col">' + 'Specialty' + '</th>'
						+ '<th scope="col">' + 'Faculty' + '</th>'
						+ '<th scope="col">' + 'Type study' + '</th>'
						+ '<th scope="col">' + 'Confirmation' + '</th>'
						+ '<th scope="col">' + 'User account' + '</th>'
						+ '</tr>';

				$('#table-caption').append(thcap);

			}

			function createFacultyCaption() {
				
			
				$('#table-caption tr').remove();

				let thcap = '';

				thcap = '<tr>' + '<th scope="col">' + 'Faculty' + '</th>'
						+ '</tr>';

				$('#table-caption').append(thcap);

			}

		});