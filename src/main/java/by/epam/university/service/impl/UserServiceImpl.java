package by.epam.university.service.impl;

import java.util.List;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SQLUserDao;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.User;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.UserExistsException;

public class UserServiceImpl implements UserService {

	
	@Override
	public boolean signIn(String login, String password) throws ServiceException {
		SQLUserDao dao = DAOFactory.getInstance().getUserDAO();

		User user = null;
		
		
		try {
			user = dao.getUserByLoginPassword(login, password);
			
			return user != null ? true : false;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean registration(User user) throws ServiceException, UserExistsException {
		SQLUserDao dao = DAOFactory.getInstance().getUserDAO();

		try {
			
			User userByLogin = dao.getUserByLogin(user.getLogin());
			boolean isInsert;
			
			if (userByLogin == null) {
				isInsert = dao.insert(user);
			} else {
				throw new UserExistsException("user already exists!");
			}
			
			return isInsert;
		} catch (DAOException e) {
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
		
		try {
			return dao.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public boolean updateUser(User user) throws ServiceException {
		SQLUserDao dao = DAOFactory.getInstance().getUserDAO();
		
		try {
			return dao.updateUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}




}
