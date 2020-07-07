package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.service.validator.PrivilegeValidator;
import by.epam.university.service.validator.ValidatorParameters;

public class PrivilegeValidatorImpl implements PrivilegeValidator {

	@Override
	public List<String> validate(String privilegeName) {
		List<String> validation = new ArrayList<String>();

		if (privilegeName == null) {
			validation.add(ValidatorParameters.INVALID_PRIVILEGE_NAME);
		}

		return validation;
	}

	@Override
	public List<String> validate(String id, String temp) {
		List<String> validation = new ArrayList<String>();

		if (id == null || id.isEmpty() || Integer.parseInt(id) > 0) {
			validation.add(ValidatorParameters.INVALID_PRIVILEGE);
		}

		return validation;
	}

}
