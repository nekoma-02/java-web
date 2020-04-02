package by.epamtc.task1.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.task1.controller.command.impl.ChangeLocal;
import by.epamtc.task1.controller.command.impl.Registration;
import by.epamtc.task1.controller.command.impl.SignIn;

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
