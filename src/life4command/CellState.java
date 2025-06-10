package life4command;

public abstract interface CellState {
	
	public CellState live();
	public CellState die();
	public boolean isAlive(); 
	
}
