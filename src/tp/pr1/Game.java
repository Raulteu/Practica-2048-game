package tp.pr1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.io.IOException;

import Exceptions.Excepciones;
import Exceptions.fileNameInvalid;
import Exceptions.fileNot2048;
import Exceptions.finException;
import Exceptions.invalidInitCells;
import Exceptions.invalidRules;
import Exceptions.redoException;
import Exceptions.undoException;

import Rules.GameRules;
import control.Command;
import control.CommandParse;
import control.GameType;

public class Game {
	 
	private Board board; // Tablero
	private int currentSize; // Dimension del tablero
	private int currentInitCells; // Numero de baldosas no nulas iniciales
	private Random myRandom; // Comportamiento aleatorio del juego
	private MoveResults mr;
	private boolean perdido;
	private GameStateStack undoes = new GameStateStack();
	private GameStateStack redoes = new GameStateStack();
	private GameRules currentRules;
	
	public static String undoE = "No se puede deshacer el movimiento";
	public static String redoE = "No se puede des-deshacer el movimiento";
	public static String loseE = "Juego terminado. HAS PERDIDO.";
	public static String winE = "Juego terminado. ENHORABUENA, HAS GANADO.";
	public static String invCic = "El numero de celdas iniciales del archivo no es valido";
	public static String  invR = "El tipo de juego guardado no es valido, se mantendran las reglas actuales.";


	
	public Game(GameRules rules, int size, int initCells, Random myRandom) {
		
		currentRules = rules;
		currentSize = size;
		currentInitCells = initCells;
		this.myRandom = myRandom;
		this.perdido = false;
		board = rules.createBoard(currentSize);
		rules.initBoard(board, currentInitCells, myRandom);
		this.mr = new MoveResults(true, 0, 0);
	}
	
	public void play(GameRules rules, int size, int initCells, Random myRandom) {
		this.currentInitCells = initCells;
		this.currentRules = rules;
		this.currentSize = size;
		this.board = rules.createBoard(size);
		rules.initBoard(this.board, initCells, myRandom);
		this.mr = new MoveResults(true, 0, 0);
	}
	
	public MoveResults getResult()
	{
		return this.mr;
	}
	
	public void move(Direction dir) throws finException {
		int maxpuntos = mr.getMaxToken();
		undoes.push(getState());
		int puntos = mr.getPoints();
		this.mr = board.executeMove(dir, currentRules);
		this.mr.setPoints(puntos + mr.getPoints());
		if (maxpuntos > mr.getMaxToken())
			this.mr.setMaxToken(maxpuntos);
		if (this.mr.getMoved() == true) {		
			currentRules.addNewCell(board, myRandom);
			}
		board.guardaHuecos();

		//Comprobamos si se ha perdido
		if (currentRules.lose(board, currentSize) == true) {
			this.perdido = true;
			throw new finException(loseE);
		}
		if (currentRules.win(board) == true)
		{
			throw new finException(winE);
		}
	
		
	}
	
	public boolean getPerdido() {
		return this.perdido;
	}
	
	public void setPerdido(boolean bool) {
		this.perdido = bool;
	}
	
	
	public void reset() {
		this.perdido = false;
		this.board = new Board(currentSize);
		this.mr = new MoveResults(true, 0, 0);
	//	this.gs = new GameState(0,0,null);
		int i = 0;
		while(i < currentInitCells)
		{	
			currentRules.addNewCell(board, myRandom);
			i++;	
		}
		board.guardaHuecos();
	}
	
	public String help() {
		String str = "";
		for (Command c : CommandParse.availableCommands) {
			str = str + "\n" + c.helpText();
			
		}
		return str;
	}

	@Override
	public String toString() {
		return board.toString() + "\n" + "Mejor Puntuacion: " +  currentRules.getWinValue(board) + "  Puntos: " + mr.getPoints() + "\n";
	}

	
	public boolean undo() throws undoException{
		if(undoes.getContGS() > 0){
			redoes.push(getState());
			setState(undoes.pop());	
			return true;
		}
		else
			throw new undoException(undoE);
	}
	
	public boolean redo() throws redoException{
		if(redoes.getContGS() > 0){
			undoes.push(getState());
			setState(redoes.pop());	
			return true;
		}
		else
			throw new redoException(redoE);
	}
	

	
	public GameState getState()	{
		
		Board aux= new Board(currentSize);
		aux.setState(board.getState());
		GameState gs = new GameState(mr.getPoints(), mr.getMaxToken(), aux.getState());
	
		return gs;
	}
	
	public void setState(GameState aState){
		for (int i = 0; i < currentSize; i++) {
			for (int j = 0; j < currentSize; j++) {
				board.getTablero()[i][j].setBaldosa(aState.getBoardState()[i][j]); 
			}
		}
	}

//Guardado y Carga de Archivos
	
	public GameRules getRules() {
		return currentRules;
	}
	
	public void load(String nombre, FileReader fr, BufferedReader entrada) throws fileNot2048, IOException, ArrayIndexOutOfBoundsException, Excepciones{
		
		Board board2 = board.load(nombre, fr, entrada);
		String[] ipr = entrada.readLine().split(" ");
		int cica = Integer.parseInt( ipr[0]);
		int pointsaux = (Integer.parseInt(ipr[1]));
		
		if (cica < board2.getTamano()* board2.getTamano())
		{
			switch (ipr[2]) {
			case "original":
				this.board = board2;
				this.mr.setPoints(pointsaux);
				currentInitCells = cica;
				currentRules = GameType.ORIG.getRules();
				break;
			case "fib":
				this.board = board2;
				this.mr.setPoints(pointsaux);
				currentInitCells = cica;
				currentRules = GameType.FIB.getRules();
				break;
			case "inverse":
				this.board = board2;
				this.mr.setPoints(pointsaux);
				currentInitCells = cica;
				currentRules = GameType.INVERSE.getRules();
				break;
			default:
				throw new invalidRules(invR);	
			}
		}
		else
		{
			throw new invalidInitCells(invCic);
		}
				

		
			undoes = new GameStateStack();
			redoes = new GameStateStack();
		entrada.close();
		
}
	
	public void store (String nombre) throws fileNameInvalid, IOException{
		
		String rules = null;
		
		FileWriter fw = new FileWriter(nombre,true);
		BufferedWriter buffer = new BufferedWriter(fw);
		PrintWriter salida = new PrintWriter(buffer);
		
		board.store(nombre);
		rules = currentRules.toString();
			
			if (rules.contains("Rules2048"))
				rules = "original";
			else if (rules.contains("RulesFib"))
				rules = "fib";
			else if (rules.contains("RulesInverse"))
				rules = "inverse";
		
		salida.println(currentInitCells + " " + mr.getPoints() + " "+ rules);
		salida.close();
	}
	public Game getGame() {
		return this;
	}
}
