package by.epamtc.task1.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.task1.controller.command.Command;

public class ChangeLocal implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String local = request.getParameter("local");
		session.setAttribute("local", local);

		String command = (String) session.getAttribute("command");

		if (command != null) {
			response.sendRedirect(request.getContextPath() + "/Controller?command=" + command);
		} else {
			response.sendRedirect(request.getContextPath());
		}

	}

}
