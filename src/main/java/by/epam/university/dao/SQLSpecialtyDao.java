package by.epam.university.dao;

import java.util.List;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.Subject;
import by.epam.university.entity.TypeStudy;

public interface SQLSpecialtyDao {

	boolean insert(Specialty specialty) throws DAOException;

	Specialty getSpecialtyByName(String name) throws DAOException;

	Specialty getSpecialtyById(int id) throws DAOException;

	List<Specialty> getAll() throws DAOException;
	
	List<Specialty> getSpecialty(int idTypeStudy, int idFaculty) throws DAOException;
	
	List<Faculty> getAllFaculty() throws DAOException;
	
	List<TypeStudy> getAllTypeStudy() throws DAOException;
	
	boolean insertFaculty(Faculty faculty) throws DAOException;
	
	boolean insertTypeStudy(TypeStudy typeStudy) throws DAOException;
	
	boolean updateFaculty(Faculty faculty) throws DAOException;
	
	boolean updateTypeStudy(TypeStudy typeStudy) throws DAOException;
	
	Faculty getFacultyById(int id) throws DAOException;
	
	TypeStudy getTypeStudyById(int id) throws DAOException;
	
	boolean updateSpecialty(Specialty specialty) throws DAOException;
	
	List<Subject> subjectBySpecialtyID(int id) throws DAOException;
	
}
