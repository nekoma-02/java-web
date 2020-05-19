package by.epamtc.task1.dao;

import java.util.List;

import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.Privilege;

public interface SQLPrivilegeDao {

	List<Privilege> getAll() throws DAOException;

	Privilege getByName(String name) throws DAOException;
}
