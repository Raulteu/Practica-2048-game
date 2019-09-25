package tp.pr1;

public class GameState {
	private int score;
	private int highest;
	private int [][]boardState;
	
	public GameState(int score, int highest, int[][] boardState) {
		this.score = score;
		this.highest = highest;
		this.boardState = boardState;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getHighest(){
		return highest;
	}
	
	public int [][]getBoardState(){
		return boardState;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public void setHighest(int highest){
		this.highest= highest;
	}
	
	public void setBoardState(int [][]boardState){
		this.boardState = boardState;
	}
}
