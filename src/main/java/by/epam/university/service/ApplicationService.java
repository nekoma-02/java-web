package by.epam.university.service;

import java.util.List;

import by.epam.university.entity.Application;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.TypeStudy;
import by.epam.university.service.exception.ServiceException;

public interface ApplicationService {

	Application ApplicationByUserId(int id) throws ServiceException;

	boolean createApplication(Application application) throws ServiceException;
	
	boolean isExistUserApplication(int userId) throws ServiceException;
	
	boolean updateApplication(Application application) throws ServiceException;
	
	List<Specialty> getAllSpecialties() throws ServiceException;
	
	List<School> getAllSchools() throws ServiceException;
	
	List<Privilege> getAllPrivileges() throws ServiceException;
	
	School getSchoolById(int id) throws ServiceException;
	
	Privilege getPrivilegeById(int id) throws ServiceException;
	
	Specialty getSpecialtyById(int id) throws ServiceException;
	
	List<Specialty> getSpecialtyByTypeStudyAndFaculty(int idTypeStudy, int idFaculty) throws ServiceException;
	
	List<Faculty> getAllFaculties() throws ServiceException;
	
	List<TypeStudy> getAllTypesStudy() throws ServiceException;
	

	
}
