package control;

import tp.pr1.Game;

public class HelpCommand extends NoParamsCommand{

	public HelpCommand() {
		super("help", "Muestra este mensaje de ayuda");
	}
	
	public void execute(Game game, Controller controller)
	{
		System.out.println(game.help());
	}

}
