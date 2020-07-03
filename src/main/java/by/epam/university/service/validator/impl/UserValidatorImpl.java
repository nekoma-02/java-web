package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.service.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {

	private static final String LOGIN_PATTERN = "[a-zA-Z0-9-_]{5,15}$";
	private static final String PASSWORD_PATTERN = "[a-zA-Z0-9_-]{6,18}$";
	private static final String NAME_PATTERN = "^([a-zA-Z-]|[а-€ј-я-]){2,25}$";
	public static final String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

	public UserValidatorImpl() {
	}

	
	private boolean validateEmail(String email) {
		return email.matches(EMAIL_PATTERN);
	}
	
	/**
	 * login c ограничением 5-15 символов, которыми могут быть буквы и цифры, символ
	 * подчеркивани€, дефис.
	 */

	private boolean validateLogin(String login) {
		return login.matches(LOGIN_PATTERN);
	}

	/**
	 * password с ограничением 6-18 символов, которыми могут быть буквы и цифры,
	 * символ подчеркивани€, дефис.
	 */
	private boolean validatePassword(String password) {
		return password.matches(PASSWORD_PATTERN);
	}

	/**
	 * name с ограничением 2-25 символов, которыми могут быть только буквы и дефис.
	 */
	private boolean validateName(String name) {
		return name.matches(NAME_PATTERN);
	}

	

	@Override
	public List<String> validate(String login, String password) {
		List<String> validation = new ArrayList<String>();
		
		if (login == null || !validateLogin(login)) {
			validation.add("invalid_login");
		}
		
		if (password == null || !validatePassword(password)) {
			validation.add("invalid_password");
		}
		
		return validation;
	}

	@Override
	public List<String> validate(String name, String secondName, String lastName, String email) {
		List<String> validation = new ArrayList<String>();
		
		if (name == null || !validateName(name)) {
			validation.add("invalid_first_name");
		}
		
		if (secondName == null || !validateName(secondName)) {
			validation.add("invalid_second_name");
		}
		
		if (lastName == null || !validateName(lastName)) {
			validation.add("invalid_last_name");
		}
		
		if (email == null || !validateEmail(email)) {
			validation.add("invalid_email");
		}
		
		return validation;
	}


	@Override
	public List<String> validate(String name, String secondName, String lastName, String email, String gender,
			String maritalStatus, String placeOfBirth) {
		List<String> validation = validate(name, secondName, lastName, email);
		return validation;
	}

	

}
