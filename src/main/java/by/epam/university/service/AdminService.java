package by.epam.university.service;

import java.util.List;

import by.epam.university.entity.Application;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.TypeStudy;
import by.epam.university.service.exception.ServiceException;

public interface AdminService {

	List<Application> getAllUnconfirmedApplication() throws ServiceException;

	List<Application> getAllConfirmedApplication() throws ServiceException;

	List<Application> getAllApplication() throws ServiceException;
	
	boolean insertFaculty(Faculty faculty) throws ServiceException;
	
	boolean insertTypeStudy(TypeStudy typeStudy) throws ServiceException;
	
	boolean insertSchool(School school) throws ServiceException;
	
	boolean insertPrivilege(Privilege privilege) throws ServiceException;
	
}
