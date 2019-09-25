package control;

import java.util.Scanner;

//import tp.pr1.Direction;
import tp.pr1.Game;


public class Controller {

	private Game game;
	private Scanner in;

	
	public Controller(Game game, Scanner in){
		this.game = game;
		this.in = in;
	}
	
	public void setJuego(Game juego)
	{
		this.game = juego;
	}
	
	public void setIn(Scanner in)
	{
		this.in = in;
	}
	
	public Game getJuego()
	{
		return this.game;
	}
	
	public Scanner getIn()
	{
		return this.in;
	}
	
	public void run(){
		//Muestra el estado del juego (toString de Game)
		System.out.println(game.toString());
		
		//Solicita una orden al usuario
		Command command = null;
		String str;
		String [] test;
		do {
			System.out.print("Command >> ");
			str = this.in.nextLine();
			test = str.trim().split("\\s+"); 
			test[0].toLowerCase();
			command = CommandParse.parseCommand(test, this);
			//Direction dir = null;
			if (command != null) {
				//Ejecutar command
				command.execute(game, this);
			} else {
				System.out.println("Unknown Command. Use ’help’ to see the available commands");
			}
			
			if (!test[0].equals("exit")) {
				System.out.println();
				System.out.println(game.toString());
			}
			
		} while (!this.game.getPerdido() && ((!test[0].equalsIgnoreCase("exit")) || (command == null)));
		System.out.println("Hasta pronto :)");
		
	}
}
