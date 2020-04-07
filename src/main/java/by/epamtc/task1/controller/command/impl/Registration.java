package by.epamtc.task1.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.task1.controller.JSPPageName;
import by.epamtc.task1.controller.RequestParameterName;
import by.epamtc.task1.controller.command.Command;
import by.epamtc.task1.entity.Role;
import by.epamtc.task1.entity.User;
import by.epamtc.task1.service.ServiceFactory;
import by.epamtc.task1.service.UserService;
import by.epamtc.task1.service.exception.ServiceException;

public class Registration implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		
		UserService service = ServiceFactory.getInstance().getUserService();

		String name = request.getParameter(RequestParameterName.NAME);
		String secondName = request.getParameter(RequestParameterName.SECOND_NAME);
		String lastName = request.getParameter(RequestParameterName.LAST_NAME);
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		//boolean status = false;
		String email = request.getParameter(RequestParameterName.EMAIL);
		Role role = Role.valueOf(request.getParameter(RequestParameterName.USER_ROLE).toUpperCase());

		User user = new User(0, name, secondName, lastName, login, password, email, role);

		try {

			boolean isExist = service.isUserExist(login);
			if (isExist) {
				request.setAttribute(RequestParameterName.RESULT_INFO,
						"Пользователь с данным логином уже зарегистрирован");
			} else {

				boolean isSaved = service.registration(user);
				
				if (isSaved) {
					page = JSPPageName.INDEX_PAGE;
				} else {
					request.setAttribute(RequestParameterName.RESULT_INFO, "Ошибка регистрации");
					page = JSPPageName.REGISTRATION_PAGE;
				}

			}

		} catch (ServiceException e) {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
			logger.log(Level.ERROR,e);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
	}

}
