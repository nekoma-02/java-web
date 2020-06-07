package by.epam.university.service;

import java.util.List;

import by.epam.university.entity.Application;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.service.exception.ServiceException;

public interface ApplicationService {

	Application ApplicationByUserId(int id) throws ServiceException;

	boolean createApplication(Application application) throws ServiceException;
	
	List<Specialty> getAllSpecialties() throws ServiceException;
	
	List<School> getAllSchools() throws ServiceException;
	
	List<Privilege> getAllPrivileges() throws ServiceException;
	
	boolean isExistUserApplication(int userId) throws ServiceException;
	
}
