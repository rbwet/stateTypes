package life3singleton;

public class AliveState implements CellState{	
	private static final AliveState aliveState = new AliveState();
	
	public static AliveState getAliveState() {
		return aliveState;
	}
	
	private AliveState() {
		
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
		return DeadState.getDeadState();
	}

}
