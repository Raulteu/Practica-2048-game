package control;

import Exceptions.redoException;
import tp.pr1.Game;

public class RedoCommand extends NoParamsCommand{

	public RedoCommand() {
		super("Redo", "Ejecuta un comando previamente realizado");
	}

	public boolean execute(Game game) throws redoException
	{
		return game.redo();
	}


}
