package checkpointC;

import java.text.DecimalFormat;

public class SelfCheckouts {

	// Fields
	// the actual self servicing queue
	private CheckoutQueue<Customer> selfServiceLane;
	private int numCheckouts;
	// array of the checkout names, start from D and increments
	private char[] names;
	// array of the self checkouts themselves
	private Customer[] checkouts;
	// array that stores the removed customers
	private Customer[] remCustomer;
	private static char idChar;
	private int totalWait;
	// tracks amount of time that there is a checkout not in use
	private int notInUse;
	// tracks amount of customers that come into the self service lane
	private int amtCusts;

	// Empty constructor
	public SelfCheckouts() {
		selfServiceLane = new CheckoutQueue<Customer>();
		totalWait = 0;
		amtCusts = 0;
		notInUse = 0;
	}

	// Full constructor
	public SelfCheckouts(CheckoutQueue<Customer> lane, int num, char laneID) {
		selfServiceLane = lane;
		numCheckouts = num;
		amtCusts = 0;
		idChar = laneID;

		names = new char[numCheckouts];
		// Set the names in the array, for each iteration, increment queue character
		for (int i = 0; i < names.length; i++) {
			names[i] = idChar;
			idChar++;
		}

		checkouts = new Customer[numCheckouts];
		// Set the checkouts all to null since there are no customers there at the
		// current time
		for (int i = 0; i < checkouts.length; i++) {
			checkouts[i] = null;
		}

		remCustomer = new Customer[numCheckouts];
		// All just removed customers are null
		for (int i = 0; i < remCustomer.length; i++) {
			remCustomer[i] = null;
		}

		totalWait = 0;
		notInUse = 0;
	}

	// This method dequeues a customer and adds them to a self checkout
	public void addToSelfCheckout(int time) {
		// Go through the array of self checkouts
		// If one is empty and there are customers in line, dequeue the customer and add
		// them there
		for (int i = 0; i < checkouts.length; i++) {
			if (checkouts[i] == null && !selfServiceLane.isEmpty()) {
				checkouts[i] = selfServiceLane.dequeue();
				// Set their fields
				setCustFields(checkouts[i], i, time);
				// Increase customer amount in self checkouts
				amtCusts++;

			}
		}
	}

	// This method sets the customer's fields who just joined the line
	// Input is the customer who was just added, the index of the checkout they were
	// added to, and the time of this
	public void setCustFields(Customer c, int indexOfCheckout, int time) {
		// Set their lane name, which is corresponding in names array (indexOfCheckout)
		char laneName = names[indexOfCheckout];
		// Set their checkout lane
		c.setLane(laneName);
		// Set their finish time
		c.setFinishTime(c.getServiceTime() + time);
		// Record the exact they started service
		// Set the new customer's finish time
		c.setFinishTime(c.getServiceTime() + time);
		// Also record their exact time of service
		c.setServiceStart(time);
		// Calculate their wait time which sets their waitTime field
		c.calculateWaitTime();
		// Add that wait time to the total wait time
		totalWait = totalWait + c.getWaitTime();
	}

	// This method removes a customer when they are finished
	// Input is time
	public void removeCust(int time) {
		// Go through the array of self checkouts
		// If the customer is finished then make it null
		// Store them in the customers of just removed for output purposes
		for (int i = 0; i < checkouts.length; i++) {
			if (checkouts[i] != null) {
				if (checkouts[i].getFinishTime() == time) {
					remCustomer[i] = checkouts[i];
					checkouts[i] = null;
				}

			}
		}

	}

	// This method checks if all checkouts are empty
	public boolean areAllEmpty() {
		// Loop and check if all empty
		int i = 0; // index
		boolean empty = true;
		while (i < checkouts.length && empty) {
			// Get the lane at the index
			// If it is not empty then set empty to false
			if (checkouts[i] != null) {
				empty = false;
			}
			// If a lane is found that is not empty then return false
			if (!empty) {
				return false;
			}

			i++;
		}

		// If it reaches this point all lanes are empty
		return true;
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
		while (i < checkouts.length && !emptyFound) {
			// Get the lane at the index
			// If it is empty then set the emptyFound to true
			if (checkouts[i] == null) {
				emptyFound = true;
			}
			// Increment the total time that lanes are not in use if empty
			if (emptyFound) {
				notInUse++;
			}

			i++;
		}
	}

	// This method calculates the average wait time
	public double calculateAvgWaitTime() {
		if (amtCusts > 0) {
			return (double) totalWait / amtCusts;
		} else
			return 0;
	}

