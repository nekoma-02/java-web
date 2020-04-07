package by.epamtc.task1.service.validator.impl;

import by.epamtc.task1.service.validator.UserDataValidator;

public class UserValidator implements UserDataValidator {

	@Override
	public boolean validate(String login) {
		if (login == null || login.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean validate(String login, String password) {
		this.validate(login);
		
		if (password == null || password.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean validate(String login, String password, String email, String name, String secondName,
			String lastName) {
		this.validate(login, password);
		
		if (email == null || email.isEmpty()) {
			return false;
		}
		if (name == null || name.isEmpty()) {
			return false;
		}
		if (secondName == null || secondName.isEmpty()) {
			return false;
		}
		if (lastName == null || lastName.isEmpty()) {
			return false;
		}
		return true;
	}



}
