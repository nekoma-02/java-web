package by.epamtc.task1.controller.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	default void forwardTo(HttpServletRequest request, HttpServletResponse response, String page){

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);

        try {
            if (requestDispatcher != null) {
                requestDispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            //throw new 
        }

    }

}
