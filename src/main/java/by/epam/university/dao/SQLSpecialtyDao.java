package by.epam.university.dao;

import java.util.List;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Specialty;

public interface SQLSpecialtyDao {

	boolean insert(Specialty specialty) throws DAOException;

	Specialty getSpecialtyByName(String name) throws DAOException;

	Specialty getSpecialtyById(int id) throws DAOException;

	List<Specialty> getAll() throws DAOException;
}
