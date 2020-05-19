package by.epamtc.task1.dao;

import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.Application;

public interface SQLApplicationDao {

	Application applicationByUserId(int userId) throws DAOException;

	boolean insertApplication(Application application) throws DAOException;

	boolean updateApplication(Application application) throws DAOException;
}