package by.epamtc.task1.service;

import by.epamtc.task1.dao.DAOFactory;
import by.epamtc.task1.dao.SQLDao;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.User;
import by.epamtc.task1.service.exception.ServiceEmptyValuesException;
import by.epamtc.task1.service.exception.ServiceException;
import by.epamtc.task1.service.exception.ServiceInvalidLoginException;
import by.epamtc.task1.service.exception.ServiceInvalidPasswordException;
import by.epamtc.task1.service.exception.ServiceUserExistException;
import by.epamtc.task1.service.validator.LoginValidator;
import by.epamtc.task1.service.validator.PasswordValidator;

public class UserServiceImpl implements UserService {

	@Override
	public boolean signIn(String login, String password) throws ServiceException {
		SQLDao<User> dao = DAOFactory.getInstance().getDAO();

		boolean flag = false;

		try {
			User user = dao.getValue(login);

			if (login.isEmpty() || password.isEmpty()) {
				throw new ServiceEmptyValuesException("fill in the empty fields");
			}

			if (user == null || !login.equals(user.getLogin())) {
				throw new ServiceInvalidLoginException("login is incorrect!");
			}

			if (!password.equals(user.getPassword())) {
				throw new ServiceInvalidPasswordException("password is incorrect!");
			}

			flag = true;

			return flag;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public boolean registration(User user) throws ServiceException {
		SQLDao<User> dao = DAOFactory.getInstance().getDAO();

		String name = user.getName();
		String login = user.getLogin();
		String password = user.getPassword();
		int flag = 0;

		if (!LoginValidator.validateLogin(login)) {
			throw new ServiceInvalidLoginException("login is incorrect! ");
		}
		
		if (!PasswordValidator.validatePassword(password)) {
			throw new ServiceInvalidPasswordException("password is incorrect! ");
		}
		
		try {
			if (isUserExist(login)) {
				throw new ServiceUserExistException("User " + login + " exist!");
			}

			flag = dao.insert(new User(name, login, password));

			return flag > 0 ? true : false;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	private boolean isUserExist(String login) throws DAOException {
		SQLDao<User> dao = DAOFactory.getInstance().getDAO();
		User user = dao.getValue(login);
		
		return user != null ? true : false;

	}

}
