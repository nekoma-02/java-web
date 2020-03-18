package by.epamtc.task1.service.validator;

public class LoginValidator {

	public static boolean validateLogin(String login) {
        return login.matches("^[a-zA-Z][a-zA-Z0-9]{3,15}$");
    }

    
}
