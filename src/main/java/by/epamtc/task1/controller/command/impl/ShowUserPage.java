package by.epamtc.task1.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.task1.controller.JSPPageName;
import by.epamtc.task1.controller.RequestParameterName;
import by.epamtc.task1.controller.SessionParameterName;
import by.epamtc.task1.controller.command.Command;
import by.epamtc.task1.entity.User;
import by.epamtc.task1.service.ServiceFactory;
import by.epamtc.task1.service.UserService;
import by.epamtc.task1.service.exception.ServiceException;

public class ShowUserPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = ServiceFactory.getInstance().getUserService();
		
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute(SessionParameterName.USER_ID_SESSION_ATTRIBUTE);

		try {
			User user = service.userById(userId);
			request.setAttribute(RequestParameterName.USER_INFO, user);
			
		} catch (ServiceException e) {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_PAGE);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
	}

}
