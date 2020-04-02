package by.epamtc.task1.service;

import java.util.List;

import by.epamtc.task1.entity.Specialty;
import by.epamtc.task1.service.exception.ServiceException;

public interface SpecialtyService {

	List<Specialty> getAll() throws ServiceException;
}
