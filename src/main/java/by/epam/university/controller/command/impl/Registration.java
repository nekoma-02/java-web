package by.epam.university.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.controller.JSPPageName;
import by.epam.university.controller.RequestParameterName;
import by.epam.university.controller.command.Command;
import by.epam.university.entity.Role;
import by.epam.university.entity.User;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;

public class Registration implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService service = ServiceFactory.getInstance().getUserService();

		String name = request.getParameter(RequestParameterName.NAME);
		String secondName = request.getParameter(RequestParameterName.SECOND_NAME);
		String lastName = request.getParameter(RequestParameterName.LAST_NAME);
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		String email = request.getParameter(RequestParameterName.EMAIL);		
		Role role = Role.valueOf(request.getParameter(RequestParameterName.USER_ROLE).toUpperCase());
		
		User user = new User(0, name, secondName, lastName, login, password, email, role);
		

		try {

			boolean isExist = service.isUserExist(login);
	
			if (!isExist) {
				request.setAttribute(RequestParameterName.RESULT_INFO,
						"Пользователь с данным логином уже зарегистрирован");
				forwardTo(request, response, JSPPageName.REGISTRATION_PAGE);
			} else {
				boolean isSaved = service.registration(user);
				if (isSaved) {
					response.sendRedirect(JSPPageName.INDEX_PAGE);
				} else {
					request.setAttribute(RequestParameterName.RESULT_INFO, "Ошибка регистрации");
					forwardTo(request, response, JSPPageName.REGISTRATION_PAGE);
				}
			}
			

		} catch (ServiceException e) {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
			logger.log(Level.ERROR,e);

		}

		
	}

}
