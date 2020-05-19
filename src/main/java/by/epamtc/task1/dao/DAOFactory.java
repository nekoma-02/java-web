package by.epamtc.task1.dao;

import by.epamtc.task1.dao.impl.ApplicationDAOImpl;
import by.epamtc.task1.dao.impl.PrivilegeDAOImpl;
import by.epamtc.task1.dao.impl.SchoolDAOImpl;
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
	
	public SQLApplicationDao getApplicationDAO() {
		return new ApplicationDAOImpl();
	}
	
	public SQLSchoolDao getSchoolDAO() {
		return new SchoolDAOImpl();
	}
	
	public SQLPrivilegeDao getPrivilegeDAO() {
		return new PrivilegeDAOImpl();
	}
	

}
