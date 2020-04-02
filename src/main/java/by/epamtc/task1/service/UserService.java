package by.epamtc.task1.service;

import by.epamtc.task1.entity.User;
import by.epamtc.task1.service.exception.ServiceException;

public interface UserService {

	boolean signIn(String login, String password) throws ServiceException;

	boolean registration(User user) throws ServiceException;

	boolean isUserExist(String login) throws ServiceException;

	User userByLogin(String login) throws ServiceException;

	User userById(int id) throws ServiceException;
}
