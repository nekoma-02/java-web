package by.epam.university.dao;

import java.util.List;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Hostel;

public interface SQLHostelDao {
	List<Hostel> getAll() throws DAOException;

	Hostel getByNumber(int number) throws DAOException;
}
