package by.epamtc.task1.service.validator;

public class PasswordValidator {
	public static boolean validatePassword(String password) {
		return password.matches("^[a-zA-Z0-9][a-zA-Z0-9]{3,18}$");
	}
}
