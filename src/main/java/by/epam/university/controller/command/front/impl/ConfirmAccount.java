package by.epam.university.controller.command.front.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.command.front.ForwardException;
import by.epam.university.controller.parameter.JSPPageName;
import by.epam.university.controller.parameter.RequestParameterName;
import by.epam.university.controller.parameter.SessionParameterName;
import by.epam.university.entity.ExamMark;
import by.epam.university.service.AdminService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class ConfirmAccount implements Command {

	private static final String ADMIN_PAGE = "/Controller?command=admin_page";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		HttpSession session = request.getSession();

		int idApplication = (Integer) session.getAttribute(SessionParameterName.APPLICATION_ID);
		int idSpecialty = Integer.parseInt(request.getParameter(RequestParameterName.SPECIALTY_ID));

		String[] idSubjectArray = request.getParameterValues(RequestParameterName.SUBJECT_ID);
		String[] mark = request.getParameterValues(RequestParameterName.MARK);

		try {

			List<ExamMark> examMark = new ArrayList<ExamMark>();

			int index = 0;
			if (idSubjectArray.length == mark.length) {
				for (String item : idSubjectArray) {
					int idSubject = adminService.getIdbySubjectAndSpecialty(Integer.parseInt(item), idSpecialty);
					examMark.add(new ExamMark(idSubject, Integer.parseInt(mark[index])));
					index++;
				}
			} else {
				request.setAttribute(RequestParameterName.RESULT_INFO, "not all marks are filled");
				forwardTo(request, response, JSPPageName.USER_PAGE);
			}

			boolean isAddMark = false;
			List<ExamMark> isExistMark = adminService.getAllMarksByApplication(idApplication);
			for (ExamMark item : examMark) {
				
				if (isExistMark == null || isExistMark.size() == 0) {
					isAddMark = adminService.addMark(idApplication, item.getMark(), item.getIdSubject());
				} else {
					isAddMark = adminService.updateMark(idApplication, item.getMark(), item.getIdSubject());
				}
				
				if (!isAddMark) {
					request.setAttribute(RequestParameterName.RESULT_INFO, "failed to add mark");
					forwardTo(request, response, JSPPageName.USER_PAGE);
				}
			}

			adminService.confirmApplication(idApplication);
			response.sendRedirect(request.getContextPath()+ADMIN_PAGE);
			
		} catch (ServiceException | ForwardException e) {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}

	}

}
