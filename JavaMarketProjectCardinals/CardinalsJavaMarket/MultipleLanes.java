package CardinalsJavaMarket;

import java.util.ArrayList;

//This class manages multiple checkout lanes
public class MultipleLanes {

	private ArrayList<CheckoutLane<Customer>> checkoutLanes; // Arraylist of all checkout lanes
	private int notInUse; // Tracks how much time there is a line not in use
	private int totalWait; // Tracks total wait time
	private Customer[] removedCusts; // Tracks removed customers

	// Constructors
	public MultipleLanes() {
		checkoutLanes = new ArrayList<CheckoutLane<Customer>>();
		totalWait = 0;
		notInUse = 0;
		removedCusts = new Customer[checkoutLanes.size()];
	}

	public MultipleLanes(ArrayList<CheckoutLane<Customer>> d) {
		checkoutLanes = d;
		totalWait = 0;
		notInUse = 0;
		removedCusts = new Customer[checkoutLanes.size()];
	}

	// This method determines which lane is the smallest
	public CheckoutLane<Customer> chooseLine() {
		// If more than one are the same size and smallest, it picks the one closest to
		// the beginning of the list
		CheckoutLane<Customer> smallest = checkoutLanes.get(0);
		for (int i = 0; i < checkoutLanes.size(); i++) {
			CheckoutLane<Customer> curLine = checkoutLanes.get(i);
			if (curLine.size() < smallest.size()) {
				smallest = curLine;
			}
		}

		return smallest;
	}

	// This method checks if the customer at the front is new (or null) and needs to
	// set their fields accordingly
	public void checksIfNew(int time) {

		for (int i = 0; i < checkoutLanes.size(); i++) {

			CheckoutLane<Customer> theLane = checkoutLanes.get(i);

			// If a customer had been just removed
			if (theLane.getJustRemoved()) {
				if (theLane.getFirstNode() != null) {
					// Set the new customer's finish time
					theLane.getFirstValue().setFinishTime(theLane.getFirstValue().getServiceTime() + time);
					// Also record their exact time of service
					theLane.getFirstValue().setServiceStart(time);
					// Calculate their wait time which sets their waitTime field
					theLane.getFirstValue().calculateWaitTime();
					// Add that wait time to the total wait time
					totalWait = totalWait + theLane.getFirstValue().getWaitTime();
				}

				// Switch just removed to false because now either a customer has just been
				// moved to the front
				// Or there is null value moved to the front
				theLane.setJustRemoved(false);
			}

			// If a customer just joined an empty list, the justRemoved will not be true
			// But their fields still need to be set, as they are new to the front of the
			// line
			// So if this the case, then check if the list is of size 1 and if the
			// customer's value for finish time is 0 (which was how it was initialized, this
			// is where it gets set properly)
			else if (theLane.size() == 1 && theLane.getFirstValue().getFinishTime() == 0) {
				// Set the new customer's finish time
				theLane.getFirstValue().setFinishTime(theLane.getFirstValue().getServiceTime() + time);
				// Also record their exact time of service
				theLane.getFirstValue().setServiceStart(time);
				// Calculate their wait time which sets their waitTime field
				theLane.getFirstValue().calculateWaitTime();
				// Add that wait time to the total wait time
				totalWait = totalWait + theLane.getFirstValue().getWaitTime();
			}

		}
	}

	// This method removes customers from the front line if their service time is
	// over (They reach the finish time)
	public void removeFromFront(int time) {
		for (int i = 0; i < checkoutLanes.size(); i++) {
			CheckoutLane<Customer> theLane = checkoutLanes.get(i);
			if (theLane.getFirstNode() != null) {
				// If the customer reaches the finish time then remove them
				// justRemoved field in the CheckoutLane will be set to true upon removal (in
				// the
				// CheckoutLane's remove method itself)
				// Put the removed customer in the array of removed custers
				if (time == theLane.getFirstValue().getFinishTime()) {
					Customer toRemove = theLane.remove(0);
					removedCusts[i] = toRemove;
				}

			}
		}
	}

	// This method checks if there is a line that is not in use and tracks the
	// amount of time of no usage
	public void trackNoUsageTime() {
		// Look through all lines
		// As soon as there is one that is currently empty then increment the no usage
		// time field
		// Exit while loop because there is no need to check the others since one is
		// already empty
		int i = 0; // index
		boolean emptyFound = false;
		while (i < checkoutLanes.size() && !emptyFound) {
			// Get the lane at the index
			// If it is empty then set the emptyFound to true
			emptyFound = checkoutLanes.get(i).isEmpty();
			// Increment the total time that lanes are not in use if empty
			if (emptyFound) {
				notInUse++;
			}

			i++;
		}
	}

