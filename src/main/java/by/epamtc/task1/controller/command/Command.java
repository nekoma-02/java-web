package by.epamtc.task1.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
	String execute(HttpServletRequest request);
}
