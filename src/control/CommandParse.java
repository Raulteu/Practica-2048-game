package control;

public class CommandParse {

	private static Command[ ] availableCommands = { 
			new HelpCommand(),
			new ResetCommand(), 
			new ExitCommand(), 
			new MoveCommand(null),
			new PlayCommand(null, null)};
	
	public static Command parseCommand(String[ ] commandWords, Controller controller)
	{
		Command command = null;
		for (Command c: availableCommands) {
				command = c.parse(commandWords, controller) ;
			if (command != null)
				return command;
		}
		
		return command;
	}
	
	
	public static String commandHelp()
	{
		String str = "";
		for (Command command: availableCommands) {
			str += command.helpText();
		}
		return str;
	}
	
}
