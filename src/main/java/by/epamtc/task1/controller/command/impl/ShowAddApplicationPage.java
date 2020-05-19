package by.epamtc.task1.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.task1.controller.JSPPageName;
import by.epamtc.task1.controller.RequestParameterName;
import by.epamtc.task1.controller.SessionParameterName;
import by.epamtc.task1.controller.command.Command;
import by.epamtc.task1.entity.Privilege;
import by.epamtc.task1.entity.School;
import by.epamtc.task1.entity.Specialty;
import by.epamtc.task1.entity.User;
import by.epamtc.task1.service.ApplicationService;
import by.epamtc.task1.service.ServiceFactory;
import by.epamtc.task1.service.UserService;
import by.epamtc.task1.service.exception.ServiceException;

public class ShowAddApplicationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = ServiceFactory.getInstance().getUserService();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute(SessionParameterName.USER_ID_SESSION_ATTRIBUTE);

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
