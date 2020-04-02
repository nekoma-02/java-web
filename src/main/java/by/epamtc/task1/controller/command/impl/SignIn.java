package by.epamtc.task1.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.task1.controller.JSPPageName;
import by.epamtc.task1.controller.RequestParameterName;
import by.epamtc.task1.controller.SessionParameterName;
import by.epamtc.task1.controller.command.Command;
import by.epamtc.task1.entity.User;
import by.epamtc.task1.service.ServiceFactory;
import by.epamtc.task1.service.UserService;
import by.epamtc.task1.service.exception.ServiceException;

public class SignIn implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;

		UserService service = ServiceFactory.getInstance().getUserService();

		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);

		HttpSession session = request.getSession();

		try {
			boolean isAuth = service.signIn(login, password);

			if (isAuth) {
				User user = service.userByLogin(login);
				
				session.setAttribute(SessionParameterName.USER_ID_SESSION_ATTRIBUTE, user.getId());
				session.setAttribute(SessionParameterName.USER_LOGIN_SESSION_ATTRIBUTE, user.getLogin());
				
				page = JSPPageName.INDEX_PAGE;
				
			} else {
				request.setAttribute(RequestParameterName.RESULT_INFO, "Авторизация не выполнена");
				page = JSPPageName.LOGIN_PAGE;
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect(JSPPageName.ERROR_PAGE);
			}
			
		} catch (ServiceException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		}

	}

}
