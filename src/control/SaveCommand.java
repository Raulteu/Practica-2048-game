package control;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Exceptions.Excepciones;
import Exceptions.noSave;
import Exceptions.fileNameInvalid;
import tp.pr1.Game;

public class SaveCommand extends Command {
	private String file;

	public static String fniE = "Nombre de archivo no valido, no utilice caracteres especiales (los archivos deben ser .txt)";
	public static String noSaveE = "No se guardaran los cambios";

	
	public SaveCommand(String fileName) {
		super ("save", "Guarda el tablero, el tipo de juego y la mejor puntuacion en un archivo de texto");
		this.file = fileName;
	}

	@Override
	public boolean execute(Game game) throws Excepciones, IOException {
		
		game.store(file);
		System.out.println("El juego ha sido guardado con �xito. Utiliza el comando 'load' para cargarlo");
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws Excepciones{
		 
		Scanner se = new Scanner(System.in);
		String aux = "";
		 Command c = null;
		 if (commandWords[0].equals("save") && commandWords.length == 2) {
			
			 if(commandWords[1].endsWith(".txt") && commandWords[1].matches(".*[^ªº?¿!¡ç{}].*")) 
			 {				 
				 File loadName = new File(commandWords[1]);
				 
				 if(loadName.exists()) {
					 System.out.println("Este archivo ya existe, � deseas sobreescribirlo? (S/N):");
					 aux = se.nextLine();
					 while(aux.matches(".*[^sSnN].*")) {
						 System.out.println("Por favor responde 'S' o 'N' ");
						 aux = se.nextLine();
					 }
					 
					 switch (aux) {
						case "N":
						case "n":
							throw new noSave(noSaveE);
					case "s":
						case "S":
							c = new SaveCommand(commandWords[1]);
							break;
					} 
				 }
				 else {
					 c = new SaveCommand(commandWords[1]);
				 }
			 }
			 else {
				 throw new fileNameInvalid(fniE);
			 }
		
		 }
		 return c;
	}

}
