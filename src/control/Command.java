package control;

import java.io.IOException;

import Exceptions.Excepciones;
import tp.pr1.Game;

public abstract class Command {

	private String helpText;
	private String commandText;
	protected final String commandName;
	
	
	public Command(String commandInfo, String helpInfo) {
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}
	
	public abstract boolean execute(Game game) throws Excepciones, IOException;
	public abstract Command parse(String[] commandWords /*, Scanner in*/) throws Excepciones, IOException ; 

	public String helpText() {
		return " " + commandText + ": " + helpText;
	}	
}
