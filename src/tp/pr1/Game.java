package tp.pr1;

import Rules.GameRules;

public class Game {
	 
	private Board board; // Tablero
	private int currentSize; // Dimension del tablero
	private int currentInitCells; // Numero de baldosas no nulas iniciales
	//private Random myRandom; // Comportamiento aleatorio del juego
	private MoveResults mr;
	private boolean perdido;
	private GameStateStack undoes = new GameStateStack();
	private GameStateStack redoes = new GameStateStack();
	private GameRules currentRules;
	
	public Game(GameRules rules, int size, int initCells) {
		
		currentRules = rules;
		currentSize = size;
		currentInitCells = initCells;
		//this.myRandom = myRandom;
		this.perdido = false;
		board = rules.createBoard(currentSize);
		rules.initBoard(board, currentInitCells);
		this.mr = new MoveResults(true, 0, 0);
	}
	
	public MoveResults getResult()
	{
		return this.mr;
	}
	
	public void move(Direction dir) {
		int maxpuntos = mr.getMaxToken();
		undoes.push(getState());
		int puntos = mr.getPoints();
		this.mr = board.executeMove(dir, currentRules);
		this.mr.setPoints(puntos + mr.getPoints());
		if (maxpuntos > mr.getMaxToken())
			this.mr.setMaxToken(maxpuntos);
		if (this.mr.getMoved() == true) {		
			currentRules.addNewCell(board);
			}
		board.guardaHuecos();

		//Comprobamos si se ha perdido
		if (currentRules.lose(board, currentSize) == true) {
			this.perdido = true;
			System.out.println("Has perdido");
		}
		if (currentRules.win(board) == true)
		{
			System.out.println("Enhorabuen, has ganado! :)");
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
			currentRules.addNewCell(board);
			i++;	
		}
		board.guardaHuecos();
	}
	
	public String help() {
		String str = "Move <direction>: execute a move in one of the four directions (up, down, left, right) \n"
				+ "Play: cambia el modo de juego (FIB, INV o ORIG)\n"
				+ "Reset: start a new game \n"
				+ "Help: print this help message \n"
				+ "Exit: finish the program";
		return str;
	}

	@Override
	public String toString() {

		
		return board.toString() + "\n" + "Mejor Puntuacion: " +  currentRules.getWinValue(board) + "  Puntos: " + mr.getPoints() + "\n";

	}

	
	public void undo(){
		if(undoes.getContGS() > 0){
			redoes.push(getState());
			setState(undoes.pop());	
			
		}
		else{
			System.out.println("No se puede deshacer el movimiento");
		}
	}
	
	public void redo(){
		if(redoes.getContGS() > 0){
			undoes.push(getState());
			setState(redoes.pop());	
		
		}
		else{
			System.out.println("No se puede des-deshacer el movimiento");
		}
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


}
