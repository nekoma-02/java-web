package by.epam.jwd_university_project.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import by.epam.university.dao.connectionpool.ConnectionPool;
import by.epam.university.dao.connectionpool.ConnectionPoolException;
import by.epam.university.dao.connectionpool.ConnectionPoolManager;

public class DataBaseCreation {

	private ConnectionPool pool;
	private Connection connection;
	
	@BeforeClass
	public void initConnectionPool() throws ConnectionPoolException, SQLException {
		ConnectionPoolManager.getInstance().getConnectionPool().initPoolData();
		pool = ConnectionPoolManager.getInstance().getConnectionPool();
		
		
	}
	
	@Before
	public void takeConnection() throws ConnectionPoolException, SQLException {
		connection = pool.takeConnection();
		
		
	}
	
	@After
	public void closeConnection() throws SQLException {
		pool.releaseConnection(connection);
	}
	
	@AfterClass
	public void disposeConnectionPool() throws SQLException {
		pool.dispose();
	}
	
	
}
