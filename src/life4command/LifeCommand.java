package life4command;

public abstract class LifeCommand {
	protected Cell receiver;
	
	public LifeCommand(Cell newCell) {
		receiver = newCell;
	}

	public abstract void execute();
}
