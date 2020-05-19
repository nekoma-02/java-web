package by.epamtc.task1.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.task1.controller.command.impl.ChangeLocal;
import by.epamtc.task1.controller.command.impl.Registration;
import by.epamtc.task1.controller.command.impl.ShowAddApplicationPage;
import by.epamtc.task1.controller.command.impl.ShowLoginPage;
import by.epamtc.task1.controller.command.impl.ShowRegistrPage;
import by.epamtc.task1.controller.command.impl.ShowSpecialty;
import by.epamtc.task1.controller.command.impl.ShowUserPage;
import by.epamtc.task1.controller.command.impl.SignIn;
import by.epamtc.task1.controller.command.impl.SignOut;

public class CommandProvided {

	private static final CommandProvided instance = new CommandProvided();

	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();

	public static CommandProvided getInstance() {
		return instance;
	}

	public CommandProvided() {
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.SIGN_IN, new SignIn()); 
		commands.put(CommandName.CHANGE_LOCAL, new ChangeLocal()); 
		commands.put(CommandName.SHOW_SPECIALTIES, new ShowSpecialty()); 
		commands.put(CommandName.SIGN_OUT, new SignOut()); 
		commands.put(CommandName.SHOW_USERPAGE, new ShowUserPage()); 
		commands.put(CommandName.SHOW_ADDAPPLICATION_PAGE, new ShowAddApplicationPage()); 
		commands.put(CommandName.SHOW_LOGIN_PAGE, new ShowLoginPage()); 
		commands.put(CommandName.SHOW_REGISTRATION_PAGE, new ShowRegistrPage()); 
	}
			
			
	public Command getCommand(String commandName) {
		CommandName name = CommandName.valueOf(commandName.toUpperCase());
		Command command = null;

		if (name != null) {
			command = commands.get(name);
		} else {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}

		return command;

	}

}
