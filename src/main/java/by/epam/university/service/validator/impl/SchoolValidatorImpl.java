package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.service.validator.SchoolValidator;
import by.epam.university.service.validator.ValidatorParameters;

public class SchoolValidatorImpl implements SchoolValidator {

	@Override
	public List<String> validate(String nameSchool, String level, String institution) {
		List<String> validation = validate(level, institution);

		if (nameSchool == null) {
			validation.add(ValidatorParameters.INVALID_SCHOOL_NAME);
		}

		
		return validation;
	}

	@Override
	public List<String> validate(String level, String institution) {
		List<String> validation = new ArrayList<String>();

		if (level == null) {
			validation.add(ValidatorParameters.INVALID_LEVEL);
		}
		
		if (institution == null) {
			validation.add(ValidatorParameters.INVALID_INSTITUTION);
		}
		
		return validation;
	}

	@Override
	public List<String> validate(String id) {
		List<String> validation = new ArrayList<String>();

		if (id == null || id.isEmpty() || Integer.parseInt(id) > 0) {
			validation.add(ValidatorParameters.INVALID_SCHOOL);
		}

		return validation;
	}

}
