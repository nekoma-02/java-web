package by.epam.university.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.university.controller.JSPPageName;
import by.epam.university.controller.RequestParameterName;
import by.epam.university.controller.SessionParameterName;
import by.epam.university.controller.command.Command;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.User;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;

public class ShowAddApplicationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = ServiceFactory.getInstance().getUserService();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute(SessionParameterName.USER_ID);

		try {
			User user = userService.userById(userId);
			List<School> schools = appService.getAllSchools();
			List<Privilege> privileges = appService.getAllPrivileges();
			List<Specialty> specialties = appService.getAllSpecialties();

			
			request.setAttribute(RequestParameterName.USER_INFO, user);
			request.setAttribute(RequestParameterName.SCHOOLS, schools);
			request.setAttribute(RequestParameterName.PRIVILEGES, privileges);
			request.setAttribute(RequestParameterName.SPECIALTIES, specialties);
			
			
			forwardTo(request, response, JSPPageName.ADD_APPLICATION_PAGE);
			
		} catch (ServiceException e) {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
		
	}

}
