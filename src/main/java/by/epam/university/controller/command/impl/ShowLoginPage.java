package by.epam.university.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.university.controller.JSPPageName;
import by.epam.university.controller.command.Command;

public class ShowLoginPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardTo(request, response, JSPPageName.LOGIN_PAGE);
		
	}

}
