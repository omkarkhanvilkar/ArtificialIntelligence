package homework2;

public class State {
	private int missionaries = 0;
	private int canniables = 0;
	private boolean boat = false; // True: Source, false: Destination
	final int MIN = 0;
	final int MAX = 3;

	private static final State GOAL_STATE = new State(0, 0, false);

	public State() {
	}

	public State(int missionaries, int canniables, boolean boat) {
		this.setMissionaries(missionaries);
		this.setCanniables(canniables);
		this.setBoat(boat);
	}

	/**
	 * @return the missionaries
	 */
	public int getMissionaries() {
		return missionaries;
	}

	/**
	 * @param missionaries
	 *            the missionaries to set
	 */
	private void setMissionaries(int missionaries) {
		this.missionaries = missionaries;
	}

	public void addMissionary() {
		int missionaries = this.missionaries + 1;
		this.setMissionaries(missionaries);
	}

	public void subtractMissionaries() {
		int missionaries = this.missionaries - 1;
		this.setMissionaries(missionaries);
	}

	/**
	 * @return the canniables
	 */
	public int getCanniables() {
		return canniables;
	}

	/**
	 * @param canniables
	 *            the canniables to set
	 */
	private void setCanniables(int canniables) {
		this.canniables = canniables;
	}

	public void addCanniable() {
		int canniables = this.canniables + 1;
		this.setCanniables(canniables);
	}

	public void subtractCanniable() {
		int canniables = this.canniables - 1;
		this.setCanniables(canniables);
	}

	/**
	 * @return the boat
	 */
	public boolean isBoat() {
		return boat;
	}

	/**
	 * @param boat
	 *            the boat to set
	 */
	private void setBoat(boolean boat) {
		this.boat = boat;
	}

	public void updateBoat() {
		boolean boat = !this.boat;
		this.setBoat(boat);
	}

	public boolean isGoalState() {
		// Checks is a state is the goal statae
		return this.compareWith(GOAL_STATE);
	}

	public boolean compareWith(State s) {
		// Compares two states
		if (this.isBoat() == s.isBoat() && this.getCanniables() == s.getCanniables()
				&& this.getMissionaries() == s.getMissionaries()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		// String representation of state
		return "Missionaries: " + this.getMissionaries() + "\t Cannibles:" + this.getCanniables() + "\t Boat: "
				+ this.isBoat();
	}

	public boolean isStateValid() {
		// Validity check for the state
		if (this.getCanniables() > this.getMissionaries()) {
			return false;
		} else if (this.getCanniables() < this.MIN || this.getCanniables() > this.MAX) {
			return false;
		} else if (this.getMissionaries() < this.MIN || this.getMissionaries() > this.MAX) {
			return false;
		} else if (CannibalsMissionariesProblem.checkIfClosed(this)) {
			return false;
		} else {
			return true;
		}
	}
}
