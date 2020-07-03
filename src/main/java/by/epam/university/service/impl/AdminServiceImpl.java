package by.epam.university.service.impl;

import java.util.List;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SQLApplicationDao;
import by.epam.university.dao.SQLPrivilegeDao;
import by.epam.university.dao.SQLSchoolDao;
import by.epam.university.dao.SQLSpecialtyDao;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Application;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.TypeStudy;
import by.epam.university.service.AdminService;
import by.epam.university.service.exception.ServiceException;

public class AdminServiceImpl implements AdminService {

	@Override
	public List<Application> getAllUnconfirmedApplication() throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.getAllUnconfirmedApplication();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Application> getAllConfirmedApplication() throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.getAllConfirmedApplication();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Application> getAllApplication() throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.getAllApplication();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean insertFaculty(Faculty faculty) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();
		
		try {
			return dao.insertFaculty(faculty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean insertTypeStudy(TypeStudy typeStudy) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();
		
		try {
			return dao.insertTypeStudy(typeStudy);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean insertSchool(School school) throws ServiceException {
		SQLSchoolDao dao = DAOFactory.getInstance().getSchoolDAO();
		
		try {
			return dao.insert(school);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean insertPrivilege(Privilege privilege) throws ServiceException {
		SQLPrivilegeDao dao = DAOFactory.getInstance().getPrivilegeDAO();
		
		try {
			return dao.insert(privilege);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
