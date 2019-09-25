package tp.pr1;
import java.lang.String;

import Rules.GameRules;


public class Board {
	private Cell [ ][ ] _board;
	private int _boardSize;
	private int [ ][ ] _boardState;
	private int []huecos;
	private int contH ;
	
	public Board copyBoard(Board board) {
		Board aux = new Board(_boardSize);
		
		for (int i = 0; i < _boardSize; i++)
			for (int j = 0; j < _boardSize; j++)
				aux._board[i][j].setBaldosa(this._board[i][j].getBaldosa());
		return aux;
	}
	

	public Board(int n) {
		_boardSize = n;
		_board = new Cell [n][n];
		huecos =  new int [n*n]; 
		_boardState = new int [n][n];
		
		this.contH = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				_board [i][j]= new Cell(0);//Inicializa el tablero con todos 0
			}
		}
	}
	
	public Cell[][] getTablero()
	{
		return this._board;
	}
	
	public void setCell(Position pos, int value)//Cambia el valor de la casilla en la posicion pos
	{
		_board [pos.setColumna()][pos.setFila()].setBaldosa(value);
	}
	
	public Cell getCell(Position pos){
		return this._board[pos.setColumna()][pos.setFila()];
	}
	
	
	public MoveResults executeMove(Direction dir, GameRules rules) 
	{
		MoveResults mr = new MoveResults(false, 0, 0);
		int vecino;
		int puntos = 0;
		switch (dir) 
		{
		case RIGHT:
			for (int x = 0; x < _boardSize; x++)
			{
				for (int y = _boardSize-1; y > 0; y--)
				{
					//Se pone LEFT porque se empieza a fusionar desde el extremo de la derecha hasta el de la izquierda
					vecino = findvecino(x, y, Direction.LEFT);
					puntos = _board[x][y].doMerge(_board[x][vecino], rules);
					if (puntos != 0) {
						if (_board[x][y].getBaldosa() > mr.getMaxToken())
							mr.setMaxToken(_board[x][y].getBaldosa());
						mr.setPoints(mr.getPoints() + puntos);
						mr.setMoved(true);
					}
				}
			}
			
			//Despues de hacer todas las fusiones, desplazamos.
			for (int x = 0; x < _boardSize; x++)
				for (int y = _boardSize-1; y > 0; y--) {
					for (int right = y-1; right < _boardSize-1; right++ )
					if (!_board[x][right].isEmpty() && _board[x][right+1].isEmpty())
					{
						_board[x][right+1].setBaldosa(_board[x][right].getBaldosa());
						_board[x][right].setBaldosa(0);
						mr.setMoved(true);						
					}
				}
			
			break;
		case LEFT:
			for (int x = 0; x < _boardSize; x++)
			{
				for (int y = 0; y < _boardSize-1; y++)
				{
					//Se pone RIGHT porque se empieza a fusionar desde el extremo de la derecha hasta el de la izquierda
					vecino = findvecino(x, y, Direction.RIGHT);
					puntos = _board[x][y].doMerge(_board[x][vecino], rules);
					if (puntos != 0) {
						if (_board[x][y].getBaldosa() > mr.getMaxToken())
							mr.setMaxToken(_board[x][y].getBaldosa());
						mr.setPoints(mr.getPoints() + puntos);
						mr.setMoved(true);
					}
				}
			}
			
			for(int x= 0; x < _boardSize; x++ )
				for(int y= 1; y < _boardSize; y++) {
					for (int left = y; left > 0; left--) {
						if (!_board[x][left].isEmpty() && _board[x][left-1].isEmpty())
						{
								_board[x][left-1].setBaldosa(_board[x][left].getBaldosa());
								_board[x][left].setBaldosa(0);
								mr.setMoved(true);
						}
					}
				}
			break;
		case DOWN:
			for (int x = _boardSize-1; x > 0; x--)
			{
				for (int y = 0; y < _boardSize; y++)
				{
					//Se pone UP porque se empieza a fusionar desde el extremo de la derecha hasta el de la izquierda
					vecino = findvecino(x, y, Direction.UP);
					puntos = _board[x][y].doMerge(_board[vecino][y], rules);
					if (puntos != 0) {
						if (_board[x][y].getBaldosa() > mr.getMaxToken())
							mr.setMaxToken(_board[x][y].getBaldosa());
						mr.setPoints(mr.getPoints() + puntos);
						mr.setMoved(true);
					}
				}
			}
			
			for (int x = _boardSize-2; x >= 0; x--)
				for (int y = 0; y < _boardSize; y++)
					for (int down = x; down < _boardSize-1; down++) {
						if (!_board[down][y].isEmpty() && _board[down+1][y].isEmpty())
						{	
							_board[down+1][y].setBaldosa(_board[down][y].getBaldosa());
							_board[down][y].setBaldosa(0);
							mr.setMoved(true);
						}
					}
			break;
		case UP:
			for (int x = 0; x < _boardSize-1; x++)
			{
				for (int y = 0; y < _boardSize; y++)
				{
					//Se pone DOWN porque se empieza a fusionar desde el extremo de la derecha hasta el de la izquierda
					vecino = findvecino(x, y, Direction.DOWN);
					puntos = _board[x][y].doMerge(_board[vecino][y], rules);
					if (puntos != 0) {
						if (_board[x][y].getBaldosa() > mr.getMaxToken())
							mr.setMaxToken(_board[x][y].getBaldosa());
						mr.setPoints(mr.getPoints() + puntos);
						mr.setMoved(true);
					}
				}
			}
			
			for (int x = 1; x < _boardSize; x++)
				for (int y = 0; y < _boardSize; y++)
					for (int up = x; up > 0; up--) {
						if (!_board[up][y].isEmpty() && _board[up-1][y].isEmpty()){
								_board[up-1][y].setBaldosa(_board[up][y].getBaldosa());
								_board[up][y].setBaldosa(0);
								mr.setMoved(true);
						}
					}
			break;
		}
		return mr;
	}
	
	
	public int findvecino(int x, int y, Direction dir) {
		boolean b = false;
		int ret = 0;
		switch(dir)
		{
		case RIGHT:
			ret = y+1;
			while (y < this._boardSize-1 && !b) {
			y++;
			if (this._board[x][y].getBaldosa() != 0) {
				b = true;
				ret = y;
				}
			}
			break;
		case LEFT:
			ret = y - 1;
			while (y > 0 && !b) {
			y--;
			if (this._board[x][y].getBaldosa() != 0) {
				b = true;
				ret = y;
				}
			}
			break;
		case DOWN:
			ret = x +1;
			while (x < this._boardSize-1 && !b) {
			x++;
			if (this._board[x][y].getBaldosa() != 0) {
				b = true;
				ret = x;
				}
			}
			break;
		case UP:
			ret = x -1;
			while (x > 0 && !b) {
			x--;
			if (this._board[x][y].getBaldosa() != 0) {
				b = true;
				ret = x;
				}
			}
			break;
		}
		
		return ret;
	}
	
	
	@Override
	public String toString() 
	{
        String newLine = "\n";
		String space = " ";
		String vert = "|";
		String horz = "-";
		
		String str = new String();		
        str = "";
        
        for(int h = 0; h < _boardSize; h++) {
        	str = str + horz + horz + horz + horz + horz + horz; 	
        }
        str = str + horz + newLine;
        
		for (int i = 0; i < _boardSize; i++) 
		{
			for (int j = 0; j < _boardSize; j++)
			{
				str = str + vert;
				if (_board[i][j].getBaldosa() > 999)
					str += space + _board[i][j].getBaldosa();
				else if (_board[i][j].getBaldosa() > 99 )
					str += space + _board[i][j].getBaldosa() + space;
				else if (_board[i][j].getBaldosa() > 9)
					str += space + space + _board[i][j].getBaldosa() + space;
				else if (_board[i][j].getBaldosa() > 0)
					str += space + space + _board[i][j].getBaldosa() + space + space;
				else if (_board[i][j].getBaldosa() == 0)
					str += space + space + space + space + space;

			}
			str = str + vert + newLine;
			for(int h2 = 0; h2 < _boardSize; h2++) {
	        	str = str + horz + horz + horz + horz + horz + horz; 	
	        }
	        str = str + horz + newLine;
		} 
		return str;
	} 
	
