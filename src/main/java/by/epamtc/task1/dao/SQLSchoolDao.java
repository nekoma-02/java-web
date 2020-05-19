package by.epamtc.task1.dao;

import java.util.List;

import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.School;

public interface SQLSchoolDao {

	List<School> getAll() throws DAOException;

	School getByName(String name) throws DAOException;
}
