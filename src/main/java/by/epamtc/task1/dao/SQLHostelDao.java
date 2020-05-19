package by.epamtc.task1.dao;

import java.util.List;

import by.epamtc.task1.dao.exception.DAOException;
import by.epamtc.task1.entity.Hostel;

public interface SQLHostelDao {
	List<Hostel> getAll() throws DAOException;

	Hostel getByNumber(int number) throws DAOException;
}
