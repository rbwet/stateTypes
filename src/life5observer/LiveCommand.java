package life5observer;



public class LiveCommand extends LifeCommand{

	public LiveCommand(Cell newCell) {
		super(newCell);
	}

	@Override
	public void execute() {
		this.receiver.live();
	}

}