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

import by.epamtc.task1.dao.SQLSpecialtyDao;
import by.epamtc.task1.dao.connectionpool.ConnectionPool;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolException;
import by.epamtc.task1.dao.connectionpool.ConnectionPoolManager;
import by.epamtc.task1.dao.exception.DAOConnectionPoolException;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.dao.exception.DAOSQLException;
import by.epamtc.task1.entity.Faculty;
import by.epamtc.task1.entity.Specialty;
import by.epamtc.task1.entity.TypeStudy;

public class SpecialtyDAOImpl implements SQLSpecialtyDao {

	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();

	private static final String SELECT_ALL_SPECIALTY = "select specialties.idspecialty,specialties.specialty_name,specialties.plan,specialties.year,types_study.type_name,faculties.faculty_name from specialties inner join types_study on specialties.idtype_study = types_study.idtype_study inner join faculties on faculties.idfaculty=specialties.faculties_idfaculty";
	private static final String SELECT_SPECIALTY_BY_ID = "select specialties.idspecialty,specialties.specialty_name,specialties.plan,specialties.year,types_study.type_name,faculties.faculty_name from specialties inner join types_study on specialties.idtype_study = types_study.idtype_study inner join faculties on faculties.idfaculty=specialties.faculties_idfaculty where specialties.idspecialty = ?";
	private static final String SELECT_SPECIALTY_BY_NAME = "select specialties.idspecialty,specialties.specialty_name,specialties.plan,specialties.year,types_study.type_name,faculties.faculty_name from specialties inner join types_study on specialties.idtype_study = types_study.idtype_study inner join faculties on faculties.idfaculty=specialties.faculties_idfaculty where specialties.specialty_name = ?";
	private static final String INSERT_SPECIALTY = "insert into specialties(specialty_name,plan,year,idtype_study,faculties_idfaculty) values (?,?,?,?,?)";
	
	@Override
	public boolean insert(Specialty specialty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		int idInt = 1;
		int nameInt = 2;
		int planInt = 3;
		int yearInt = 4;
		int typeStudyIdInt = 5;
		int facultyIdInt = 6;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSERT_SPECIALTY);

			ps.setString(nameInt, specialty.getName());
			ps.setInt(planInt, specialty.getPlan());
			ps.setInt(yearInt, specialty.getYear());
			ps.setInt(typeStudyIdInt, specialty.getTypeStudy().getId());
			ps.setInt(facultyIdInt, specialty.getFaculty().getId());

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
	public Specialty getSpecialtyByName(String name) throws DAOException {
		Specialty specialty = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;
		int planInt = 3;
		int yearInt = 4;
		int typeStudyNameInt = 5;
		int facultyNameInt = 6;

		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_SPECIALTY_BY_NAME);
			ps.setString(1,name);

			rs = ps.executeQuery();

			if (rs.next()) {
				specialty = new Specialty(rs.getInt(idInt), 
						rs.getString(nameInt), 
						rs.getInt(planInt),
						rs.getInt(yearInt), 
						new TypeStudy(0, rs.getString(typeStudyNameInt)),
						new Faculty(0, rs.getString(facultyNameInt)));
			}

			return specialty;

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
	public Specialty getSpecialtyById(int id) throws DAOException {
		Specialty specialty = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;
		int planInt = 3;
		int yearInt = 4;
		int typeStudyNameInt = 5;
		int facultyNameInt = 6;

		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_SPECIALTY_BY_ID);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				specialty = new Specialty(rs.getInt(idInt), 
						rs.getString(nameInt), 
						rs.getInt(planInt),
						rs.getInt(yearInt), 
						new TypeStudy(0, rs.getString(typeStudyNameInt)),
						new Faculty(0, rs.getString(facultyNameInt)));
			}

			return specialty;

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
	public List<Specialty> getAll() throws DAOException {
		List<Specialty> specialties = new ArrayList<Specialty>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;
		int planInt = 3;
		int yearInt = 4;
		int typeStudyNameInt = 5;
		int facultyNameInt = 6;

		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_SPECIALTY);

			while (rs.next()) {
				specialties.add(new Specialty(rs.getInt(idInt), rs.getString(nameInt), rs.getInt(planInt),
						rs.getInt(yearInt), new TypeStudy(0, rs.getString(typeStudyNameInt)),
						new Faculty(0, rs.getString(facultyNameInt))));
			}

			return specialties;

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
