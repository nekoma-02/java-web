package by.epamtc.task1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import by.epamtc.task1.dao.connectionpool.ConnectionPool;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolException;
import by.epamtc.task1.dao.exception.DAOConnectionPoolException;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.dao.exception.DAOSQLException;
import by.epamtc.task1.entity.User;

public class UserDAOImpl implements SQLDao<User> {

	private static final String INSER_USER = "insert into users(name_user,login,password_user) values (?,?,?)";
	private static final String SELECT_USERS = "select * from users";
	@Override
	public int insert(User field) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement ps = null;

		int name = 1;
		int login = 2;
		int password = 3;
		
		try {
			connectionPool.initPoolData();
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSER_USER);

			ps.setString(name, field.getName());
			ps.setString(login, field.getLogin());
			ps.setString(password, field.getPassword());

			return ps.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOSQLException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, ps);
		}
	}

	@Override
	public User getValue(String key) throws DAOException {
		User user = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from users where login = ?";
		
		int name = 2;
		int login = 3;
		int password = 4;

		try {
			connectionPool.initPoolData();
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(query);
			
			ps.setString(1, key);

			rs = ps.executeQuery();
			
			while (rs.next()) {
				user = new User(rs.getString(name), rs.getString(login), rs.getString(password));
			}
			return user;
			
		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOSQLException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, ps,rs);
		}
	}

	@SuppressWarnings("null")
	@Override
	public List<User> getAll() throws DAOException {
		List<User> users = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		int name = 2;
		int login = 3;
		int password = 4;
		
		try {
			connectionPool.initPoolData();
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();

			rs = statement.executeQuery(SELECT_USERS);
			
			while (rs.next()) {
				users.add(new User(rs.getString(name), rs.getString(login), rs.getString(password)));
			}
			
			return users;
			
		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOSQLException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, statement, rs);;
		}
	}



}
