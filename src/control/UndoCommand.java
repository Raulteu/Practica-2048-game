package control;

import Exceptions.undoException;
import tp.pr1.Game;

public class UndoCommand extends NoParamsCommand {

	public UndoCommand() {
		super("Undo", "Restablece el juego al estado que tenía antes del último movimiento");
	}


	public boolean execute(Game game) throws undoException
	{
		return game.undo();
	}

}
