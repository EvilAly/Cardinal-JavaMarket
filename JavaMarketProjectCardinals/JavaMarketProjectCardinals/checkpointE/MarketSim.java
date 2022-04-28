package checkpointE;

import java.time.LocalDate;

//This class creates a customer object
public class Customer implements Comparable<Customer> {
	private int arrivalTime;
	private int serviceTime;
	private int serviceStart;
	private int finishTime;
	private int waitTime;
	private char lane; // May not need this, figure this out
	private char serviceType;
	private static int idNum = 1;
	private int id;
	private int runID; // tracks which version of simulation is being run for db purposes

	// Empty constructor
	public Customer() {
		id = idNum;
		idNum++;
	}

	// Full constructor
	public Customer(int at, int st) {
		arrivalTime = at;
		serviceTime = st;
		finishTime = 0; // This fields need to initialized for later use, it will be set differently
						// later
		id = idNum;
		idNum++;

	}

	// Calculate wait time
	public void calculateWaitTime() {
		waitTime = serviceStart - arrivalTime;
	}

	// Calculate turnaround time
	public int calculateTurnaroundTime() {
		return waitTime + serviceTime;
	}

	// To string
	public String toString() {
		String s;
		s = "The customer (Id: " + id + ") arrived at " + arrivalTime + " and was serviced for " + serviceTime;
		s = s + " with their service beginning at " + serviceStart + " ";
		s = s + " in lane " + lane + " which is ";
		if (serviceType == 'f')
			s = s + "full service.";
		else if (serviceType == 's')
			s = s + "self service.";
		return s;
	}

	// create SQL Statement
	public String toSQLString() {
		String c;
		c = "INSERT INTO customers (custID, arrivalTime, waitTime, serviceStart, serviceTime, finishTime, runID) VALUES "
				+ "(" + id + ", " + arrivalTime + ", " + waitTime + ", " + serviceStart + ", "
				+ serviceTime + ", " + finishTime + ", " + runID + ");";
		return c;
	}

	// Compare to method compares customers by finish time
	// Compare to method compares customers by finish time FIX THISSSS
	public int compareTo(Customer c2) {

		// Base this comparison on finish times
		// If both are equal return 0
		if (c2.getFinishTime() == this.getFinishTime()) {
			return 0;
		}
		// If one is equal to 0 and the other one is greater then the one with the
		// greater value is lesser than
		else if (c2.getFinishTime() == 0 && c2.getFinishTime() < this.getFinishTime()) {
			return -1;
		} else if (this.getFinishTime() == 0 && c2.getFinishTime() > this.getFinishTime()) {
			return 1;
		}
		// If both are greater than 0, the one with the lesser value is lesser than
		else if (c2.getFinishTime() > this.getFinishTime()) {
			return -1;
		} else {
			return 1;
		}

	}

	// this method resets the id to 1 when sim runs again
	public static void resetID() {
		idNum = 1;
	}

	// This method allows the customer be named with id
	public String nameReference() {
		return "Customer " + id;
	}

	// Getters and setters
	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public char getLane() {
		return lane;
	}

	public void setLane(char laneName) {
		this.lane = laneName;
	}

	public char getServiceType() {
		return serviceType;
	}

	public void setServiceType(char serviceType) {
		this.serviceType = serviceType;
	}

	public int getServiceStart() {
		return serviceStart;
	}

	public void setServiceStart(int serviceStart) {
		this.serviceStart = serviceStart;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRunID() {
		return runID;
	}

	public void setRunID(int runID) {
		this.runID = runID;
	}

}
