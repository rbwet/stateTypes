package life2state;

public abstract interface CellState {
	
	public CellState live();
	public CellState die();
	public boolean isAlive();
	
}
