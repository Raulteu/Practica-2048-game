package control;

import java.io.IOException;
import Exceptions.Excepciones;
import Exceptions.comandoDesconocido;


public class CommandParse {
	
	public static String excepcion = "Comando desconocido. Escribe 'help' para ver los posibles comandos.";

	public static Command[ ] availableCommands = { 
			new HelpCommand(),
			new ResetCommand(), 
			new ExitCommand(), 
			new MoveCommand(null),
			new PlayCommand(null, null),
			new RedoCommand(),
			new UndoCommand(),
			new LoadCommand(null),
			new SaveCommand(null)};
			
	
	public static Command parseCommand(String[ ] commandWords) throws Excepciones, IOException
	{
		Command command = null;
		for (Command c: availableCommands) {
				command = c.parse(commandWords) ;
		if (command != null)
			return command;
		}
		throw new comandoDesconocido(excepcion);
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
