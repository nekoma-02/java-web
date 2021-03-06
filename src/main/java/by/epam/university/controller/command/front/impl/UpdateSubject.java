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
import by.epam.university.entity.Subject;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.SubjectExistsException;
import by.epam.university.service.validator.SubjectValidator;
import by.epam.university.service.validator.factory.ValidatorFactory;

public class UpdateSubject implements Command {

	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = ServiceFactory.getInstance().getAdminService();
		HttpSession session = request.getSession();
		String id = request.getParameter(RequestParameterName.SUBJECT_ID);
		String subName = request.getParameter(RequestParameterName.SUBJECT_NAME);

		SubjectValidator validator = ValidatorFactory.getInstance().getSubjectValidator();
		List<String> validation = validator.validate(subName);

		try {

			if (validation.size() != 0) {

				Subject subject = new Subject(Integer.parseInt(id), subName);

				boolean isUpdate = service.updateSubject(subject);
				if (isUpdate) {
					response.sendRedirect(JSPPageName.UPDATE_SUBJECT);
				} else {
					request.setAttribute(RequestParameterName.RESULT_INFO, "wrong update");
					forwardTo(request, response, JSPPageName.UPDATE_SUBJECT);
				}
			} else {

				for (String item : validation) {
					request.setAttribute(item.toLowerCase(), item);
				}

				forwardTo(request, response, JSPPageName.UPDATE_SUBJECT);
			}

			session.setAttribute(SessionParameterName.QUERY_STRING, request.getQueryString());

		} catch (ServiceException | ForwardException e) {

			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);

		} catch (SubjectExistsException e) {
			request.setAttribute(RequestParameterName.RESULT_INFO, "such subject already exist! ");
			try {
				forwardTo(request, response, JSPPageName.UPDATE_SUBJECT);
			} catch (ForwardException ex) {
				logger.log(Level.ERROR, ex);
				response.sendRedirect(JSPPageName.ERROR_PAGE);
			}
		}

	}

}
