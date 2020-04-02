package by.epamtc.task1.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.task1.controller.JSPPageName;
import by.epamtc.task1.controller.SessionParameterName;
import by.epamtc.task1.controller.command.Command;

public class SignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        session.removeAttribute(SessionParameterName.USER_ID_SESSION_ATTRIBUTE);
        session.removeAttribute(SessionParameterName.USER_LOGIN_SESSION_ATTRIBUTE);
        response.sendRedirect(JSPPageName.INDEX_PAGE);
		
	}

}
