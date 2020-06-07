package by.epam.university.controller.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.university.controller.JSPPageName;


public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	default void forwardTo(HttpServletRequest request, HttpServletResponse response, String page) throws IOException{

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);

        try {
            if (requestDispatcher != null) {
                requestDispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
        	response.sendRedirect(JSPPageName.ERROR_PAGE);
        }

    }

}
