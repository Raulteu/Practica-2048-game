package tp.pr1;

public class MoveResults {

	private boolean moved; //para identificar si ha habido movimiento o fusion de baldosas
	private int points;//para almacenar el numero de puntos obtenidos en el movimiento
	private int maxToken;//para llevar el valor mas alto tras el movimiento 
	
	public MoveResults (boolean moved, int points, int maxToken)
	{
		this.moved = moved;
		this.points = points;
		this.maxToken = maxToken;
	}

	//Metodos accedentes
	public boolean getMoved() {
		return moved;
	}
	public int getPoints() {
		return points;
	}
	public int getMaxToken() {
		return maxToken;
	}
	public void setMoved(boolean moved) {
		this.moved= moved;
	}
	public void setPoints(int points) {
		this.points= points;
	}
	public void setMaxToken(int maxToken) {
		this.maxToken= maxToken;
	}
	
}
