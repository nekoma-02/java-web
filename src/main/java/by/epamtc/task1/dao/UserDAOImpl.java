package by.epamtc.task1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epamtc.task1.dao.connectionpool.ConnectionPool;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolException;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolManager;
import by.epamtc.task1.dao.exception.DAOConnectionPoolException;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.dao.exception.DAOSQLException;
import by.epamtc.task1.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class UserDAOImpl implements SQLUserDao {
	
	private static Logger logger = LogManager.getLogger();	
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();

	private static final String INSER_USER = "insert into users(name,secondName,lastName,status,login,password,email,roles_id_role) values (?,?,?,?,?,?,?,1)";
	private static final String SELECT_USER_BY_PASSWORD_LOGIN = "select * from users where login = ? and password = ?";
	private static final String SELECT_USER_BY_LOGIN = "select * from users where login = ?";
	private static final String SELECT_USER_BY_ID = "select * from users where id_user = ?";
	
	@Override
	public boolean insert(User field) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		int nameInt = 1;
		int secondNameInt = 2;
		int lastNameInt = 3;
		int statusInt = 4;
		int loginInt = 5;
		int passwordInt = 6;
		int emailInt = 7;
		
		
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSER_USER);

			ps.setString(nameInt, field.getName());
			ps.setString(secondNameInt, field.getSecondName());
			ps.setString(lastNameInt, field.getLastName());
			ps.setBoolean(statusInt, field.isStatus());
			ps.setString(loginInt, field.getLogin());
			ps.setString(passwordInt, field.getPassword());
			ps.setString(emailInt, field.getEmail());

			return ps.executeUpdate() == 1;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOSQLException(e.getMessage());
		} finally {
			closeConnection(connection, ps);
		}
	
	}

	@Override
	public User getUserByLoginPassword(String login, String password) throws DAOException {
		User user = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int idInt = 1;
		int nameInt = 2;
		int secondNameInt = 3;
		int lastNameInt = 4;
		int statusInt = 5;
		int loginInt = 6;
		int passwordInt = 7;
		int emailInt = 8;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_PASSWORD_LOGIN);
			
			ps.setString(1, login);
			ps.setString(2, password);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt(idInt),rs.getString(nameInt),rs.getString(secondNameInt),rs.getString(lastNameInt),rs.getString(loginInt),rs.getString(passwordInt),rs.getBoolean(statusInt),rs.getString(emailInt),1);
			}
			
			return user;
			
		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOSQLException(e.getMessage());
		} finally {
			closeConnection(connection, ps,rs);
		}
	}


	@Override
	public User getUserByLogin(String login) throws DAOException {
		User user = null;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int idInt = 1;
		int nameInt = 2;
		int secondNameInt = 3;
		int lastNameInt = 4;
		int statusInt = 5;
		int loginInt = 6;
		int passwordInt = 7;
		int emailInt = 8;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_LOGIN);
			
			ps.setString(1, login);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt(idInt),rs.getString(nameInt),rs.getString(secondNameInt),rs.getString(lastNameInt),rs.getString(loginInt),rs.getString(passwordInt),rs.getBoolean(statusInt),rs.getString(emailInt),1);
			}
			
			return user;
			
		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOSQLException(e.getMessage());
		} finally {
			closeConnection(connection, ps,rs);
		}
	}

	@Override
	public User getUserById(int id) throws DAOException {
		User user = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int idInt = 1;
		int nameInt = 2;
		int secondNameInt = 3;
		int lastNameInt = 4;
		int statusInt = 5;
		int loginInt = 6;
		int passwordInt = 7;
		int emailInt = 8;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_ID);
			
			ps.setInt(1, id);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt(idInt),rs.getString(nameInt),rs.getString(secondNameInt),rs.getString(lastNameInt),rs.getString(loginInt),rs.getString(passwordInt),rs.getBoolean(statusInt),rs.getString(emailInt),1);
			}
			
			return user;
			
		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOSQLException(e.getMessage());
		} finally {
			closeConnection(connection, ps,rs);
		}
	}
	
	private void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
			 logger.log(Level.ERROR, "Connection isn't closed.");
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			 logger.log(Level.ERROR, "ResultSet isn't closed.");
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			 logger.log(Level.ERROR, "Statement isn't closed.");
		}
		
	}

	private void closeConnection(Connection con, Statement st) {
		try {
			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
			 logger.log(Level.ERROR, "Connection isn't closed.");
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Statement isn't closed.");
		}
		
	}


}
