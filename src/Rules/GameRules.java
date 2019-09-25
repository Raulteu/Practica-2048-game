package Rules;

import java.util.Random;

import tp.pr1.Board;
import tp.pr1.Cell;
import tp.pr1.Direction;
import tp.pr1.Position;

public interface GameRules{

	void addNewCellAt(Board board, Position pos, Random myRandom);
	//que incorpora una celula con valor aleatorio en la posicion pos del tablero board,
	
	int merge(Cell self, Cell other);
	//que fusiona dos celulas y devuelve el numero de puntos obtenido por la fusion
	
	int getWinValue(Board board);
	//que devuelve el mejor valor del tablero, segun las reglas de ese juego, comprobandose si es un valor ganador y se ha ganado el juego,
	
	boolean win(Board board);
	//que devuelve si el juego se ha ganado o no
	
	default boolean lose(Board board, int size) { //que devuelve si el juego se ha perdido o no.
		boolean lose = true;
		if (board.getContH() == 0)
		{
			Board aux = board.copyBoard(board);
			if (!aux.executeMove(Direction.RIGHT, this).getMoved() && !aux.executeMove(Direction.LEFT, this).getMoved()
				&& !aux.executeMove(Direction.DOWN, this).getMoved() && !aux.executeMove(Direction.UP, this).getMoved())
			{
				lose = true;
			}
			else
				lose = false;
		}
		else
			lose = false;
		
		return lose;
	}
	
	default Board createBoard(int size) {
	//Cuya implementacion por defecto crea y devuelve un tablero 
		return new Board(size);
	}
	
	default void addNewCell(Board board, Random myRandom) {
	//Cuya implementacion por defecto elige una posicion libre de board e invoca el metodo addNewCellAt() para añadir una celula en esa posicion
		board.guardaHuecos();

		//generar nueva baldosa en una posicion aleatoria(90% 2, 10% 4)
			int aux = (int) (myRandom.nextDouble()*board.getContH()-1);
			int x = board.getPosVacia(aux)/10;
			int y = board.getPosVacia(aux)%10;
			Position pos = new Position(x, y);

			addNewCellAt(board, pos, myRandom);
	}
	
	default void initBoard(Board board, int initCells, Random myRandom) {
	//Cuya implementacion por defecto inicializa board eligiendo numCells posiciones libres,
	//e invoca el metodo addNewCellAt() para añadir nuevas celulas en esas posiciones.
		int i = 0; 
		while (i < initCells)
		{
			addNewCell(board, myRandom);
			i++;
		}
		board.guardaHuecos();
	}

	
}
