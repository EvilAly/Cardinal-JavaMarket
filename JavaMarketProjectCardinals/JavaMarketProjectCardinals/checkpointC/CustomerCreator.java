package checkpointC;

import java.util.Random;

//This class aids in customer creation
public class CustomerCreator {

	private int minArrival;
	private int maxArrival;
	private int minService;
	private int maxService;
	private int selfServPenalty;
	private int[] arrivalTimes; // An array of arrival times
	private int[] serviceTimes; // An array of service times
	private int numOfCusts; // See how many customers

	// Constructors
	public CustomerCreator() {

	}

	public CustomerCreator(int minA, int maxA, int minS, int maxS, int n) {
		// Throw illegal argument exceptions if the maximums are less than the maximums
		minArrival = minA;
		maxArrival = maxA;
		if (maxArrival < minArrival) {
			throw new IllegalArgumentException();
		}

		minService = minS;
		maxService = maxS;
		if (maxService < minService) {
			throw new IllegalArgumentException();
		}
		
		numOfCusts = n;

		// Create arrays of random arrival times and service times based on number of
		// customers
		generateArrivalTimes();
		generateServiceTimes();

	}

	// to string method
	public String toString() {
		String state = "";
		state = state + "Each customer will arrive between " + this.minArrival + " minutes and " + maxArrival
				+ " minutes apart.";
		state = state + "\nEach customer will complete his or her service in " + minService + " to " + maxService
				+ " minutes.";
		return state;
	}

	// This method generates an array of exact arrival times for each customer
	public void generateArrivalTimes() {
		// Get the arrival times that come from the ranges
		int[] arrivalsInRange = new int[numOfCusts];
		arrivalTimes = new int[numOfCusts];

		// Put a value from the given arrival times range into the array
		for (int i = 0; i < arrivalsInRange.length; i++) {
			arrivalsInRange[i] = randomizer(minArrival, maxArrival);
		}

		// Now put absolute arrival times in the field of arrival times
		// Have to add next time to previous
		for (int i = 0; i < arrivalTimes.length; i++) {
			if (i == 0) {
				arrivalTimes[i] = arrivalsInRange[i];
			} else {
				arrivalTimes[i] = arrivalTimes[i - 1] + arrivalsInRange[i];
			}
		}

	}

	// This method generates an array of service times
	public void generateServiceTimes() {
		serviceTimes = new int[numOfCusts];

		// Put a service time value from the given range into each slot of the array
		for (int i = 0; i < serviceTimes.length; i++) {
			serviceTimes[i] = randomizer(minService, maxService);
		}
	}

	// randomize lanes
	public int laneChoice() {
		return randomizer(0, 1);
	}

	// randomizer method to randomize an integer
	public static int randomizer(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max + 1) - min) + min;
	}

	// getters and setters
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

	public int[] getArrivalTimes() {
		return arrivalTimes;
	}

	public int[] getServiceTimes() {
		return serviceTimes;
	}
	
	public int getSelfServPenalty() {
		return selfServPenalty;
	}
	
	public void setSelfServPenalty(int ssp) {
		this.selfServPenalty = ssp;
	}

}

