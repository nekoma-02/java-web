package by.epam.university.controller.command.front.impl;

import java.io.IOException;
import java.util.List;

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
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class ShowUpdateSpecialtyPage implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationService service = ServiceFactory.getInstance().getApplicationService();
		HttpSession session = request.getSession();
		String id =  request.getParameter(RequestParameterName.SPECIALTY_ID);
		
		try {
			
			Specialty specialty = service.getSpecialtyById(Integer.parseInt(id));
			List<Faculty> faculties = service.getAllFaculties();
			List<TypeStudy> typeStudies = service.getAllTypesStudy();

			request.setAttribute(RequestParameterName.SPECIALTY, specialty);
			request.setAttribute(RequestParameterName.FACULTY, faculties);
			request.setAttribute(RequestParameterName.TYPE_STUDY, typeStudies);

			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());
			forwardTo(request, response, JSPPageName.UPDATE_SPECIALTY);

		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		}
		
	}

}
