package control;

import Exceptions.direccionDesconocida;
import Exceptions.finException;
import tp.pr1.Direction;
import tp.pr1.Game;

public class MoveCommand extends Command{
	
	private Direction dir;
	
	public static String excepcion = "Direccion no valida. (Direcciones: right, left, up, down)";

	public MoveCommand(Direction dir) {
		super("move", "Ejecuta un movimiento en una direcci�n");
		this.dir = dir;
	}
	
	public Command parse(String[] commandWords) throws direccionDesconocida {
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
				if (c == null)
					throw new direccionDesconocida(excepcion);			
			return c;
		}
		else
			return null; 
	}
	
	public boolean execute(Game game) throws finException
	{
		game.move(dir);
		return true;
	}
}
