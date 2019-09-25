package control;

import tp.pr1.Game;

public class UndoCommand extends NoParamsCommand {

	public UndoCommand() {
		super("Undo", "Restablece el juego al estado que tenía antes del último movimiento");
	}


	public void execute(Game game, Controller controller)
	{
		game.undo();
		System.out.println(game.toString());
	}

}
