package by.epam.university.service;

import java.util.List;

import by.epam.university.entity.Application;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.Subject;
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
	
	boolean insertSpecialty(Specialty specialty) throws ServiceException;
	
	boolean updateFaculty(Faculty faculty) throws ServiceException;
	
	boolean updateTypeStudy(TypeStudy typeStudy) throws ServiceException;
	
	boolean updateSchool(School school) throws ServiceException;
	
	boolean updatePrivilege(Privilege privilege) throws ServiceException;
	
	boolean updateSpecialty(Specialty specialty) throws ServiceException;
	
	Faculty getFacultyById(int id) throws ServiceException;
	
	List<Subject> getSubjectBySpecialtyId(int id) throws ServiceException;
	
	TypeStudy getTypeStudyById(int id) throws ServiceException;
	
	
}
