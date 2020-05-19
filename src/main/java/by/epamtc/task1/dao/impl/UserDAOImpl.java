package by.epamtc.task1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epamtc.task1.dao.SQLUserDao;
import by.epamtc.task1.dao.connectionpool.ConnectionPool;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolException;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolManager;
import by.epamtc.task1.dao.exception.DAOConnectionPoolException;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.dao.exception.DAOSQLException;
import by.epamtc.task1.entity.Role;
import by.epamtc.task1.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class UserDAOImpl implements SQLUserDao {

	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();

	private static final String INSER_USER = "insert into users(name,secondName,lastName,login,password,email,roles_id_role) values (?,?,?,?,?,?,?)";
	private static final String SELECT_USER_BY_PASSWORD_LOGIN = "select id_user,name,secondName,lastName,login,password,email,roles.role_name from users inner join roles on users.roles_id_role = roles.id_role where login = ? and password = ?";
	private static final String SELECT_USER_BY_LOGIN = "select id_user,name,secondName,lastName,login,password,email,roles.role_name from users inner join roles on users.roles_id_role = roles.id_role where login = ?";
	private static final String SELECT_USER_BY_ID = "select id_user,name,secondName,lastName,login,password,email,roles.role_name from users inner join roles on users.roles_id_role = roles.id_role where id_user = ?";

	@Override
	public boolean insert(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		int nameInt = 1;
		int secondNameInt = 2;
		int lastNameInt = 3;
		int loginInt = 4;
		int passwordInt = 5;
		int emailInt = 6;
		int roleInt = 7;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSER_USER);

			ps.setString(nameInt, user.getName());
			ps.setString(secondNameInt, user.getSecondName());
			ps.setString(lastNameInt, user.getLastName());
			ps.setString(loginInt, user.getLogin());
			ps.setString(passwordInt, user.getPassword());
			ps.setString(emailInt, user.getEmail());
			ps.setInt(roleInt, user.getRole().ordinal()+1);

			return ps.executeUpdate() == 1;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOSQLException(e);
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
		int loginInt = 5;
		int passwordInt = 6;
		int emailInt = 7;
		int roleInt = 8;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_PASSWORD_LOGIN);

			ps.setString(1, login);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt(idInt), rs.getString(nameInt), rs.getString(secondNameInt),
						rs.getString(lastNameInt), rs.getString(loginInt), rs.getString(passwordInt),
						rs.getString(emailInt), Role.valueOf(rs.getString(roleInt).toUpperCase()));
			}

			return user;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e.getMessage());
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOSQLException(e.getMessage());
		} finally {
			closeConnection(connection, ps, rs);
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
		int loginInt = 5;
		int passwordInt = 6;
		int emailInt = 7;
		int roleInt = 8;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_LOGIN);

			ps.setString(1, login);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt(idInt), rs.getString(nameInt), rs.getString(secondNameInt),
						rs.getString(lastNameInt), rs.getString(loginInt), rs.getString(passwordInt),
						rs.getString(emailInt), Role.valueOf(rs.getString(roleInt).toUpperCase()));
			}

			return user;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
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
		int loginInt = 5;
		int passwordInt = 6;
		int emailInt = 7;
		int roleInt = 8;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_ID);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt(idInt), rs.getString(nameInt), rs.getString(secondNameInt),
						rs.getString(lastNameInt), rs.getString(loginInt), rs.getString(passwordInt),
						rs.getString(emailInt), Role.valueOf(rs.getString(roleInt).toUpperCase()));
			}

			return user;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
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
