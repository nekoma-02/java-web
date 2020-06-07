package by.epam.university.service;

import java.util.List;

import by.epam.university.entity.User;
import by.epam.university.service.exception.ServiceException;

public interface UserService {

	boolean signIn(String login, String password) throws ServiceException;

	boolean registration(User user) throws ServiceException;

	boolean isUserExist(String login) throws ServiceException;

	User userByLogin(String login) throws ServiceException;

	User userById(int id) throws ServiceException;
	
	List<User> getAllUser() throws ServiceException;
}
