package control;

public abstract class NoParamsCommand extends Command{


	
	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	public Command parse(String[] commandWords, Controller controller) {
		Command c = null;
		if (commandWords.length == 1) {
			if (commandWords[0].equals("exit"))
				c = new ExitCommand();
			else if (commandWords[0].equals("help"))
				c = new HelpCommand();
			else if (commandWords[0].equals("reset"))
				c = new ResetCommand();
			else if (commandWords[0].equals("undo"))
				c = new UndoCommand();
			else if (commandWords[0].equals("redo"))
				c = new RedoCommand();
		}
		return c;
	}
		
}
