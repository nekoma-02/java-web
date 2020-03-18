package by.epamtc.task1.dao;

import java.util.List;

import by.epamtc.task1.dao.exception.DAOException;

public interface SQLDao <T> {

	int insert(T field) throws DAOException;
	T getValue(String key) throws DAOException;
	List<T> getAll() throws DAOException;
}
