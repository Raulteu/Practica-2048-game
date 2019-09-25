//Grupo formado por Raul Vindel Y Jose Marquez
package tp.pr1;

import java.util.Scanner;
import Rules.*;
import control.Controller;


public class Game2048 {
	public static int size;
	public static int numInitial;
	public static String reglas;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Selecciona el tamaño del tablero: ");
		size = sc.nextInt();
		
		do {
			System.out.print("Selecciona el numero de casillas ocupadas: ");
			numInitial = sc.nextInt();
		} while ((numInitial > size*size) || (numInitial < 0));	
		Controller controller = new Controller(new Game(new Rules2048(),size, numInitial), new Scanner(System.in)); // recibe un objeto de la clase Game y el Scanner
		controller.run();
		
		sc.close();
	}

}
