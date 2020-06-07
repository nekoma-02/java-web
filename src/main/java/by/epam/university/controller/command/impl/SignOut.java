package by.epam.university.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.university.controller.JSPPageName;
import by.epam.university.controller.SessionParameterName;
import by.epam.university.controller.command.Command;

public class SignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        session.removeAttribute(SessionParameterName.USER_ID);
        session.removeAttribute(SessionParameterName.USER_LOGIN);
        response.sendRedirect(JSPPageName.INDEX_PAGE);
		
	}

}
