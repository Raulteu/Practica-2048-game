package control;

import tp.pr1.Game;

public class RedoCommand extends NoParamsCommand{

	public RedoCommand() {
		super("Redo", "Ejecuta un comando previamente realizado");
	}

	public void execute(Game game, Controller controller)
	{
		game.redo();
		System.out.println(game.toString());
	}

}
