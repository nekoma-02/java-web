package by.epamtc.task1.dao;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public SQLUserDao getDAO() {
		return new UserDAOImpl();
	}
}
