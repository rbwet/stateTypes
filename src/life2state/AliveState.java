package life2state;

public class AliveState implements CellState{	
	
	public AliveState() {
		
	}
	
	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public CellState live() {
		return this;
	}

	@Override
	public CellState die() {
		return new DeadState();
	}

}
