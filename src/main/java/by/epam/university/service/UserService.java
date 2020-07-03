package by.epam.university.service;

import java.util.List;

import by.epam.university.entity.User;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.UserExistsException;

public interface UserService {

	boolean signIn(String login, String password) throws ServiceException;

	boolean registration(User user) throws ServiceException, UserExistsException;

	User userByLogin(String login) throws ServiceException;

	User userById(int id) throws ServiceException;
	
	List<User> getAllUser() throws ServiceException;
	
	boolean updateUser(User user) throws ServiceException;
	
}
