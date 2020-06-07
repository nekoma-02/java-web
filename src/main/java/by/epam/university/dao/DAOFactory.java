package by.epam.university.dao;

import by.epam.university.dao.impl.ApplicationDAOImpl;
import by.epam.university.dao.impl.PrivilegeDAOImpl;
import by.epam.university.dao.impl.SchoolDAOImpl;
import by.epam.university.dao.impl.SpecialtyDAOImpl;
import by.epam.university.dao.impl.UserDAOImpl;

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
