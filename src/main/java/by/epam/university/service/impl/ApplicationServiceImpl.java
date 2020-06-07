package by.epam.university.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SQLApplicationDao;
import by.epam.university.dao.SQLPrivilegeDao;
import by.epam.university.dao.SQLSchoolDao;
import by.epam.university.dao.SQLSpecialtyDao;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Application;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.exception.ServiceException;

public class ApplicationServiceImpl implements ApplicationService {

	@Override
	public Application ApplicationByUserId(int id) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();
		
		try {
			Application app = dao.applicationByUserId(id);
			return app;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean createApplication(Application application) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();
		boolean isInsert = false;
		
		try {
			isInsert = dao.insertApplication(application);
			return isInsert;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public List<Specialty> getAllSpecialties() throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();
		
		List<Specialty> specialties = new ArrayList<Specialty>();
		try {
			specialties = dao.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return specialties;
		
	}

	@Override
	public List<School> getAllSchools() throws ServiceException {
		SQLSchoolDao dao = DAOFactory.getInstance().getSchoolDAO();
		
		List<School> schools = new ArrayList<School>();
		try {
			schools = dao.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return schools;
		
	}

	@Override
	public List<Privilege> getAllPrivileges() throws ServiceException {
		SQLPrivilegeDao dao = DAOFactory.getInstance().getPrivilegeDAO();
		
		List<Privilege> privileges = new ArrayList<Privilege>();
		try {
			privileges = dao.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return privileges;
	}
	
	@Override
	public boolean isExistUserApplication(int userId) throws ServiceException {
		SQLApplicationDao appDao = DAOFactory.getInstance().getApplicationDAO();
		Application app = null;
		try {
			app = appDao.applicationByUserId(userId);
			return app == null ? true : false;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
}
