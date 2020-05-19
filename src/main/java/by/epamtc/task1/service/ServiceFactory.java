package by.epamtc.task1.service;

import by.epamtc.task1.service.impl.ApplicationServiceImpl;
import by.epamtc.task1.service.impl.UserServiceImpl;

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
