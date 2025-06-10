package life2state;

public class DeadState implements CellState{

	public DeadState() {
		// constructor - does nothing
	}
		
	@Override
	public boolean isAlive() {
		return false;
	}

	@Override
	public CellState live() {
		return new AliveState();
	}
	
	@Override
	public CellState die() {
		return this;
	}

}
