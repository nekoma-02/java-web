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

import by.epamtc.task1.dao.SQLHostelDao;
import by.epamtc.task1.dao.connectionpool.ConnectionPool;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolException;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolManager;
import by.epamtc.task1.dao.exception.DAOConnectionPoolException;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.dao.exception.DAOSQLException;
import by.epamtc.task1.entity.Hostel;

public class HostelDAOImpl implements SQLHostelDao{

	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();
	
	private static final String SELECT_ALL_HOSTELS = "select * from hostels";
	private static final String SELECT_HOSTEL_BY_NUMBER = "select * from hostels where number_hostel = ? ";
	//private static final String INSERT_HOSTEL = "select into hostels(number_hostel,occupied_places,total_places) values (?,?,?)";

	@Override
	public List<Hostel> getAll() throws DAOException {
		List<Hostel> listHostels = new ArrayList<Hostel>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		int idInt = 1;
		int numberInt = 2;
		int occupiedInt = 3;
		int totalInt = 4;

		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_HOSTELS);

			while (rs.next()) {
				listHostels.add(new Hostel(rs.getInt(idInt), rs.getInt(numberInt), rs.getInt(occupiedInt), rs.getInt(totalInt)));
			}

			return listHostels;

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
	public Hostel getByNumber(int number) throws DAOException {
		Hostel hostel = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idInt = 1;
		int numberInt = 2;
		int occupiedInt = 3;
		int totalInt = 4;

		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_HOSTEL_BY_NUMBER);
			ps.setInt(1,number);

			rs = ps.executeQuery();

			if (rs.next()) {
				hostel = new Hostel(rs.getInt(idInt), rs.getInt(numberInt), rs.getInt(occupiedInt), rs.getInt(totalInt));
			}

			return hostel;

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
