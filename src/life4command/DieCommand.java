package life4command;

public class DieCommand extends LifeCommand{

	public DieCommand(Cell newCell) {
		super(newCell);
	}

	@Override
	public void execute() {
		this.receiver.die();
	}

}
