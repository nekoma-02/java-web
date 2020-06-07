package by.epam.university.dao;

import java.util.List;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Privilege;

public interface SQLPrivilegeDao {

	List<Privilege> getAll() throws DAOException;

	Privilege getByName(String name) throws DAOException;
}
