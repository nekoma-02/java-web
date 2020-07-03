package by.epam.university.controller.command.ajax.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.epam.university.controller.command.ajax.AjaxCommand;
import by.epam.university.controller.parameter.RequestParameterName;
import by.epam.university.entity.Specialty;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;

public class GetSpecialty implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int idTypeStudy = Integer.parseInt(request.getParameter(RequestParameterName.TYPE_STUDY));
		int idFaculty = Integer.parseInt(request.getParameter(RequestParameterName.FACULTY));
		
		String answer = null;
		
		Gson gson = new Gson();
		ApplicationService appService = ServiceFactory.getInstance().getApplicationService();
		
		try {
			List<Specialty> listSpec = appService.getSpecialtyByTypeStudyAndFaculty(idTypeStudy, idFaculty);
			answer = gson.toJson(listSpec);
		} catch (ServiceException e) {
			response.setStatus(500);
		}
		
		return answer;
	}

}
