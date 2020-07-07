package by.epam.university.service.validator;

import java.util.List;

public interface SchoolValidator {
	List<String> validate(String id);
	
	List<String> validate(String level, String institution);

	List<String> validate(String nameSchool, String level, String institution);

}