	// This method makes the removed customers array completely null
	public void makeRemovedCustsNull() {
		for (int i = 0; i < remCustomer.length; i++) {
			remCustomer[i] = null;
		}
	}

	// This method displays stats about waiting time and lanes not in use
	public int displayStats(int time) {
		DecimalFormat df = new DecimalFormat("#.##");

		System.out.println("Self-Service stats:");
		System.out.println("The average wait time was " + df.format(calculateAvgWaitTime()) + " minutes.");
		System.out.println("The time that self checkout were not in use was " + notInUse + " minutes.");
		
		int avgNotInUse = (notInUse / numCheckouts);
		int predictAvgNotInUse = (time / numCheckouts);

		if ((avgNotInUse > (int) (predictAvgNotInUse * .65)) && (calculateAvgWaitTime() <= 10)) {
			int closeLanes = (int) (numCheckouts - (notInUse / predictAvgNotInUse * .10));

			if (closeLanes < numCheckouts)
				return closeLanes;
			else
				return numCheckouts - 1;

		}
		
		return 0;
		

	}

	public void displayOutput(int time) {

		if (time > 0) {
			// If a customer just joined the self servicing queue say so
			if (!selfServiceLane.isEmpty()) {
				if (selfServiceLane.getElement(selfServiceLane.size() - 1).getArrivalTime() == time) {
					System.out.println("	" + selfServiceLane.getElement(selfServiceLane.size() - 1).nameReference()
							+ " joins the self servicing line");
				}
			}
			
			System.out.println("\n----------------------------------Self-Checkout-------------------------------------");
			// Loop through all checkouts
			for (int i = 0; i < checkouts.length; i++) {

				// Get the index of that self service checkout
				// int indexOfLane = i;

				// Store lane name
				String s = "	Checkout lane " + names[i] + ":";

				// Say if a customer leaves
				// Also if they leave display their turnaround time
				if (remCustomer[i] != null) {
					System.out.println(s + " " + remCustomer[i].nameReference() + " leaves (Turnaround time: "
							+ remCustomer[i].calculateTurnaroundTime() + ")");
				}

				// Say if the checkout is empty
				else if (checkouts[i] == null) {
					System.out.println(s + " Free");
				}

				// If lane actually used
				if (checkouts[i] != null) {
					// Get the customer
					Customer custInLane = checkouts[i];

					// Say if customer just joined line
					if (custInLane.getArrivalTime() == time) {
						System.out.println("	" + custInLane.nameReference() + " joins the self servicing line");
					}
					// Say if there is a customer starting service
					// If their service start time matches the current time then print that they are
					// starting
					// Show wait time
					if (custInLane.getServiceStart() == time) {
						System.out.println(s + " " + custInLane.nameReference() + " starts service (Wait time: "
								+ custInLane.getWaitTime() + ", Service time will be: " + custInLane.getServiceTime()
								+ ")");
					}

					// Say if there is a customer continuing service, it is if the first customer's
					// finish time is not equal to the time
					else if (custInLane.getFinishTime() != time) {
						System.out.println(s + " " + custInLane.nameReference() + " (cont.)");
					}
				}

			}
		}
		System.out.println("");
	}

	// Getters and setters
	public CheckoutQueue<Customer> getSelfServiceLane() {
		return selfServiceLane;
	}

	public void setSelfServiceLane(CheckoutQueue<Customer> selfServiceLane) {
		this.selfServiceLane = selfServiceLane;
	}

	public int getNumCheckouts() {
		return numCheckouts;
	}

	public void setNumCheckouts(int numCheckouts) {
		this.numCheckouts = numCheckouts;
	}

	public char[] getNames() {
		return names;
	}

	public void setNames(char[] names) {
		this.names = names;
	}

	public Customer[] getCheckouts() {
		return checkouts;
	}

	public void setCheckouts(Customer[] checkouts) {
		this.checkouts = checkouts;
	}

	public static char getIdChar() {
		return idChar;
	}

	public static void setIdChar(char idChar) {
		SelfCheckouts.idChar = idChar;
	}

	public int getTotalWait() {
		return totalWait;
	}

	public void setTotalWait(int totalWait) {
		this.totalWait = totalWait;
	}

	public int getNotInUse() {
		return notInUse;
	}

	public void setNotInUse(int notInUse) {
		this.notInUse = notInUse;
	}

	public int getAmtCusts() {
		return amtCusts;
	}

	public void setAmtCusts(int amtCusts) {
		this.amtCusts = amtCusts;
	}
}
