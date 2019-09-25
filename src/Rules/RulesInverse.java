package Rules;

import java.util.Random;

import tp.pr1.Board;
import tp.pr1.Cell;
import tp.pr1.Position;

public class RulesInverse implements GameRules{

	public void addNewCellAt(Board board, Position pos, Random myRandom)
	//que incorpora una celula con valor aleatorio en la posicion pos del tablero board,
	{
		int valor = (int) (myRandom.nextDouble()*10);
		if (valor == 7) {
			board.getTablero()[pos.setFila()][pos.setColumna()].setBaldosa(1024);
		}
		else {
			board.getTablero()[pos.setFila()][pos.setColumna()].setBaldosa(2048);
		}
	}
	
	public int merge(Cell baldosa, Cell vecina)
	//que fusiona dos celulas y devuelve el numero de puntos obtenido por la fusion,
	{
		int ptInv = 0;
		if (baldosa.getBaldosa() == vecina.getBaldosa()) {
			baldosa.setBaldosa(vecina.getBaldosa() / 2);
			vecina.setBaldosa(0);
			switch (baldosa.getBaldosa()) {
			case 1024:
				 ptInv = 2;
				break;
			case 512:
				 ptInv = 4;
				break;
			case 256:
				 ptInv = 8;
				break;
			case 128:
				 ptInv = 16;
				break;
			case 64:
				 ptInv = 32;
				break;
			case 32:
				 ptInv = 64;
				break;
			case 16:
				 ptInv = 128;
				break;
			case 8:
				 ptInv = 256;
				break;
			case 4:
				 ptInv = 512;
				break;
			case 2:
				 ptInv = 1024;
				break;
			case 1:
				 ptInv = 2048;
				break;
			}
			return ptInv;
			}
		else
			return 0;	
	}
	
	public int getWinValue(Board board)
	//que devuelve el mejor valor del tablero, según las reglas de ese juego, comprobándose si es un valor ganador y se ha ganado el juego,
	{
		return board.getMinValue();
	}
	
	public boolean win(Board board)
	//que devuelve si el juego se ha ganado o no
	{
		if (getWinValue(board) == 2)
			return true;
		else
			return false;
	}

}
