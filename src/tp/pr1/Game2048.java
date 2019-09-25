//Grupo formado por Raul Vindel Y Jose Marquez
package tp.pr1;

import java.util.Random;
import java.util.Scanner;

import Exceptions.caracterNoValido;
import Rules.*;
import control.Controller;

public class Game2048 {
public static int size;
public static int numInitial;
public static String reglas;
static int randomSeed;
public static Random myRandom;

public static String unE = "Solo se admite un numero";
public static String positivo = "El numero debe ser postitivo";
public static String letra = "Solo se admiten numeros";
public static String demasie = "Selecciona un numero de casillas iniciales menor que ";

	
	public static void main(String[] args)  {
		System.out.println("--------GAME 2048--------");
		//Tratar excepcion de valores incorrectos en args
		try {
			
		size = Integer.parseInt(args[0]);
		numInitial =  Integer.parseInt(args[1]);
		randomSeed = Integer.parseInt(args[2]);
		myRandom = new Random(randomSeed);
		}
		catch (Exception ex){
			System.out.println("Los valores recibidos son incorrectos. Intruzca dos numeros.");
			Scanner sc = new Scanner(System.in);
			try {
				boolean aux;
				do {
					aux = entradaDatosTam(sc);
				} while(!aux);
				
				do {
					System.out.println("Tamanio del tablero guardado: " + size);
					aux = entradaDatosInit(sc);
				} while(!aux);
				
				do {
					aux = entradaDatosSeed(sc);
				} while(!aux);
				myRandom = new Random(randomSeed);
			}
			catch(caracterNoValido e) {
			}
		}
		finally {
		Controller controller = new Controller(new Game(new Rules2048(),size, numInitial, myRandom), new Scanner(System.in)); // recibe un objeto de la clase Game y el Scanner
		controller.run();
		}
	}
	
	
	
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public static boolean entradaDatosTam(Scanner sc) throws caracterNoValido {		
		System.out.print("Selecciona el tamaño del tablero: ");
		String str= null;
		String burgoa[] = null;
		
		try {
	
			str = sc.nextLine();
			burgoa = str.split("\\s+");
				
			if (burgoa[0].equals("")) {
				size = 4; 
				System.out.println("Tama�o de tablero por defecto: 4");
			}
			else {
				if(!isNumeric(burgoa[0]))
					throw new caracterNoValido(letra);
				
				if (isNumeric(burgoa[0])&& burgoa[0].contains("-"))		
					throw new caracterNoValido(positivo);
				
				if(isNumeric(burgoa[0]) && burgoa.length != 1)
					throw new caracterNoValido(unE);
				
				size = Integer.parseInt(burgoa[0]);
			}
	
		}
		catch(caracterNoValido e){
			System.out.println("\n" + e.getMessage());
			return false;		
		}
		return true;
	}
	
	public static boolean entradaDatosInit(Scanner sc) {
		System.out.print("Selecciona el numero de casillas ocupadas: ");
		String str= null;
		String burgoa[] = null;
		try {
			str = sc.nextLine();
			burgoa = str.split("\\s+");
		
			
			if (burgoa[0].equals("")) {
				numInitial = 2; 
				System.out.println("Casillas iniciales por defecto: 2");
			}	
			else{
				if(!isNumeric(burgoa[0]))
					throw new caracterNoValido(letra);
				
				if(burgoa.length != 1)
					throw new caracterNoValido(unE);
			
				if (burgoa[0].contains("-"))		
					throw new caracterNoValido(positivo);
				
				numInitial =  Integer.parseInt(burgoa[0]);
			}
			
			if (numInitial > size * size) {
				throw new caracterNoValido(demasie + size*size);
			}
		}
		catch(caracterNoValido e){
			System.out.println("\n" + e.getMessage());
			return false;
		}
	return true;
	}
	
	
	public static boolean entradaDatosSeed(Scanner sc) throws caracterNoValido {		
		System.out.print("Selecciona la semilla del tablero: ");
		String str= null;
		String burgoa[] = null;
		
		try {

			str = sc.nextLine();
			burgoa = str.split("\\s+");
				
			if (burgoa[0].equals("")) {
				randomSeed = 4; 
				System.out.println("Semilla por defecto: 2");
			}
			else {
//				if(!isNumeric(burgoa[0]))
//					throw new caracterNoValido(letra);
				
				if (isNumeric(burgoa[0])&& burgoa[0].contains("-"))		
					throw new caracterNoValido(positivo);
				
				if(isNumeric(burgoa[0]) && burgoa.length != 1)
					throw new caracterNoValido(unE);
				
				randomSeed = Integer.parseInt(burgoa[0]);
			}

		}
		catch(caracterNoValido e){
			System.out.println("\n" + e.getMessage());
			return false;		
		}
		return true;
	}

}
