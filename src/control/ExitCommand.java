package control;
import tp.pr1.Game;;


public class ExitCommand extends NoParamsCommand{

	public ExitCommand() {
		super("exit", "Termina el programa\"");
	}
	
	public boolean execute(Game game)
	{
		return false;
	}

	
}
