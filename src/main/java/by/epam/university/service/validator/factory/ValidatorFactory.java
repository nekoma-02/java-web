package by.epam.university.service.validator.factory;

import by.epam.university.service.validator.UserValidator;
import by.epam.university.service.validator.impl.UserValidatorImpl;

public class ValidatorFactory {
	private static final ValidatorFactory instance = new ValidatorFactory();
	
	public static ValidatorFactory getInstance() {
		return instance;
	}
	
	public UserValidator getUserValidator() {
		return new UserValidatorImpl();
	}

}
