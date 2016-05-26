package by.vsu.mf.amm.sta.problem;

import by.vsu.mf.amm.sta.Entity;

abstract public class Problem extends Entity {
	private boolean solved;

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}
}
