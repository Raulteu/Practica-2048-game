package tp.pr1;

import Rules.GameRules;

public class Cell {
	private int baldosa;

	public Cell(int v){
		this.baldosa = v;
	}
	
	public boolean isEmpty(){
		if (baldosa == 0) 
			return true;
		else
			return false;
	}
	public int doMerge(Cell vecina, GameRules rules){
		return rules.merge(this, vecina);
	}
	
	public int getBaldosa() { //Metodo accedente
		return baldosa;
	}
	
	public void setBaldosa(int baldosa) { //Metodo mutador
		this.baldosa = baldosa; 
	}
	
}
