$(document).ready(
		function($) {

			$(document).on('click', '.specialties', function() {
				var idTypeStudy = $("#type_study_list").val();
				var idFaculty = $("#faculty_list").val();
				console.log('spec spec');

				$.get('/university-project/AjaxController', {
					command : 'get_specialty',
					specialty_filter: 'specialty',
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

			
			$(document).on('click', '#show-specialty', function() {
				$(".table-filters").hide();
				createSpecialtyCaption();
				var filter = $(this).val();

				$.get('/university-project/AjaxController', {
					command : 'get_specialty',
					specialty_filter : filter
				}, createAllSpecialty);

			});
			
			
			$(document).on('click', '#show-type-study', function() {
				$(".table-filters").hide();
				createTypeStudyCaption();
				var filter = $(this).val();

				$.get('/university-project/AjaxController', {
					command : 'get_type_study',
					typestudy_filter : filter
				}, createTypeStudy);

			});
			
			
			$(document).on('click', '#show-privilege', function() {
				$(".table-filters").hide();
				createPrivilegeCaption();
				var filter = $(this).val();

				$.get('/university-project/AjaxController', {
					command : 'get_privilege',
					privilege_filter : filter
				}, createPrivilege);

			});
			
			$(document).on('click', '#show-school', function() {
				$(".table-filters").hide();
				createSchoolCaption();
				var filter = $(this).val();

				$.get('/university-project/AjaxController', {
					command : 'get_school',
					school_filter : filter
				}, createSchool);

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
							+ '<td> <a href="Controller?command=show_userpage&user_id=' + app.user.id + '"> CLICK </a> </td>' + '</tr>';

				});

				$('#table-list').append(applications);

			}
			
			function createAllSpecialty(data) {

				$('#table-list tr').remove();

				let applications = '';

				$.each(data, function(specialty, spec) {

					applications += '<tr>'
							+ '<td>' + spec.name + '</td>' 
							+ '<td>' + spec.plan + '</td>' 
							+ '<td>' + spec.year + '</td>' 
							+ '<td>' + spec.faculty.name + '</td>' 
							+ '<td>' + spec.typeStudy.typeName + '</td>'
							+ '<td> <a href="Controller?command=show_update_specialty_page&specialty_id=' + spec.id + '"> CLICK </a> </td>'
							+ '</tr>';

				});

				$('#table-list').append(applications);

			}
			
			
			function createSchool(data) {

				$('#table-list tr').remove();

				let applications = '';

				$.each(data, function(school, sch) {

					applications += '<tr>'
							+ '<td>' + sch.name + '</td>' 
							+ '<td>' + sch.level + '</td>' 
							+ '<td>' + sch.institution + '</td>' 
							+ '<td> <a href="Controller?command=show_update_school_page&school_id=' + sch.id + '"> CLICK </a> </td>'
							+ '</tr>';

				});

				$('#table-list').append(applications);

			}

			function createFaculty(data) {

				$('#table-list tr').remove();

				let applications = '';

				$.each(data, function(faculty, fac) {

					applications += '<tr>' + '<td>' + fac.name + '</td>' 
					+ '<td> <a href="Controller?command=show_update_faculty_page&faculty_id=' + fac.id + '"> CLICK </a> </td>'
							+ '</tr>';

				});

				$('#table-list').append(applications);

			}
			
			
			function createPrivilege(data) {

				$('#table-list tr').remove();

				let applications = '';

				$.each(data, function(privilege, priv) {

					applications += '<tr>' + '<td>' + priv.name + '</td>'
					+ '<td> <a href="Controller?command=show_update_privilege_page&privilege_id=' + priv.id + '"> CLICK </a> </td>'
							+ '</tr>';

				});

				$('#table-list').append(applications);

			}
			
			function createTypeStudy(data) {

				$('#table-list tr').remove();

				let applications = '';

				$.each(data, function(typeStudy, ts) {

					applications += '<tr>' + '<td>' + ts.typeName + '</td>'
					+ '<td> <a href="Controller?command=show_update_type_study_page&type_study_id=' + ts.id + '"> CLICK </a> </td>'
							+ '</tr>';

				});

				$('#table-list').append(applications);

			}

			function createApplicationCaption() {
				
				$('#Specialty-caption').hide();
				$('#Faculty-caption').hide();
				$('#TypeStudy-caption').hide();
				$('#School-caption').hide();
				$('#Privilege-caption').hide();
				$('#Application-caption').show();



			}
			
			function createSpecialtyCaption() {
				$('#Specialty-caption').show();
				$('#Faculty-caption').hide();
				$('#TypeStudy-caption').hide();
				$('#School-caption').hide();
				$('#Privilege-caption').hide();
				$('#Application-caption').hide();



			}
			
			function createSchoolCaption() {
				$('#Specialty-caption').hide();
				$('#Faculty-caption').hide();
				$('#TypeStudy-caption').hide();
				$('#School-caption').show();
				$('#Privilege-caption').hide();
				$('#Application-caption').hide();



			}

			function createFacultyCaption() {
				$('#Specialty-caption').hide();
				$('#Faculty-caption').show();
				$('#TypeStudy-caption').hide();
				$('#School-caption').hide();
				$('#Privilege-caption').hide();
				$('#Application-caption').hide();
			


			}
			
			function createTypeStudyCaption() {
				
				$('#Specialty-caption').hide();
				$('#Faculty-caption').hide();
				$('#TypeStudy-caption').show();
				$('#School-caption').hide();
				$('#Privilege-caption').hide();
				$('#Application-caption').hide();
				


			}
			
			function createPrivilegeCaption() {
				
				$('#Specialty-caption').hide();
				$('#Faculty-caption').hide();
				$('#TypeStudy-caption').hide();
				$('#School-caption').hide();
				$('#Privilege-caption').show();
				$('#Application-caption').hide();
//				

			}

		});