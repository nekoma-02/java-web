package by.epam.university.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SQLUserDao;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.User;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.validator.impl.UserValidator;

public class UserServiceImpl implements UserService {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public boolean signIn(String login, String password) throws ServiceException {
		SQLUserDao dao = DAOFactory.getInstance().getUserDAO();

		User user = null;
		
		UserValidator userValidator = new UserValidator();
		boolean isValidate = userValidator.validate(login,password);
		
		try {

			if (isValidate) {
			user = dao.getUserByLoginPassword(login, password);
			} else {
				return false;
			}
			
			return user != null ? true : false;
		} catch (DAOException e) {
			logger.log(Level.ERROR, e);
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean registration(User user) throws ServiceException {
		SQLUserDao dao = DAOFactory.getInstance().getUserDAO();

		UserValidator userValidator = new UserValidator();
		boolean isValidate = userValidator.validate(user.getLogin(), user.getPassword(), user.getEmail(),
				user.getName(), user.getSecondName(), user.getLastName());
		
		boolean isInsert = false;

		try {

			if (isValidate) {
				isInsert = dao.insert(user);
			} else {
				return false;
			}

			return isInsert;
		} catch (DAOException e) {
			logger.log(Level.ERROR, e);
			throw new ServiceException(e);
		}

	}
	
	@Override
	public boolean isUserExist(String login) throws ServiceException  {
		SQLUserDao dao = DAOFactory.getInstance().getUserDAO();

		User user = null;
		
		try {

			user = dao.getUserByLogin(login);
			
			return user == null ? true : false;
		} catch (DAOException e) {
			logger.log(Level.ERROR, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public User userByLogin(String login) throws ServiceException {
		SQLUserDao dao = DAOFactory.getInstance().getUserDAO();

		User user = null;
		
		try {

			user = dao.getUserByLogin(login);
			
			return user;
		} catch (DAOException e) {
			logger.log(Level.ERROR, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public User userById(int id) throws ServiceException {
		SQLUserDao dao = DAOFactory.getInstance().getUserDAO();

		User user = null;
		
		try {

			user = dao.getUserById(id);
			
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getAllUser() throws ServiceException {
		SQLUserDao dao = DAOFactory.getInstance().getUserDAO();
		List<User> userList = null;
		try {
			userList = dao.getAll();
			return userList;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

}
