package by.epam.university.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.dao.SQLApplicationDao;
import by.epam.university.dao.connectionpool.ConnectionPool;
import by.epam.university.dao.connectionpool.ConnectionPoolException;
import by.epam.university.dao.connectionpool.ConnectionPoolManager;
import by.epam.university.dao.exception.DAOConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.dao.exception.DAOSQLException;
import by.epam.university.entity.Application;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.TypeStudy;
import by.epam.university.entity.User;

public class ApplicationDAOImpl implements SQLApplicationDao {

	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();


	private static final String SELECT_APPLICATION_BY_USERID = "select applications.idapplication, applications.adress, applications.certificate, stud_privileges.idprivilege, users.id_user, schools.idschool, specialties.idspecialty,applications.confirmation,applications.type_document,applications.id_document,applications.series_passport,applications.number_passport,applications.issued_by,applications.end_study_date from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege inner join users on applications.users_id_user = users.id_user inner join schools on applications.schools_idschool=schools.idschool inner join specialties on applications.specialties_idspecialty = specialties.idspecialty where users.id_user = ?";
	private static final String INSERT_APPLICATION = "insert into applications(adress,certificate,privileges_idprivilege,users_id_user,schools_idschool,specialties_idspecialty,confirmation,type_document,id_document,series_passport,number_passport,issued_by,end_study_date) value (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_APPLICATION = "update applications set adress = ?,certificate = ?,privileges_idprivilege = ?,users_id_user = ?,schools_idschool = ?,specialties_idspecialty = ?,confirmation = ?,type_document = ?,id_document = ?,series_passport = ?,number_passport = ?,issued_by = ?,end_study_date = ? where idapplication = ?";
	private static final String SELECT_APPLICATION_BY_UNCONFIRMED = "select applications.idapplication, applications.adress, applications.certificate, applications.confirmation, applications.type_document, applications.id_document,applications.series_passport,applications.number_passport,applications.issued_by,applications.end_study_date,stud_privileges.idprivilege, stud_privileges.name,users.id_user, users.name, users.secondname,users.lastname,schools.idschool,specialties.idspecialty, specialties.specialty_name,faculties.idfaculty, faculties.faculty_name,types_study.idtype_study, types_study.type_name from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege inner join users on applications.users_id_user = users.id_user inner join schools on applications.schools_idschool=schools.idschool inner join specialties on applications.specialties_idspecialty = specialties.idspecialty inner join types_study on specialties.idtype_study = types_study.idtype_study inner join faculties on specialties.faculties_idfaculty = faculties.idfaculty where confirmation = 0";
	private static final String SELECT_APPLICATION_BY_CONFIRMED = "select applications.idapplication, applications.adress, applications.certificate, applications.confirmation, applications.type_document, applications.id_document,applications.series_passport,applications.number_passport,applications.issued_by,applications.end_study_date,stud_privileges.idprivilege, stud_privileges.name,users.id_user, users.name, users.secondname,users.lastname,schools.idschool,specialties.idspecialty, specialties.specialty_name,faculties.idfaculty, faculties.faculty_name,types_study.idtype_study, types_study.type_name from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege inner join users on applications.users_id_user = users.id_user inner join schools on applications.schools_idschool=schools.idschool inner join specialties on applications.specialties_idspecialty = specialties.idspecialty inner join types_study on specialties.idtype_study = types_study.idtype_study inner join faculties on specialties.faculties_idfaculty = faculties.idfaculty where confirmation = 1";
	private static final String SELECT_APPLICATION = "select applications.idapplication, applications.adress, applications.certificate, applications.confirmation, applications.type_document, applications.id_document,applications.series_passport,applications.number_passport,applications.issued_by,applications.end_study_date,stud_privileges.idprivilege, stud_privileges.name,users.id_user, users.name, users.secondname,users.lastname,schools.idschool,specialties.idspecialty, specialties.specialty_name,faculties.idfaculty, faculties.faculty_name,types_study.idtype_study, types_study.type_name from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege inner join users on applications.users_id_user = users.id_user inner join schools on applications.schools_idschool=schools.idschool inner join specialties on applications.specialties_idspecialty = specialties.idspecialty inner join types_study on specialties.idtype_study = types_study.idtype_study inner join faculties on specialties.faculties_idfaculty = faculties.idfaculty";

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
						new User(rs.getInt(5)), new School(rs.getInt(6)),
						new Specialty(rs.getInt(7)), rs.getBoolean(8), rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12),rs.getString(13),rs.getDate(14));
			}

			return application;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
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
			ps.setInt(6, application.getSpecialties().getId());
			ps.setBoolean(7, application.isConfirmation());
			ps.setString(8, application.getTypeDocument());
			ps.setString(9, application.getIdDocument());
			ps.setString(10, application.getSeriesPassport());
			ps.setInt(11, application.getNumberPassport());
			ps.setString(12, application.getIssuedBy());
			ps.setDate(13, application.getEndStudyDate());
			

			return ps.executeUpdate() == 1;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}
	}

	@Override
	public boolean updateApplication(Application application) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_APPLICATION);

			ps.setString(1, application.getAdress());
			ps.setInt(2, application.getCertificate());
			ps.setInt(3, application.getPrivilege().getId());
			ps.setInt(4, application.getUser().getId());
			ps.setInt(5, application.getSchool().getId());
			ps.setInt(6, application.getSpecialties().getId());
			ps.setBoolean(7, application.isConfirmation());
			ps.setString(8, application.getTypeDocument());
			ps.setString(9, application.getIdDocument());
			ps.setString(10, application.getSeriesPassport());
			ps.setInt(11, application.getNumberPassport());
			ps.setString(12, application.getIssuedBy());
			ps.setDate(13, application.getEndStudyDate());
			ps.setInt(14, application.getId());
			

			return ps.executeUpdate() == 1;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}
	}

	

	@Override
	public List<Application> getAllUnconfirmedApplication() throws DAOException {
		List<Application> applications = new ArrayList<Application>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			st = connection.createStatement();

			rs = st.executeQuery(SELECT_APPLICATION_BY_UNCONFIRMED);

			if (rs.next()) {
				applications.add(buildApplication(rs));
			}

			return applications;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
	}

	@Override
	public List<Application> getAllConfirmedApplication() throws DAOException {
		List<Application> applications = new ArrayList<Application>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			st = connection.createStatement();

			rs = st.executeQuery(SELECT_APPLICATION_BY_CONFIRMED);

			if (rs.next()) {
				applications.add(buildApplication(rs));
			}

			return applications;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
	}

	@Override
	public List<Application> getAllApplication() throws DAOException {
		List<Application> applications = new ArrayList<Application>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			st = connection.createStatement();

			rs = st.executeQuery(SELECT_APPLICATION);

			if (rs.next()) {
				applications.add(buildApplication(rs));
			}

			return applications;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
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
	
	
	private Application buildApplication(ResultSet rs) throws SQLException {
		Application app = null;
		
		int id = rs.getInt(1);
		String adress = rs.getString(2);
		int certificate = rs.getInt(3);
		boolean confirmation = rs.getBoolean(4);
		String typeDocument = rs.getString(5);
		String idDocument = rs.getString(6);
		String seriesPassport = rs.getString(7);
		int numberPassport = rs.getInt(8);
		String issuedBy = rs.getString(9);
		Date endStudyDate = rs.getDate(10);
		Privilege privilege = new Privilege(rs.getInt(11), rs.getString(12));
		User user = new User(rs.getInt(13), rs.getString(14), rs.getString(15), rs.getString(16));
		School school = new School(rs.getInt(17));
		TypeStudy typeStudy = new TypeStudy(rs.getInt(22), rs.getString(23));
		Faculty faculty = new Faculty(rs.getInt(20), rs.getString(21));
		Specialty specialties = new Specialty(rs.getInt(18),  rs.getString(19), typeStudy, faculty);
		
		
		app = new Application(id, adress, certificate, privilege, user, school, specialties, confirmation, typeDocument, idDocument, seriesPassport, numberPassport, issuedBy, endStudyDate);
		
		return app;
	}

}
