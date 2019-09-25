package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Exceptions.Excepciones;
import Exceptions.fileNameInvalid;
import Exceptions.fileNot2048;
import tp.pr1.Game;

public class LoadCommand extends Command {
	
	private String archivo;
	public static String loadE = "Este archivo no contiene ningun juego guardado";
	public static String fniE = "Nombre de archivo no valido, no utilice caracteres especiales (los archivos deben ser .txt)";
	
	public LoadCommand(String fileName) {
		super ("load", "Carga el tablero, el tipo de juego y la mejor puntuacion de un archivo de texto");
		this.archivo = fileName;
	}
	
	@Override
	public boolean execute(Game game) throws Excepciones, IOException, ArrayIndexOutOfBoundsException{
		
		FileReader fr = new FileReader(archivo);
		BufferedReader entrada = new BufferedReader(fr);
		String s = entrada.readLine();
		
		
		 if (s.equals("This stores a saved 2048 game")) {
			 game.load(archivo, fr , entrada);
			 String aux = game.getRules().toString();
			 if (aux.contains("Rules2048"))
					aux = "original";
				else if (aux.contains("RulesFib"))
					aux = "fibonacci";
				else if (aux.contains("RulesInverse"))
					aux = "inverse";
			 
			System.out.println("El juego ha sido cargado con �xito: 2048, " + aux + " versi�n");	
		 }
	 
		 else {
			 throw new fileNot2048(loadE);
		 }
		 return true;
	}

	@Override
	public Command parse(String[] commandWords) throws Excepciones, IOException {
		
		if (commandWords[0].equals("load") && commandWords.length == 2) {
			
			Command c = null;

			 if(commandWords[1].endsWith(".txt") && commandWords[1].matches(".*[^ªº?¿!¡ç{}].*")) {
				 File loadName = new File(commandWords[1]);
				 if (loadName.exists()) {
					 c = new LoadCommand(commandWords[1]);
				 }
				 else {
					 throw new FileNotFoundException(loadE);
				 }
			 }
			 else {
				 throw new fileNameInvalid(fniE);
			 }
			return c;
		}
		return null;
		
	}


}
