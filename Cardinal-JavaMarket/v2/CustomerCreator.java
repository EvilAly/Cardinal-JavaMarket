package v2;

public class CustomerCreator {

	private int minArrival;
	private int maxArrival;
	private int minService;
	private int maxService;

	// to string method
	public String toString() {
		String state = "";
		state = state + "Each customer will arrive between " + this.minArrival + " minutes and " + maxArrival
				+ " minutes apart.";
		state = state + "\nEach customer will complete his or her service in " + minService + " to " + maxService
				+ " minutes.";
		return state;
	}
	
	//getters and setters
	public int getMinArrival() {
		return minArrival;
	}

	public void setMinArrival(int minArrival) {
		this.minArrival = minArrival;
	}

	public int getMaxArrival() {
		return maxArrival;
	}

	public void setMaxArrival(int maxArrival) {
		this.maxArrival = maxArrival;
	}

	public int getMinService() {
		return minService;
	}

	public void setMinService(int minService) {
		this.minService = minService;
	}

	public int getMaxService() {
		return maxService;
	}

	public void setMaxService(int maxService) {
		this.maxService = maxService;
	}
	

}
