package by.epam.university.dao.connectionpool;

public class ConnectionPoolManager {
	private static final ConnectionPoolManager instance = new ConnectionPoolManager();
	private static ConnectionPool connectionPool = new ConnectionPool();
	
	public static ConnectionPoolManager getInstance() {
		return instance;
	}
	
	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}

}
