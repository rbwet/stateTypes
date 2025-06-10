package life5observer;

import edu.du.dudraw.DrawListener;

public abstract class LifeObserver implements DrawListener{
	protected GameOfLife subj;
	
	public LifeObserver(GameOfLife s) {
		subj = s;
		s.attach(this);
	}
	
	public abstract void updateObserver();
}
