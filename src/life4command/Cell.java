package life4command;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	
	private CellState theState;
	private List<Cell> neighbors;
	
	public Cell() {
		theState = DeadState.getDeadState();
		neighbors = new ArrayList<Cell>();
	}
	
	public void live() {
		theState = theState.live();
	}
	
	public void die() {
		theState = theState.die();
	}
	
	public boolean isAlive() {
		return theState.isAlive();
	}
	
	public void addNeighbor(Cell c) {
		neighbors.add(c);
	}
	
	public int nbrAliveNeighbors() {
		int n = 0;
		for (Cell i : neighbors) {
			if (i.isAlive()) {
				n++;
			}
		}
		
		return n;
	}
}
