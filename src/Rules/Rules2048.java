package Rules;

import tp.pr1.Board;
import tp.pr1.Cell;
import tp.pr1.Position;

public class Rules2048 implements GameRules{
	
	
	public void addNewCellAt(Board board, Position pos){	
		//que incorpora una celula con valor aleatorio en la posicion pos del tablero board
			int valor = (int) (Math.random()*10);
			if (valor == 7) {
				board.getTablero()[pos.setFila()][pos.setColumna()].setBaldosa(4);
			}
			else {
				board.getTablero()[pos.setFila()][pos.setColumna()].setBaldosa(2);
			}
	}
	
	public int merge(Cell baldosa, Cell vecina)
	//que fusiona dos celulas y devuelve el numero de puntos obtenido por la fusion,
	{
		if (baldosa.getBaldosa() == vecina.getBaldosa()) {
			baldosa.setBaldosa(vecina.getBaldosa() * 2);
			vecina.setBaldosa(0);
			return baldosa.getBaldosa();
			}
		else
			return 0;		
	}
	
	public int getWinValue(Board board)
	//que devuelve el mejor valor del tablero, segun las reglas de ese juego, comprobandose si es un valor ganador y se ha ganado el juego,
	{
		return board.getMaxValue();
	}
	
	public boolean win(Board board)
	//que devuelve si el juego se ha ganado o no
	{
		if (board.getMaxValue() == 2048)
			return true;
		else
			return false;
	}
	
}
