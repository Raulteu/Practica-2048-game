package Rules;

import tp.pr1.Board;
import tp.pr1.Cell;
import tp.pr1.Position;
import util.MyMathsUtil;
public class RulesFib implements GameRules{
	
	private static final int MAX = 144;
	
	public void addNewCellAt(Board board, Position pos){//que incorpora una célula con valor aleatorio en la posición pos del tablero board	
		board.guardaHuecos();
		//generar nueva baldosa en una posicion aleatoria(90% 1, 10% 2)
			int aux = (int) (Math.random()*board.getContH()-1);

			int x = board.getPosVacia(aux)/10;
			int y = board.getPosVacia(aux)%10;
			int valor = (int) (Math.random()*10);
			if (valor == 7) {
				board.getTablero()[x][y].setBaldosa(2);
			}
			else {
				board.getTablero()[x][y].setBaldosa(1);
			}
	}
	
	public int merge(Cell baldosa, Cell vecina)
	//que fusiona dos cÃ©lulas y devuelve el nÃºmero de puntos obtenido por la fusiÃ³n,
	{
		if ((baldosa.getBaldosa() == MyMathsUtil.nextFib(vecina.getBaldosa())) || 
				((vecina.getBaldosa() == 1) && (baldosa.getBaldosa() == 1)))
		{
			baldosa.setBaldosa(MyMathsUtil.nextFib(baldosa.getBaldosa()));
			vecina.setBaldosa(0);
			return baldosa.getBaldosa();
		}
		else if (vecina.getBaldosa() == MyMathsUtil.nextFib(baldosa.getBaldosa())) {
			baldosa.setBaldosa(MyMathsUtil.nextFib(vecina.getBaldosa()));
			vecina.setBaldosa(0);
			return baldosa.getBaldosa();
		}	
		else
			return 0;		
	}
	
	public int getWinValue(Board board){
		//que devuelve el mejor valor del tablero, según las reglas de ese juego, comprobándose si es un valor ganador y se ha ganado el juego
		
		return board.getMaxValue();
	}
	
	public boolean win(Board board)
	//que devuelve si el juego se ha ganado o no
	{
		if (getWinValue(board) == MAX)
			return true;
		else
			return false;
	}

}