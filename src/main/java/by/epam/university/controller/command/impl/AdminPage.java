package by.epam.university.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.university.controller.JSPPageName;
import by.epam.university.controller.RequestParameterName;
import by.epam.university.controller.command.Command;
import by.epam.university.entity.User;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;

public class AdminPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = ServiceFactory.getInstance().getUserService();
		List<User> userList = null;
		
		try {
			userList = userService.getAllUser();
			request.setAttribute(RequestParameterName.USER_INFO, userList);
			forwardTo(request, response, JSPPageName.ADMIN_PAGE);
		} catch (ServiceException e) {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
		
		
	}

}
