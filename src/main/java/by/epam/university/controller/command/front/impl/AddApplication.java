package by.epam.university.controller.command.front.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.command.front.ForwardException;
import by.epam.university.controller.parameter.JSPPageName;
import by.epam.university.controller.parameter.RequestParameterName;
import by.epam.university.controller.parameter.SessionParameterName;
import by.epam.university.entity.Application;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.User;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.validator.ApplicationValidator;
import by.epam.university.service.validator.PrivilegeValidator;
import by.epam.university.service.validator.SchoolValidator;
import by.epam.university.service.validator.SpecialtyValidator;
import by.epam.university.service.validator.UserValidator;
import by.epam.university.service.validator.factory.ValidatorFactory;

public class AddApplication implements Command {

	private static Logger logger = LogManager.getLogger();
	
	private static final String USER_PAGE="/Controller?command=show_userpage";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		UserService userService = ServiceFactory.getInstance().getUserService();

		Object idApplication = session.getAttribute(SessionParameterName.APPLICATION_ID);
		int idUser = (Integer) session.getAttribute(SessionParameterName.USER_ID);
		String adres = request.getParameter(RequestParameterName.ADRES);
		String name = request.getParameter(RequestParameterName.NAME);
		String secondName = request.getParameter(RequestParameterName.SECOND_NAME);
		String lastName = request.getParameter(RequestParameterName.LAST_NAME);
		String email = request.getParameter(RequestParameterName.EMAIL);
		String idSpecialty = request.getParameter(RequestParameterName.SPECIALTY);
		String idPrivilege = request.getParameter(RequestParameterName.PRIVILEGE);
		String idSchool = request.getParameter(RequestParameterName.SCHOOL);
		String certificate = request.getParameter(RequestParameterName.CERTIFICATE);
		String gender = request.getParameter(RequestParameterName.GENDER);
		String maritalStatus = request.getParameter(RequestParameterName.MARITAL_STATUS);
		String placeOfBirth = request.getParameter(RequestParameterName.PLACE_OF_BIRTH);
		String dateOfBirth = request.getParameter(RequestParameterName.DATE_OF_BIRTH);
		String typeDocument = request.getParameter(RequestParameterName.TYPE_DOCUMENT);
		String idDocument = request.getParameter(RequestParameterName.ID_DOCUMENT);
		String seriesPassport = request.getParameter(RequestParameterName.SERIES_PASSPORT);
		String numberPassport = request.getParameter(RequestParameterName.NUMBER_PASSPORT);
		String issuedBy = request.getParameter(RequestParameterName.ISSUED_BY);
		String endStudyDate = request.getParameter(RequestParameterName.END_STUDY_DATE);
		

		
		try {
			UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
			ApplicationValidator appValidator = ValidatorFactory.getInstance().getApplicationValidator();
			SpecialtyValidator specValidator = ValidatorFactory.getInstance().getSpecialtyValidator();
			PrivilegeValidator privValidator = ValidatorFactory.getInstance().getPrivilegeValidator();
			SchoolValidator schoolValidator = ValidatorFactory.getInstance().getSchoolValidator();
			
			List<String> validation = userValidator.validate(name, secondName, lastName, email, gender, maritalStatus, placeOfBirth);
			validation.addAll(appValidator.validate(adres, certificate, typeDocument, idDocument, seriesPassport, numberPassport, issuedBy, endStudyDate));
			validation.addAll(specValidator.validate(idSpecialty));
			validation.addAll(privValidator.validate(idPrivilege,"false"));
			validation.addAll(schoolValidator.validate(idSchool));
			
			if (validation.size() == 0 || validation == null) {
				
				Application application = new Application(0, adres, Integer.parseInt(certificate), new Privilege(Integer.parseInt(idPrivilege)), new User(idUser), new School(Integer.parseInt(idSchool)), new Specialty(Integer.parseInt(idSpecialty)), false, typeDocument, idDocument, seriesPassport, Integer.parseInt(numberPassport), issuedBy, convert(endStudyDate));
				User user = new User(idUser,name,secondName,lastName,email,gender,maritalStatus, placeOfBirth, convert(dateOfBirth));

				userService.updateUser(user);
				
				if (idApplication == null) {
					appService.createApplication(application);
				} else {
					appService.updateApplication(application);
				}
				
				session.setAttribute(SessionParameterName.APPLICATION_ID, appService.ApplicationByUserId(idUser).getId());
				response.sendRedirect(request.getContextPath()+USER_PAGE);
				
			} else {
				
				for (String item : validation) {
					request.setAttribute(item.toLowerCase(), item);
				}
				
				forwardTo(request, response, JSPPageName.ADD_APPLICATION_PAGE);
		 
			}
			
			session.setAttribute(SessionParameterName.QUERY_STRING,request.getQueryString());
			
		} catch (ServiceException | ParseException | ForwardException e ) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}


	}
	
	private static java.sql.Date convert(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date udate = formatter.parse(date);
		java.sql.Date sDate = new java.sql.Date(udate.getTime());
		return sDate;
	}

}
