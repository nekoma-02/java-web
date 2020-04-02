package by.epamtc.task1.service;

import by.epamtc.task1.service.impl.SpecialtyServiceImpl;
import by.epamtc.task1.service.impl.UserServiceImpl;

public class ServiceFactory {
private static final ServiceFactory instance = new ServiceFactory();

public static ServiceFactory getInstance() {
	return instance;
}

public UserService getUserService() {
	return new UserServiceImpl();
}

public SpecialtyService getSpecialtyService() {
	return new SpecialtyServiceImpl();
}

}
