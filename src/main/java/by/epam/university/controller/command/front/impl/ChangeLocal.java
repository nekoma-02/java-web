package by.epam.university.controller.command.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.parameter.SessionParameterName;

public class ChangeLocal implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String local = request.getParameter("local");
		session.setAttribute("local", local);

		String queryString = (String) session.getAttribute(SessionParameterName.QUERY_STRING);

		if (queryString != null) {
			response.sendRedirect(request.getContextPath() + "/Controller?" + queryString);
		} else {
			response.sendRedirect(request.getContextPath());
		}

	}

}
