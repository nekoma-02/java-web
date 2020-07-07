package by.epam.university.controller.command.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.command.front.ForwardException;
import by.epam.university.controller.parameter.JSPPageName;
import by.epam.university.controller.parameter.RequestParameterName;
import by.epam.university.controller.parameter.SessionParameterName;
import by.epam.university.entity.Faculty;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class UpdateFaculty implements Command {

	private static Logger logger = LogManager.getLogger();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = ServiceFactory.getInstance().getAdminService();
		HttpSession session = request.getSession();
		String id =  request.getParameter(RequestParameterName.FACULTY_ID);
		String facultyName = request.getParameter(RequestParameterName.FACULTY_NAME);
		
		try {
			
			Faculty faculty = new Faculty(Integer.parseInt(id), facultyName);

			boolean isUpdate = service.updateFaculty(faculty);
			if (isUpdate) {
				response.sendRedirect(JSPPageName.UPDATE_FACULTY);
			} else {
				request.setAttribute(RequestParameterName.RESULT_INFO, "wrong update");
				forwardTo(request, response, JSPPageName.UPDATE_FACULTY);
			}
			

			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());

		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		}
		
	}

}
