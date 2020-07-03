package by.epam.university.dao;

import java.util.List;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Application;

public interface SQLApplicationDao {

	Application applicationByUserId(int userId) throws DAOException;

	boolean insertApplication(Application application) throws DAOException;

	boolean updateApplication(Application application) throws DAOException;
	
	List<Application> getAllUnconfirmedApplication() throws DAOException;
	
	List<Application> getAllConfirmedApplication() throws DAOException;
	
	List<Application> getAllApplication() throws DAOException;
}