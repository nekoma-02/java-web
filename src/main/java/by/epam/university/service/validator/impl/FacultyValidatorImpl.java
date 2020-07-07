package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.service.validator.FacultyValidator;
import by.epam.university.service.validator.ValidatorParameters;

public class FacultyValidatorImpl implements FacultyValidator {

	@Override
	public List<String> validate(String facultyName) {
		List<String> validation = new ArrayList<String>();
		
		if (facultyName == null ) {
			validation.add(ValidatorParameters.INVALID_FACULTY_NAME);
		}
		
		return validation;
	}

}
