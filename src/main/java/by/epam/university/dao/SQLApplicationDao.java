package by.epam.university.dao;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Application;

public interface SQLApplicationDao {

	Application applicationByUserId(int userId) throws DAOException;

	boolean insertApplication(Application application) throws DAOException;

	boolean updateApplication(Application application) throws DAOException;
}