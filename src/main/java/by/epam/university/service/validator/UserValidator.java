package by.epam.university.service.validator;

import java.util.List;


public interface UserValidator {

	List<String> validate(String login, String password);
	
	List<String> validate(String name,String secondName,String lastName, String email);
	
	List<String> validate(String name,String secondName,String lastName, String email,String gender, String maritalStatus, String placeOfBirth);
	


}