// Guardar y Establecer Estado
	public int[ ][ ] getState(){ //produce la representación compacta a partir del estado del tablero actual,
		
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board.length; j++) {
				_boardState[i][j]= _board[i][j].getBaldosa();	
			}			
		}
		return _boardState;
	}
	
	
	public void setState(int[ ][ ] aState)	{
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board.length; j++) {
				_board[i][j].setBaldosa(aState[i][j]);	
			}			
		}
	}
	
	
//	Array con las Posiciones vacias
	public void guardaHuecos(){
		contH = 0;
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j <_boardSize; j++) {
				if(_board[i][j].isEmpty()) {
					huecos[contH]= (i*10+j);
					contH++;
				}
			}
		}
	}
	
	public int getContH(){
		return contH;
	}
	
	public void setContH(int contH){
		this.contH = contH;
	}
	
	public int getPosVacia(int pos){
		return huecos[pos];
	}


// Maximo y minimo del tablero

		public int getMaxValue() {
			int max =0;
			for (int i = 0; i < _board.length; i++) {
				for (int j = 0; j < _board.length; j++) {
					if (_board[i][j].getBaldosa() > max) {
						max =_board[i][j].getBaldosa();
					}
				}
			}
			return max;
		}
		
		public int getMinValue() {
			int min =2048;
			for (int i = 0; i < _board.length; i++) {
				for (int j = 0; j < _board.length; j++) {
					if ((_board[i][j].getBaldosa() < min)&&(_board[i][j].getBaldosa()!= 0)) {
						min =_board[i][j].getBaldosa() ;
					}
				}
			}
			return min;
		}
}



