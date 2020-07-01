package by.epam.university.dao;

import java.util.List;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.School;

public interface SQLSchoolDao {

	List<School> getAll() throws DAOException;

	School getByName(String name) throws DAOException;
}