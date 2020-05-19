package by.epamtc.task1.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.task1.controller.JSPPageName;
import by.epamtc.task1.controller.command.Command;
import by.epamtc.task1.entity.Specialty;
import by.epamtc.task1.service.ApplicationService;
import by.epamtc.task1.service.ServiceFactory;
import by.epamtc.task1.service.exception.ServiceException;

public class ShowSpecialty implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ApplicationService service = ServiceFactory.getInstance().getApplicationService();

		try {

			List<Specialty> specialties = service.getAllSpecialties();
			request.setAttribute("specialties", specialties);
			forwardTo(request, response, JSPPageName.INFO_PAGE);
			

		} catch (ServiceException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		}
		
	}

}
