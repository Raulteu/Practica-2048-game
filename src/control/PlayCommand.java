package control;

import java.util.Scanner;

import Rules.GameRules;
import Rules.Rules2048;
import Rules.RulesFib;
import Rules.RulesInverse;
import tp.pr1.Game;

public class PlayCommand extends Command{
	protected int boardSize, initialCells;
	protected GameType gameType;
	private GameRules gameRules;

	public PlayCommand(GameType gameType, GameRules gameRules) {
		super("play", "Selecciona el juego que quieres empezar (FIB, INVERSE o 2048)");
		this.gameType = gameType;
		this.gameRules = gameRules;
	}
	
	public Command parse(String[] commandWords, Controller controller) {
		Command c = null;
		if (commandWords[0].equals("play"))
		{
			switch (commandWords[1]) {
			case "fib":
				gameType = GameType.FIB;
				gameRules = new RulesFib();
				c = new PlayCommand(gameType, gameRules);
				break;
			case "inv":
				gameType = GameType.INVERSE;
				gameRules = new RulesInverse();
				c = new PlayCommand(gameType, gameRules);	
				break;
			case "orig":
				gameType = GameType.ORIG;
				gameRules = new Rules2048();
				c = new PlayCommand(gameType, gameRules);		
				break;
			}
			return c;
		}
		else
			return null;
	}
	
	public void execute(Game game, Controller controller) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Selecciona el tamaño del tablero: ");
		String str = sc.nextLine();
		if (str.equals("")) {
			boardSize = 4; 
			System.out.println("Tamaño de tablero por defecto: 4");
		}
		else
			boardSize = Integer.parseInt(str);
		
		do {
			System.out.print("Selecciona el numero de casillas ocupadas: ");
			str = sc.nextLine();
			if (str.equals("")) {
				initialCells = 2;
				System.out.println("Casillas iniciales por defecto: 2");
			}
			else
				initialCells = Integer.parseInt(str);
		} while ((initialCells > boardSize*boardSize) || (initialCells < 0));	
		
		Game juego = new Game(gameRules, boardSize, initialCells);
		controller.setJuego(juego);
	}	
}
