package by.epam.university.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.university.controller.JSPPageName;
import by.epam.university.controller.RequestParameterName;
import by.epam.university.controller.SessionParameterName;
import by.epam.university.controller.command.Command;
import by.epam.university.entity.User;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;

public class ShowUserPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = ServiceFactory.getInstance().getUserService();
		
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute(SessionParameterName.USER_ID);

		try {
			User user = service.userById(userId);
			request.setAttribute(RequestParameterName.USER_INFO, user);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_PAGE);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect(JSPPageName.ERROR_PAGE);
			}
			
		} catch (ServiceException e) {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
		
		
	}

}
