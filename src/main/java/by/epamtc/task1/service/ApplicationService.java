package by.epamtc.task1.service;

import java.util.List;

import by.epamtc.task1.entity.Application;
import by.epamtc.task1.entity.Privilege;
import by.epamtc.task1.entity.School;
import by.epamtc.task1.entity.Specialty;
import by.epamtc.task1.service.exception.ServiceException;

public interface ApplicationService {

	Application ApplicationById(int id) throws ServiceException;

	boolean createApplication(Application application) throws ServiceException;
	
	List<Specialty> getAllSpecialties() throws ServiceException;
	
	List<School> getAllSchools() throws ServiceException;
	
	List<Privilege> getAllPrivileges() throws ServiceException;
	
}
