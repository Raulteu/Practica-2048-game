package control;

import Exceptions.undoException;
import tp.pr1.Game;

public class UndoCommand extends NoParamsCommand {

	public UndoCommand() {
		super("Undo", "Restablece el juego al estado que ten�a antes del �ltimo movimiento");
	}


	public boolean execute(Game game) throws undoException
	{
		return game.undo();
	}

}
