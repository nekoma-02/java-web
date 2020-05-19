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

import by.epamtc.task1.dao.SQLSchoolDao;
import by.epamtc.task1.dao.connectionpool.ConnectionPool;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolException;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolManager;
import by.epamtc.task1.dao.exception.DAOConnectionPoolException;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.dao.exception.DAOSQLException;
import by.epamtc.task1.entity.School;

public class SchoolDAOImpl implements SQLSchoolDao {
	
	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();
	
	private static final String SELECT_ALL_SCHOOLS = "select * from schools";
	private static final String SELECT_SCHOOL_BY_NAME = "select * from schools where name = ?";

	
	@Override
	public List<School> getAll() throws DAOException {
		List<School> listSchools = new ArrayList<School>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;


		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_SCHOOLS);

			while (rs.next()) {
				listSchools.add(new School(rs.getInt(idInt), rs.getString(nameInt)));
			}

			return listSchools;

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
	public School getByName(String name) throws DAOException {
		School school = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;


		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_SCHOOL_BY_NAME);
			ps.setString(1,name);
			rs = ps.executeQuery();

			if (rs.next()) {
				school = new School(rs.getInt(idInt), rs.getString(nameInt));
			}

			return school;

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
