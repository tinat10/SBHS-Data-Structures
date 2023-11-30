public class State {

	private String name, population;
	private int numReps, incrReps, repPerDistr;
	private int popul = -1;

	public State(String name, String popul, int numReps, int incrReps) {

		this.name = name;
		population = popul;
		this.numReps =  numReps;
		this.incrReps = incrReps;
		repPerDistr = getPopulation()/numReps;

	}

	public State(int input) {
		popul = input;
		repPerDistr = input;
	}


	public void setState(State state) {
		this.name = state.getName();
		population = state.getStringPopul();
		this.numReps =  state.getNumReps();
		this.incrReps = state.getIncrReps();
	}

	public int getRepPerDistr() {
		return repPerDistr;
	}

	public int getNumReps() {
		return numReps;
	}

	public int getIncrReps() {
		return incrReps;
	}

	public String getStringPopul() {
		return population;
	}

	public String getName() {
		return name;
	}

	public void setName(String input) {
		name = input;
	}

	public int getPopulation() {

		if (popul == -1) {
			String converted = "";
			for (int i = 0; i<population.length(); i++) {
				if (population.charAt(i)!=',')
					converted+=population.charAt(i);
			}
			popul = Integer.parseInt(converted);
			return Integer.parseInt(converted);
		}
		else
			return popul;
	}

	public String toString() {
		//return name + ": \n    Population: " + population + "\n    Number of Representatives: " + numReps + "\n    Change in Reps: " + incrReps + "\n";
		return name + " -> " + getPopulation();
	}

}
