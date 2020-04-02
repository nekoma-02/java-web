package by.epamtc.task1.service;

import by.epamtc.task1.dao.DAOFactory;
import by.epamtc.task1.dao.SQLUserDao;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.User;
import by.epamtc.task1.service.exception.ServiceException;
import by.epamtc.task1.service.validator.impl.UserValidator;

public class UserServiceImpl implements UserService {

	@Override
	public boolean signIn(String login, String password) throws ServiceException {
		SQLUserDao dao = DAOFactory.getInstance().getDAO();

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
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean registration(User user) throws ServiceException {
		SQLUserDao dao = DAOFactory.getInstance().getDAO();

		UserValidator userValidator = new UserValidator();
		boolean isValidate = userValidator.validate(user.getLogin(), user.getPassword(), user.getEmail(),
				user.getName(), user.getSecondName(), user.getLastName());
		
		boolean flag = false;

		try {

			if (isValidate) {
				flag = dao.insert(user);
			} else {
				return false;
			}

			return flag;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}
	
	@Override
	public boolean isUserExist(String login) throws ServiceException  {
		SQLUserDao dao = DAOFactory.getInstance().getDAO();

		User user = null;
		
		UserValidator userValidator = new UserValidator();
		boolean isValidate = userValidator.validate(login);
		
		try {

			if (isValidate) {
			user = dao.getUserByLogin(login);
			} else {
				return false;
			}
			
			return user == null ? true : false;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
