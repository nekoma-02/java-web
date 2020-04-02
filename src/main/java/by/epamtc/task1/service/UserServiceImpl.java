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

		try {
			
			User user = dao.getUserByLoginPassword(login, password);

			return user != null ? true : false;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public boolean registration(User user) throws ServiceException {
		SQLUserDao dao = DAOFactory.getInstance().getDAO();

		UserValidator userValidator = new UserValidator(user);
		boolean isValidate = userValidator.validate();
		boolean flag = false;
		
		try {
			
			if (isValidate) {
				flag = dao.insert(user);
			} else {
				return false;
			}
			
			return flag;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}
	



}
