package by.epamtc.task1.dao;

import java.util.List;

import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.Specialty;

public interface SQLSpecialtyDao {

	boolean insert(Specialty specialty) throws DAOException;

	Specialty getSpecialtyByName(String name) throws DAOException;

	Specialty getSpecialtyById(int id) throws DAOException;

	List<Specialty> getAll() throws DAOException;
}
