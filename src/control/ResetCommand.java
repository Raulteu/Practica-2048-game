package control;

import tp.pr1.Game;

public class ResetCommand extends NoParamsCommand{

	public ResetCommand() {
		super("reset", "Empieza un nuevo juego");
	}

	
	public boolean execute(Game game)
	{
		game.reset();
		return true;
	}

}
