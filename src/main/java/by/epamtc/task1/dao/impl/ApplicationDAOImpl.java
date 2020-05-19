package by.epamtc.task1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.task1.dao.SQLApplicationDao;
import by.epamtc.task1.dao.connectionpool.ConnectionPool;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolException;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolManager;
import by.epamtc.task1.dao.exception.DAOConnectionPoolException;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.dao.exception.DAOSQLException;
import by.epamtc.task1.entity.Application;
import by.epamtc.task1.entity.Hostel;
import by.epamtc.task1.entity.Privilege;
import by.epamtc.task1.entity.School;
import by.epamtc.task1.entity.Specialty;
import by.epamtc.task1.entity.User;

public class ApplicationDAOImpl implements SQLApplicationDao {

	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();

	/**
	 * 1 - application id; 2 - application adress; 3 - application certificate; 4 -
	 * id privilege; 5 - id user; 6 - id hostel; 7 - id school; 8 - id specialty; 9
	 * - application need hostel; 10 - application confirmation;
	 **/
	private static final String SELECT_APPLICATION_BY_USERID = "select applications.idapplication, applications.adress, applications.certificate, stud_privileges.idprivilege, users.id_user,hostels.idhostel, schools.idschool, specialties.idspecialty, applications.need_hostel,applications.confirmation from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege inner join users on applications.users_id_user = users.id_user inner join hostels on applications.hostels_idhostel= hostels.idhostel inner join schools on applications.schools_idschool=schools.idschool inner join specialties on applications.specialties_idspecialty = specialties.idspecialty where users.id_user = ?";
	private static final String INSERT_APPLICATION = "insert into applications value (default,?,?,?,?,?,?,?,?,?)";

	@Override
	public Application applicationByUserId(int userId) throws DAOException {
		Application application = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			ps = connection.prepareStatement(SELECT_APPLICATION_BY_USERID);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next()) {
				application = new Application(rs.getInt(1), rs.getString(2), rs.getInt(3), new Privilege(rs.getInt(4)),
						new User(rs.getInt(5)), new School(rs.getInt(7)), rs.getBoolean(9), new Hostel(rs.getInt(6)),
						new Specialty(rs.getInt(8)), rs.getBoolean(10));
			}

			return application;

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
	public boolean insertApplication(Application application) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSERT_APPLICATION);

			ps.setString(1, application.getAdress());
			ps.setInt(2, application.getCertificate());
			ps.setInt(3, application.getPrivilege().getId());
			ps.setInt(4, application.getUser().getId());
			ps.setInt(5, application.getSchool().getId());
			ps.setBoolean(6, application.isNeedHostel());
			ps.setInt(7, application.getHostel().getId());
			ps.setInt(8, application.getSpecialty().getId());
			ps.setBoolean(9, application.isConfirmation());

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
	public boolean updateApplication(Application application) throws DAOException {
		// TODO Auto-generated method stub
		return false;
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
