package control;

import tp.pr1.Game;

public class UndoCommand extends NoParamsCommand {

	public UndoCommand() {
		super("Undo", "Restablece el juego al estado que ten�a antes del �ltimo movimiento");
	}


	public void execute(Game game, Controller controller)
	{
		game.undo();
		System.out.println(game.toString());
	}

}
