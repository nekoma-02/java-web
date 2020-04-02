package by.epamtc.task1.service.validator.impl;

import by.epamtc.task1.entity.User;
import by.epamtc.task1.service.validator.Validator;

public class UserValidator implements Validator {

//	private static final String LOGIN_PATTERN = "[a-zA-Z0-9-_]{5,15}$";
//    private static final String PASSWORD_PATTERN = "[a-zA-Z0-9_-]{6,18}$";
//    private static final String NAME_PATTERN = "^([a-zA-Z-]|[а-яА-Я-]){2,25}$";
	
	private User user;
	
	public UserValidator(User user) {
		this.user=user;
	}
	
	@Override
	public boolean validate() {

        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            return false;
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
        	 return false;

        }
        if (user.getName() == null || user.getName().isEmpty()) {
        	 return false;

        }
        
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return false;
        }

        if (user.getSecondName() == null || user.getSecondName().isEmpty()) {
        	 return false;

        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
        	 return false;

        }
		return true;
	}
	

//    private boolean validateLogin(String login) {
//        return login.matches(LOGIN_PATTERN);
//    }
//
//    private boolean validatePassword(String password) {
//        return password.matches(PASSWORD_PATTERN);
//    }
//
//    private boolean validateName(String name) {
//        return name.matches(NAME_PATTERN);
//    }

}
