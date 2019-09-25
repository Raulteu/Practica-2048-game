package control;

import tp.pr1.Game;

public class HelpCommand extends NoParamsCommand{

	public HelpCommand() {
		super("help", "Muestra este mensaje de ayuda");
	}
	
	public boolean execute(Game game)
	{
		System.out.println(game.help());
		return false;
	}

}
