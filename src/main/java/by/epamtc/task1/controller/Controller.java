package by.epamtc.task1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.task1.controller.command.Command;
import by.epamtc.task1.controller.command.CommandProvided;
import by.epamtc.task1.entity.User;



public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(RequestParameterName.COMMAND);
		Command command = CommandProvided.getInstance().getCommand(commandName);
		
		HttpSession session = request.getSession();
		
		String page = command.execute(request);
		
		User user = (User) session.getAttribute("current_user");
		
		if (user != null) {
			request.setAttribute(RequestParameterName.LOGIN, user.getLogin());
			request.setAttribute(RequestParameterName.PASSWORD, user.getPassword());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			response.setContentType("text/html");
			response.getWriter().println("e r r o r");
		}
	}

}
