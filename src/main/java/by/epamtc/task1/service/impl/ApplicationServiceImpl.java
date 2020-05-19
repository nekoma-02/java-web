package by.epamtc.task1.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epamtc.task1.dao.DAOFactory;
import by.epamtc.task1.dao.SQLApplicationDao;
import by.epamtc.task1.dao.SQLPrivilegeDao;
import by.epamtc.task1.dao.SQLSchoolDao;
import by.epamtc.task1.dao.SQLSpecialtyDao;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.Application;
import by.epamtc.task1.entity.Privilege;
import by.epamtc.task1.entity.School;
import by.epamtc.task1.entity.Specialty;
import by.epamtc.task1.service.ApplicationService;
import by.epamtc.task1.service.exception.ServiceException;

public class ApplicationServiceImpl implements ApplicationService {

	@Override
	public Application ApplicationById(int id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
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
}
