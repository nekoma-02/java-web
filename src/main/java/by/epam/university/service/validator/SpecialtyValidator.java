package by.epam.university.service.validator;

import java.util.List;

public interface SpecialtyValidator {
	
	List<String> validate(String id);
	
	List<String> validate(String specialtyNane, String plan, String year);

}
