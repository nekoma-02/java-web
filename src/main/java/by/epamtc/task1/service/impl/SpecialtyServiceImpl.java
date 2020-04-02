package by.epamtc.task1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.task1.dao.DAOFactory;
import by.epamtc.task1.dao.SQLSpecialtyDao;
import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.Specialty;
import by.epamtc.task1.service.SpecialtyService;
import by.epamtc.task1.service.exception.ServiceException;

public class SpecialtyServiceImpl implements SpecialtyService {
	
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public List<Specialty> getAll() throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();
		
		List<Specialty> specialties = new ArrayList<Specialty>();
		try {
			specialties = dao.getAll();
		} catch (DAOException e) {
			logger.log(Level.ERROR, e);
			throw new ServiceException(e);
		}
		
		return specialties;
		
	}

}
