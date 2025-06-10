package life5observer;

public class DeadState implements CellState{
	private static final DeadState deadState = new DeadState();
	
	public static DeadState getDeadState() {
		return deadState;
	}
	
	private DeadState() {
		// constructor - does nothing
	}
		
	@Override
	public boolean isAlive() {
		return false;
	}

	@Override
	public CellState live() {
		return AliveState.getAliveState();
	}
	
	@Override
	public CellState die() {
		return this;
	}

}
