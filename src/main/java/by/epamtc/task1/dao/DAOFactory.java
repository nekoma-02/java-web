package by.epamtc.task1.dao;

import by.epamtc.task1.entity.User;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public SQLDao<User> getDAO() {
		return new UserDAOImpl();
	}
}
