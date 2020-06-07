package by.epam.university.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.controller.JSPPageName;
import by.epam.university.controller.RequestParameterName;
import by.epam.university.controller.SessionParameterName;
import by.epam.university.controller.command.Command;
import by.epam.university.entity.Application;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.User;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class AddApplication implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();

		int idUser = (Integer) session.getAttribute(SessionParameterName.USER_ID);
		String adres = request.getParameter(RequestParameterName.ADRES);
		String needHostel = request.getParameter(RequestParameterName.NEED_HOSTEL);
		String name = request.getParameter(RequestParameterName.NAME);
		String secondName = request.getParameter(RequestParameterName.SECOND_NAME);
		String lastName = request.getParameter(RequestParameterName.LAST_NAME);
		String email = request.getParameter(RequestParameterName.EMAIL);
		String idSpecialty = request.getParameter(RequestParameterName.SPECIALTY);
		String idPrivilege = request.getParameter(RequestParameterName.PRIVILEGE);
		String idSchool = request.getParameter(RequestParameterName.SCHOOL);
		String certificate = request.getParameter(RequestParameterName.CERTIFICATE);

		Application application = new Application(0,
				adres, 
				Integer.parseInt(certificate),
				new Privilege(Integer.parseInt(idPrivilege)), 
				new User(idUser),
				new School(Integer.parseInt(idSchool)), 
				new Specialty(Integer.parseInt(idSpecialty)), 
				false);
		
		if (needHostel == "true") {
			application.setNeedHostel(true);
		}
		else {
			application.setNeedHostel(false);
		}
		
		try {
			boolean isCreateApp = false;
			isCreateApp = appService.createApplication(application);
			if (isCreateApp) {
				response.sendRedirect(JSPPageName.INDEX_PAGE);
			} else {
				request.setAttribute(RequestParameterName.RESULT_INFO, "Ошибка ввода данных");
				forwardTo(request, response, JSPPageName.ADD_APPLICATION_PAGE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
		

//		System.out.println("adres "+adres);
//		System.out.println("needHostel "+needHostel);
//		System.out.println("name "+name);
//		System.out.println("secondName "+secondName);
//		System.out.println("lastName "+lastName);
//		System.out.println("email "+email);
//		System.out.println("specialty "+specialty);
//		System.out.println("privilege "+privilege);
//		System.out.println("school "+school);
//		System.out.println("certificate "+certificate);


	}

}
