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
import by.epam.university.entity.Specialty;
import by.epam.university.entity.TypeStudy;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class AddSpecialty implements Command {
	
private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = ServiceFactory.getInstance().getAdminService();
		HttpSession session = request.getSession();
		String name = request.getParameter(RequestParameterName.SPECIALTY);
		String plan = request.getParameter(RequestParameterName.SPECIALTY_PLAN);
		String year = request.getParameter(RequestParameterName.SPECIALTY_YEAR);
		String idTypeStudy = request.getParameter(RequestParameterName.TYPE_STUDY);
		String idFaculty = request.getParameter(RequestParameterName.FACULTY);
		
		try {
			
			Specialty specialty = new Specialty(0, name, Integer.parseInt(plan), Integer.parseInt(year), new TypeStudy(Integer.parseInt(idTypeStudy)), new Faculty(Integer.parseInt(idFaculty)));

			boolean isUpdate = service.insertSpecialty(specialty);
			if (isUpdate) {
				response.sendRedirect(JSPPageName.ADD_SPECIALTY);
			} else {
				request.setAttribute(RequestParameterName.RESULT_INFO, "wrong update");
				forwardTo(request, response, JSPPageName.ADD_SPECIALTY);
			}
			

			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());

		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		}
		
		
	}

}
