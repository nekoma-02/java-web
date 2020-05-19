package by.epamtc.task1.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.task1.controller.JSPPageName;
import by.epamtc.task1.controller.command.Command;

public class ShowRegistrPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardTo(request, response, JSPPageName.REGISTRATION_PAGE);
	}

}
