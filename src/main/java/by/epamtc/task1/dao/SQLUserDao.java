package by.epamtc.task1.dao;

import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.User;

public interface SQLUserDao {

	boolean insert(User user) throws DAOException;
	User getUserByLoginPassword(String login, String password) throws DAOException;
	User getUserByLogin(String login) throws DAOException;
	User getUserById(int id) throws DAOException;

}
