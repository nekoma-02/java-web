package by.epamtc.task1.service;

public class ServiceFactory {
private static final ServiceFactory instance = new ServiceFactory();

public static ServiceFactory getInstance() {
	return instance;
}

public UserService getService() {
	return new UserServiceImpl();
}
}
