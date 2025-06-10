package life3singleton;


public abstract interface CellState {
	
	public CellState live();
	public CellState die();
	public boolean isAlive();
	
}
