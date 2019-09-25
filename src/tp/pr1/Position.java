package tp.pr1;

public class Position {
	private int fila, columna;
	
	public Position(int x, int y) {
		this.fila = x;
		this.columna = y;
	}
	
	public boolean valida(int fila, int columna, int tam)
	{
		return ((fila < tam) && (columna < tam) && (fila > 0) && (columna > 0));
	}
	

	public int setFila(){
		return fila;
	}
	public int setColumna() {
		return columna;
	}

}
