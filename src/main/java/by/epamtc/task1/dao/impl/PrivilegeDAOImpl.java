package by.epamtc.task1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.task1.dao.SQLPrivilegeDao;
import by.epamtc.task1.dao.connectionpool.ConnectionPool;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolException;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolManager;
import by.epamtc.task1.dao.exception.DAOConnectionPoolException;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.dao.exception.DAOSQLException;
import by.epamtc.task1.entity.Privilege;

public class PrivilegeDAOImpl implements SQLPrivilegeDao {

	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();
	private static final String SELECT_ALL_PRIVILEGES = "select * from stud_privileges";
	private static final String SELECT_PRIVILEGE_BY_NAME = "select * from stud_privileges where name = ?";
	
	@Override
	public List<Privilege> getAll() throws DAOException {
		List<Privilege> listPrivileges = new ArrayList<Privilege>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;


		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_PRIVILEGES);

			while (rs.next()) {
				listPrivileges.add(new Privilege(rs.getInt(idInt), rs.getString(nameInt)));
			}

			return listPrivileges;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
	}

	@Override
	public Privilege getByName(String name) throws DAOException {
		Privilege privilege = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;


		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_PRIVILEGE_BY_NAME);
			ps.setString(1,name);
			rs = ps.executeQuery();

			if (rs.next()) {
				privilege = new Privilege(rs.getInt(idInt), rs.getString(nameInt));
			}

			return privilege;

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

}
