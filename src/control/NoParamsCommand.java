package control;

public abstract class NoParamsCommand extends Command{

	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	public Command parse(String[] commandWords) {
		Command c = null; 
		if (commandWords.length == 1) {
			if (commandWords[0].equalsIgnoreCase(commandName)) {
				return this;
			}
		}	
		return c;
	}
		
}
