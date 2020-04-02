package by.epamtc.task1.dao;

import by.epamtc.task1.dao.impl.SpecialtyDAOImpl;
import by.epamtc.task1.dao.impl.UserDAOImpl;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public SQLUserDao getUserDAO() {
		return new UserDAOImpl();
	}
	
	public SQLSpecialtyDao getSpecialtyDAO() {
		return new SpecialtyDAOImpl();
	}
}