	// This method calculates the average wait time
	// It takes in the amount of customers as input
	public double calculateAvgWaitTime(int custNum) {
		return (double) totalWait / custNum;
	}

	// This method checks if all the lines are empty
	public boolean areAllEmpty() {
		// Loop and check if all empty
		int i = 0; // index
		boolean empty = true;
		while (i < checkoutLanes.size() && empty) {
			// Get the lane at the index
			// If it is not empty then set empty to false
			empty = checkoutLanes.get(i).isEmpty();
			// If a lane is found that is not empty then return false
			if (!empty) {
				return false;
			}

			i++;
		}

		// If it reaches this point all lanes are empty
		return true;
	}

	// This method displays what is occurring at each lane at the given time
	public void displayOutput(int time) {

		// Display the time
		// If the time is equal to 0 then say start
		System.out.println("Time: " + time);
		if (time == 0) {
			System.out.println("	Start");
		}

		// If time not 0 then will have to display checkout lanes info
		else {
			// Loop through all lanes
			for (int i = 0; i < checkoutLanes.size(); i++) {

				// Get the lane
				CheckoutLane<Customer> theLane = checkoutLanes.get(i);

				// Store lane name
				String s = "	Checkout lane " + theLane.getName() + ":";

				// Deal with scenarios where the first node is not empty
				if (theLane.getFirstNode() != null) {
					// Get current customer at the front of the line
					Customer curCust = theLane.getFirstValue();

					// Say if a customer leaves
					// Also if they leave display their turnaround time
					if (removedCusts[i] != null) {
						System.out.println(s + " " + removedCusts[i].nameReference() + " leaves (Turnaround time: " + removedCusts[i].calculateTurnaroundTime() + ")");
					}

					// Say if there is a customer starting service
					// If their service start time matches the current time then print that they are
					// starting
					// Show wait time
					if (curCust.getServiceStart() == time) {
						System.out.println(s + " " + curCust.nameReference() + " starts service (Wait time: " + curCust.getWaitTime() +
								", Service time will be: " + curCust.getServiceTime() + ")");
					}
					// Say if there is a customer continuing service, it is if the first customer's
					// finish time is not equal to the time
					else if (curCust.getFinishTime() != time) {
						System.out.println(s + " " + curCust.nameReference() + " (cont.)");
					}

					// Say if a customer joins the line but doesn't start service, they will be last
					if (theLane.getLastNode() != null && theLane.size() > 1) {
						if (theLane.getLastValue().getArrivalTime() == time) {
							System.out.println(s + " " + theLane.getLastValue().nameReference() + " joins the line");
						}
					}
				}

				// Say if the lane is free because a customer has just left
				else if (theLane.isEmpty() && removedCusts[i] != null) {
					System.out.println(s + " " + removedCusts[i].nameReference() + " leaves (Turnaround time: " + removedCusts[i].calculateTurnaroundTime() + ")");
				}

				// Say if the lane is free and a customer has not just left
				else if (theLane.isEmpty() && removedCusts[i] == null) {
					System.out.println(s + " Free");
				}

			}
		}
	}

	// This method makes the removed customers array completely null
	public void makeRemovedCustsNull() {
		for (int i = 0; i < removedCusts.length; i++) {
			removedCusts[i] = null;
		}
	}

	// This method displays stats about waiting time, lanes not in use, and customer satisfaction
	public void displayStats(int custAmt, ArrayList<Customer> custs) {

		System.out.println("The average wait time was " + calculateAvgWaitTime(custAmt) + " minutes.");
		System.out.println("The time that checkout lanes were not in use was " + notInUse + " minutes.");
		
		//Track number of satisfied (<5 min) and nonsatisfied customers (>= 5 mins)
		int satisfied = 0;
		int notSatisfied = 0;
		for (int i = 0; i < custs.size(); i++) {
			//Base it on wait time
			if (custs.get(i).getWaitTime() < 5) {
				satisfied++;
			}
			else notSatisfied++;
		}
			
		//Print satisfaction stats
		System.out.println("The number of customers who were satisfied (<5 wait time) is " + satisfied + ".");
		System.out.println("The number of customers who were not satisfied (>=5 wait time) is " + notSatisfied + ".");
		
	}

	// Getters and setters
	public ArrayList<CheckoutLane<Customer>> getCheckoutLanes() {
		return checkoutLanes;
	}

	public void setCheckoutLanes(ArrayList<CheckoutLane<Customer>> checkoutLanes) {
		this.checkoutLanes = checkoutLanes;
	}

	public int getNotInUse() {
		return notInUse;
	}

	public void setNotInUse(int notInUse) {
		this.notInUse = notInUse;
	}

	public int getTotalWait() {
		return totalWait;
	}

	public void setTotalWait(int totalWait) {
		this.totalWait = totalWait;
	}

}
