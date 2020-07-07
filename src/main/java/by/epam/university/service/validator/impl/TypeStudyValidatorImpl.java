package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.service.validator.TypeStudyValidator;
import by.epam.university.service.validator.ValidatorParameters;

public class TypeStudyValidatorImpl implements TypeStudyValidator {

	@Override
	public List<String> validate(String typeName) {
		List<String> validation = new ArrayList<String>();

		if (typeName == null) {
			validation.add(ValidatorParameters.INVALID_TYPE_NAME);
		}

		
		return validation;
	}

}
