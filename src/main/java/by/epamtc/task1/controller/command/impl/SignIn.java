package by.epamtc.task1.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epamtc.task1.controller.JSPPageName;
import by.epamtc.task1.controller.RequestParameterName;
import by.epamtc.task1.controller.command.Command;
import by.epamtc.task1.service.ServiceFactory;
import by.epamtc.task1.service.UserService;
import by.epamtc.task1.service.exception.ServiceEmptyValuesException;
import by.epamtc.task1.service.exception.ServiceException;
import by.epamtc.task1.service.exception.ServiceInvalidLoginException;
import by.epamtc.task1.service.exception.ServiceInvalidPasswordException;

public class SignIn implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		UserService service = ServiceFactory.getInstance().getService();
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		
		try {
				
			
			service.signIn(login, password);
			page = JSPPageName.USER_PAGE;		
			request.setAttribute(RequestParameterName.RESULT_INFO, "hello " + login);
			

		} catch (ServiceEmptyValuesException | ServiceInvalidLoginException | ServiceInvalidPasswordException e) {

			request.setAttribute(RequestParameterName.RESULT_INFO, e.getMessage());
			page = JSPPageName.INDEX_PAGE;

		} catch (ServiceException e) {

			request.setAttribute(RequestParameterName.RESULT_INFO, e.getMessage());
			page = JSPPageName.ERROR_PAGE;

		}

		return page;
	}

}
