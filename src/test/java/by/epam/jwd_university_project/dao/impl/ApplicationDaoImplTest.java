package by.epam.jwd_university_project.dao.impl;

import org.junit.Assert;
import org.junit.Test;

import by.epam.jwd_university_project.dao.DataBaseCreation;
import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SQLApplicationDao;
import by.epam.university.dao.exception.DAOException;

public class ApplicationDaoImplTest extends DataBaseCreation {
	
	@Test
	public void acceptStudent_whenIdApplicationDoesntExists_thenFalse() throws DAOException {
		SQLApplicationDao appDao = DAOFactory.getInstance().getApplicationDAO();
		boolean actual = appDao.acceptStudent(0, false, 15);
		boolean expected = false;
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void acceptStudent_whenIdApplicationExist_thenTrue() throws DAOException {
		SQLApplicationDao appDao = DAOFactory.getInstance().getApplicationDAO();
		boolean actual = appDao.acceptStudent(0, false, 1);
		boolean expected = true;
		
		Assert.assertEquals(expected, actual);
	}

}
