package tp.pr1;

public class GameStateStack {
	private static final int CAP = 20;
	private GameState []estados = new GameState[CAP + 1];
	private int cont;
	
	public GameState pop() 
	{
		GameState aux = estados[cont-1];
		cont--;
		return aux;	
		
	}
	
	
	public void push(GameState state)
	{
		if (cont < CAP) {
				cont ++;
				estados[cont-1] = state;
		}
		else {
			for (int i = 0; i < CAP-1; i++) {
				estados[i] = estados[i+1];
			}
			estados[CAP -1] = state;
		}
	}
	
	
	public boolean isEmpty(){
		
		boolean vacio = false;
		for (int i = 0; i < cont; i++) {
			if(estados[i] == null){
				vacio = true;
			}
		}
		return vacio;
	}
	
	public int getContGS(){
		return cont;
	}
}
