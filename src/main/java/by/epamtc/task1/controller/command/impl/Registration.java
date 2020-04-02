package by.epamtc.task1.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.task1.controller.JSPPageName;
import by.epamtc.task1.controller.RequestParameterName;
import by.epamtc.task1.controller.command.Command;
import by.epamtc.task1.entity.User;
import by.epamtc.task1.service.ServiceFactory;
import by.epamtc.task1.service.UserService;
import by.epamtc.task1.service.exception.ServiceException;

public class Registration implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		UserService service = ServiceFactory.getInstance().getService();

		String name = request.getParameter(RequestParameterName.NAME);
		String secondName = request.getParameter(RequestParameterName.SECOND_NAME);
		String lastName = request.getParameter(RequestParameterName.LAST_NAME);
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		boolean status = false;
		String email = request.getParameter(RequestParameterName.EMAIL);
		int role = 1;

		User user = new User(0,name,secondName,lastName,login,password,status,email,role);

		try {

			boolean isSaved = service.registration(user);
			if (isSaved) {
				page = JSPPageName.INDEX_PAGE;
			} else {
				request.setAttribute(RequestParameterName.RESULT_INFO, "registration error");
				page = JSPPageName.REGISTRATION_PAGE;
			}

		} catch (ServiceException e) {

			request.setAttribute(RequestParameterName.RESULT_INFO, e.getMessage());
			page = JSPPageName.ERROR_PAGE;

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			response.setContentType("text/html");
			response.getWriter().println("e r r o r");
		}
	}

}
