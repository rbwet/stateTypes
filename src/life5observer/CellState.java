package life5observer;

public abstract interface CellState {
	
	public CellState live();
	public CellState die();
	public boolean isAlive(); // getter for current state
	
}
