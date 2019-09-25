package control;

import tp.pr1.Game;

public class ResetCommand extends NoParamsCommand{

	public ResetCommand() {
		super("reset", "Empieza un nuevo juego");
	}

	
	public void execute(Game game, Controller controller)
	{
		game.reset();
		System.out.println(game.toString());
	}

}
