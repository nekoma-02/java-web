package by.epam.university.service.validator;

import java.util.List;

public interface PrivilegeValidator {

	List<String> validate(String id, String temp);
	
	List<String> validate(String privilegeName);
}
