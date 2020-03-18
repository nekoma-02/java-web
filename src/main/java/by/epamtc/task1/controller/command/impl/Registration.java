package by.epamtc.task1.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epamtc.task1.controller.JSPPageName;
import by.epamtc.task1.controller.RequestParameterName;
import by.epamtc.task1.controller.command.Command;
import by.epamtc.task1.entity.User;
import by.epamtc.task1.service.ServiceFactory;
import by.epamtc.task1.service.UserService;
import by.epamtc.task1.service.exception.ServiceException;
import by.epamtc.task1.service.exception.ServiceInvalidLoginException;
import by.epamtc.task1.service.exception.ServiceInvalidPasswordException;
import by.epamtc.task1.service.exception.ServiceUserExistException;

public class Registration implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		UserService service = ServiceFactory.getInstance().getService();

		String name = request.getParameter(RequestParameterName.NAME);
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);

		HttpSession session = request.getSession();
	
		User user = new User(name, login, password);
		
		
		try {

			service.registration(user);
			session.setAttribute("current_user", user);
			
			page = JSPPageName.INDEX_PAGE;

		} catch (ServiceUserExistException | ServiceInvalidPasswordException | ServiceInvalidLoginException e) {
			
			request.setAttribute(RequestParameterName.RESULT_INFO, e.getMessage());
			page = JSPPageName.REGISTRATION;
			
		} catch (ServiceException e) {

			request.setAttribute(RequestParameterName.RESULT_INFO, e.getMessage());
			page = JSPPageName.ERROR_PAGE;

		}

		return page;
	}

}
