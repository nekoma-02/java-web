package by.epam.university.service.exception;

import java.util.Set;

public class InvalidUserDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4531327515489693758L;

	private Set<String> invalidData;

	public Set<String> getInvalidData() {
		return invalidData;
	}
	
	public InvalidUserDataException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InvalidUserDataException(String message, Set<String> invalidData) {
        super(message);
        this.invalidData = invalidData;
    }

	public InvalidUserDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
