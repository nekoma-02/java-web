package by.epam.university.controller.command.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.parameter.JSPPageName;
import by.epam.university.controller.parameter.RequestParameterName;
import by.epam.university.entity.School;
import by.epam.university.entity.TypeStudy;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class AddTypeStudy implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		String name = request.getParameter(RequestParameterName.TYPE_STUDY_NAME);

		TypeStudy typeStudy = new TypeStudy(0, name);

		try {
			adminService.insertTypeStudy(typeStudy);
			response.sendRedirect(request.getContextPath());
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}

	}

}
