package control;

import java.util.Random;
import java.util.Scanner;
import Exceptions.caracterNoValido;
import Exceptions.comandoDesconocido;
import Exceptions.juegoDesconocido;
import Rules.GameRules;
import control.GameType;
import tp.pr1.Game;

public class PlayCommand extends Command{
protected int boardSize, initialCells, seed;
protected GameType gameType;
protected Random myRandom;

public static String unE = "Solo se admite un numero";
public static String positivo = "El numero debe ser postitivo";
public static String letra = "Solo se admiten numeros";
public static String demasie = "Selecciona un numero de casillas iniciales menor que ";



public PlayCommand(GameType gameType, GameRules gameRules) {
	super("play", "Selecciona el juego que quieres empezar (FIB, INVERSE o 2048)");
	this.gameType = gameType;
}

public Command parse(String[] commandWords) throws juegoDesconocido, caracterNoValido{
	Command c = null;
	Scanner sc = new Scanner(System.in);
	boolean aux;
	if (commandWords[0].equals("play") && commandWords.length == 2) { 
		gameType = GameType.parse(commandWords[1]);	
		if (gameType != null) {
			c = new PlayCommand(gameType, gameType.getRules());	
			
			do {
				aux = entradaDatosTam(sc);
			} while(!aux);
			System.out.println("Tamanio del tablero guardado: " + boardSize);
			do {
				aux = entradaDatosInit(sc);
			} while(!aux);
			System.out.println("Numero de celdas iniciales guardado: " + initialCells);
			do {
				aux = entradaDatosSeed(sc);
			} while(!aux);
			myRandom = new Random(seed);
			System.out.println("Semilla guardada: " + seed);
			return this;
		}
		else
			throw new juegoDesconocido("Juego desconocido. (Juegos validos: fib, inverse, original)");
	}
	else
		return null;	
}

public boolean execute(Game game) {
	
	game.play(gameType.getRules(), boardSize, initialCells, myRandom);
	return true;		
}

public static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}
	
public boolean entradaDatosTam(Scanner sc) throws caracterNoValido {		
	System.out.print("Selecciona el tamaño del tablero: ");
	String str= null;
	String burgoa[] = null;
	
	try {

		str = sc.nextLine();
		burgoa = str.split("\\s+");
			
		if (burgoa[0].equals("")) {
			boardSize = 4; 
			System.out.println("Tamanio de tablero por defecto: 4");
		}
		else {
			if(!isNumeric(burgoa[0]))
				throw new caracterNoValido(letra);
			
			if (isNumeric(burgoa[0])&& burgoa[0].contains("-"))		
				throw new caracterNoValido(positivo);
			
			if(isNumeric(burgoa[0]) && burgoa.length != 1)
				throw new caracterNoValido(unE);
			
			boardSize = Integer.parseInt(burgoa[0]);
		}

	}
	catch(caracterNoValido e){
		System.out.println("\n" + e.getMessage());
		return false;		
	}
	return true;
}

public boolean entradaDatosInit(Scanner sc) {
	System.out.print("Selecciona el numero de casillas ocupadas: ");
	String str= null;
	String burgoa[] = null;
	try {
		str = sc.nextLine();
		burgoa = str.split("\\s+");
	
		
		if (burgoa[0].equals("")) {
			initialCells = 2; 
			System.out.println("Casillas iniciales por defecto: 2");
		}	
		else{
			if(!isNumeric(burgoa[0]))
				throw new caracterNoValido(letra);
			
			if(burgoa.length != 1)
				throw new caracterNoValido(unE);
		
			if (burgoa[0].contains("-"))		
				throw new caracterNoValido(positivo);
			
			initialCells =  Integer.parseInt(burgoa[0]);
		}
		
		if (initialCells > boardSize * boardSize) {
			throw new caracterNoValido(demasie + boardSize*boardSize);
		}
	}
	catch(caracterNoValido e){
		System.out.println("\n" + e.getMessage());
			return false;
		}
	return true;
	}
public boolean entradaDatosSeed(Scanner sc) throws caracterNoValido {		
	System.out.print("Selecciona la semilla del tablero: ");
	String str= null;
	String burgoa[] = null;
	
	try {

		str = sc.nextLine();
		burgoa = str.split("\\s+");
			
		if (burgoa[0].equals("")) {
			seed = 2; 
			System.out.println("Semilla por defecto: 2");
		}
		else {
			if(!isNumeric(burgoa[0]))
				throw new caracterNoValido(letra);
			
			if (isNumeric(burgoa[0])&& burgoa[0].contains("-"))		
				throw new caracterNoValido(positivo);
			
			if(isNumeric(burgoa[0]) && burgoa.length != 1)
				throw new caracterNoValido(unE);
			
			seed = Integer.parseInt(burgoa[0]);
		}

	}
	catch(caracterNoValido e){
		System.out.println("\n" + e.getMessage());
		return false;		
	}
	return true;
}
}
