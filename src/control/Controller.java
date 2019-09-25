package control;

import java.io.IOException;
import java.util.Scanner;

import Exceptions.Excepciones;
import Exceptions.finException;
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
	
	public void run() {
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
			
			try {
				command = CommandParse.parseCommand(test);
					if (command.execute(game)) {
						System.out.println();
						System.out.println(game.toString());					
					}
			}catch (finException ex) {
				System.out.println(game.toString());					
				System.out.println(ex.getMessage());
			}
			catch(Excepciones | IOException  unknown){
				System.out.println(unknown.getMessage());
			}
			catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println("El tablero cargado no es valido.");
			}
			
		} while (!this.game.getPerdido() && ((!test[0].equalsIgnoreCase("exit")) || (command == null)));
		System.out.println("Hasta pronto :)");
		
	}
}
