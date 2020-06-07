package by.epam.university.service.validator;

public interface UserDataValidator {

	boolean validate(String login);
	boolean validate(String login, String password);
	boolean validate(String login, String password, String email, String name, String secondName, String lastName);

}
