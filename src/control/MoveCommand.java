package control;

import tp.pr1.Direction;
import tp.pr1.Game;

public class MoveCommand extends Command{
	
	private Direction dir;

	public MoveCommand(Direction dir) {
		super("move", "Ejecuta un movimiento en una direccion");
		this.dir = dir;
	}
	
	public Command parse(String[] commandWords, Controller controller) {
		if (commandWords[0].equals("move") && commandWords.length == 2)
		{
			Command c = null;
			switch (commandWords[1])
			{
			case "left":
				c = new MoveCommand(Direction.LEFT);
				break;
			case "right":
				c = new MoveCommand(Direction.RIGHT);
				break;
			case "up":
				c = new MoveCommand(Direction.UP);
				break;
			case "down":
				c = new MoveCommand(Direction.DOWN);
				break;
			}
			return c;
		}
		else
			return null;
	}
	
	public void execute(Game game, Controller controller)
	{
		game.move(dir);
	}
}
