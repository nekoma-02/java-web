package by.epamtc.task1.dao;

import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.Application;
import by.epamtc.task1.entity.Hostel;
import by.epamtc.task1.entity.Privilege;
import by.epamtc.task1.entity.School;

public interface SQLApplicationDao {

	Application applicationByUserId(int userId) throws DAOException;

	boolean insertApplication(Application application, Hostel hostel, Privilege privelege, School school)
			throws DAOException;

	boolean updateApplication(Application application) throws DAOException;
}