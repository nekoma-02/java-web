package by.epam.university.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.university.controller.command.ajax.AjaxCommand;
import by.epam.university.controller.command.ajax.AjaxCommandName;
import by.epam.university.controller.command.ajax.impl.GetApplication;
import by.epam.university.controller.command.ajax.impl.GetFaculty;
import by.epam.university.controller.command.ajax.impl.GetPrivilege;
import by.epam.university.controller.command.ajax.impl.GetSchool;
import by.epam.university.controller.command.ajax.impl.GetSpecialty;
import by.epam.university.controller.command.ajax.impl.GetTypeStudy;
import by.epam.university.controller.command.front.Command;
import by.epam.university.controller.command.front.CommandName;
import by.epam.university.controller.command.front.impl.AddApplication;
import by.epam.university.controller.command.front.impl.AddFaculty;
import by.epam.university.controller.command.front.impl.AddPrivilege;
import by.epam.university.controller.command.front.impl.AddSchool;
import by.epam.university.controller.command.front.impl.AddSpecialty;
import by.epam.university.controller.command.front.impl.AddTypeStudy;
import by.epam.university.controller.command.front.impl.AdminPage;
import by.epam.university.controller.command.front.impl.ChangeLocal;
import by.epam.university.controller.command.front.impl.Registration;
import by.epam.university.controller.command.front.impl.ShowAddApplicationPage;
import by.epam.university.controller.command.front.impl.ShowAddSpecialtyPage;
import by.epam.university.controller.command.front.impl.ShowSpecialty;
import by.epam.university.controller.command.front.impl.ShowSpecialtyDetail;
import by.epam.university.controller.command.front.impl.ShowUpdateFacultyPage;
import by.epam.university.controller.command.front.impl.ShowUpdatePrivilegePage;
import by.epam.university.controller.command.front.impl.ShowUpdateSchoolPage;
import by.epam.university.controller.command.front.impl.ShowUpdateSpecialtyPage;
import by.epam.university.controller.command.front.impl.ShowUpdateTypeStudyPage;
import by.epam.university.controller.command.front.impl.ShowUserPage;
import by.epam.university.controller.command.front.impl.SignIn;
import by.epam.university.controller.command.front.impl.SignOut;
import by.epam.university.controller.command.front.impl.UpdateFaculty;
import by.epam.university.controller.command.front.impl.UpdatePrivilege;
import by.epam.university.controller.command.front.impl.UpdateSchool;
import by.epam.university.controller.command.front.impl.UpdateSpecialty;
import by.epam.university.controller.command.front.impl.UpdateTypeStudy;

public class CommandProvided {

	private static final CommandProvided instance = new CommandProvided();

	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	private final Map<AjaxCommandName, AjaxCommand> ajaxRepository = new HashMap<AjaxCommandName, AjaxCommand>();


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
		commands.put(CommandName.ADD_APPLICATION, new AddApplication()); 
		commands.put(CommandName.ADMIN_PAGE, new AdminPage()); 
		commands.put(CommandName.ADD_FACULTY, new AddFaculty()); 
		commands.put(CommandName.ADD_PRIVILEGE, new AddPrivilege()); 
		commands.put(CommandName.ADD_SCHOOL, new AddSchool()); 
		commands.put(CommandName.ADD_TYPE_STUDY, new AddTypeStudy()); 
		commands.put(CommandName.ADD_SPECIALTY, new AddSpecialty()); 
		commands.put(CommandName.SHOW_UPDATE_FACULTY_PAGE, new ShowUpdateFacultyPage()); 
		commands.put(CommandName.SHOW_UPDATE_PRIVILEGE_PAGE, new ShowUpdatePrivilegePage()); 
		commands.put(CommandName.SHOW_UPDATE_SCHOOL_PAGE, new ShowUpdateSchoolPage()); 
		commands.put(CommandName.SHOW_UPDATE_SPECIALTY_PAGE, new ShowUpdateSpecialtyPage()); 
		commands.put(CommandName.SHOW_UPDATE_TYPE_STUDY_PAGE, new ShowUpdateTypeStudyPage()); 
		commands.put(CommandName.UPDATE_FACULTY, new UpdateFaculty()); 
		commands.put(CommandName.UPDATE_PRIVILEGE, new UpdatePrivilege()); 
		commands.put(CommandName.UPDATE_SCHOOL, new UpdateSchool()); 
		commands.put(CommandName.UPDATE_TYPE_STUDY, new UpdateTypeStudy()); 
		commands.put(CommandName.UPDATE_SPECIALTY, new UpdateSpecialty()); 
		commands.put(CommandName.SHOW_ADD_SPECIALTY_PAGE, new ShowAddSpecialtyPage()); 
		commands.put(CommandName.SHOW_SPECIALTY_DETAIL, new ShowSpecialtyDetail());
		
		ajaxRepository.put(AjaxCommandName.GET_SPECIALTY, new GetSpecialty());
		ajaxRepository.put(AjaxCommandName.GET_APPLICATION, new GetApplication());
		ajaxRepository.put(AjaxCommandName.GET_FACULTY, new GetFaculty());
		ajaxRepository.put(AjaxCommandName.GET_TYPE_STUDY, new GetTypeStudy());
		ajaxRepository.put(AjaxCommandName.GET_PRIVILEGE, new GetPrivilege());
		ajaxRepository.put(AjaxCommandName.GET_SCHOOL, new GetSchool());

	}
			
			
	public Command getFrontCommand(String commandName) {
		CommandName name = CommandName.valueOf(commandName.toUpperCase());
		Command command = null;

		if (name != null) {
			command = commands.get(name);
		} else {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}

		return command;

	}

	public AjaxCommand getAjaxCommand(String name) {
        AjaxCommandName ajaxCommandName;
        AjaxCommand ajaxCommand;

        if (name != null) {
            ajaxCommandName = AjaxCommandName.valueOf(name.toUpperCase());
            ajaxCommand = ajaxRepository.get(ajaxCommandName);

            if (ajaxCommand == null) {
                ajaxCommand = ajaxRepository.get(AjaxCommandName.NO_SUCH_COMMAND);
            }
        } else {
            ajaxCommand = ajaxRepository.get(AjaxCommandName.NO_SUCH_COMMAND);

        }
        return ajaxCommand;
    }
}
