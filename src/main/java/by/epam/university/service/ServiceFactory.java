package by.epam.university.service;

import by.epam.university.service.impl.ApplicationServiceImpl;
import by.epam.university.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	public static ServiceFactory getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return new UserServiceImpl();
	}

	public ApplicationService getApplicationService() {
		return new ApplicationServiceImpl();
	}
}
