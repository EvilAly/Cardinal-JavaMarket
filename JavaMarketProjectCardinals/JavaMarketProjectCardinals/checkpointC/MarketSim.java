package checkpointC;

import java.util.*;

public class MarketSim {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		boolean test = true;

		while (test) {

			// Ask for how many customers to test for, make sure its above 0
			System.out.println("How many customers do you want to test for?");
			boolean validCustAmt = false;
			int custAmt = 0;
			while (!validCustAmt) {

				boolean integer = false;
				while (!integer) {
					// Make sure they type an integer

					try {
						custAmt = s.nextInt();
						integer = true;
					} catch (InputMismatchException ime) {
						System.err.println("You must type a whole number. Please try again.");
						s.nextLine();
					}
				}

				if (custAmt > 0)
					validCustAmt = true;
				else
					System.err.println("You must have at least 1 customer. Please try again.");
			}

			// Make a CustomerCreator object which has the parameters for the simulation
			CustomerCreator custCreate = createCustomerCreator(custAmt);
			System.out.println();

			// Get the arrays for the arrival times and service times
			int[] arrivalTimes = custCreate.getArrivalTimes();
			int[] serviceTimes = custCreate.getServiceTimes();

			// Make an arraylist of customers
			ArrayList<Customer> customerList = new ArrayList<>();

			// Create the queue lines A,B, and C
			// Add them to an arraylist
			// Create a new MultipleLanes object
			CheckoutQueue<Customer> lineA = new CheckoutQueue<>("A");
			CheckoutQueue<Customer> lineB = new CheckoutQueue<>("B");
			CheckoutQueue<Customer> lineC = new CheckoutQueue<>("C");
			ArrayList<CheckoutQueue<Customer>> theLines = new ArrayList<>();
			theLines.add(lineA);
			theLines.add(lineB);
			theLines.add(lineC);
			MultipleLanes lanes = new MultipleLanes(theLines);

			// One checkout lane services the self checkouts
			// Create a new SelfServiceQueues object
			CheckoutQueue<Customer> lineD = new CheckoutQueue<>("Self Checkouts");
			SelfCheckouts selfServeLanes = new SelfCheckouts(lineD, 2);

			// Things to track during the clock run
			boolean keepRunning = true;
			int clockTime = 0;
			int custGenerationNum = 1; // Tracks what number customer you are on in terms of generation, note that it
										// starts with 1

			// Clock
			while (keepRunning) {

				// At time 0 nothing is going to be going on
				if (clockTime != 0) {

					// Since this is a new time, make all removed customers in the array null to
					// reset it
					// Do for both types of lanes
					lanes.makeRemovedCustsNull();
					selfServeLanes.makeRemovedCustsNull();

					// Remove the customer from the front of any line if need be
					// Do this before instantiating a customer because they might want to join this
					// line
					// If it is the new shortest after a customer removal
					// Also track removed customers in an array, which is done in the removal method
					// Also remove customers from self checkouts if need be
					lanes.removeFromFront(clockTime);
					selfServeLanes.removeCust(clockTime);

					// Instantiation of a customer is based on if more customers needed (if
					// custGenerationNum is less than or equal to the customer amount (custAmt))
					// If the custGenerationNum is over the amount of customers needed for the
					// simulation, then no need to instantiate any more customers
					int laneType = 0;
					Customer newCust = null;
					if (custGenerationNum <= custAmt) {
						// Check if a customer needs to be instantiated based on arrival time value for
						// its index in the arrivalTimes array
						// Its index should be custGenerationNum-1
						if (clockTime == arrivalTimes[custGenerationNum - 1]) {
							// If so, go get associated arrival and service time value for the constructor
							// using the arrays
							// Use custGenerationNum-1 for proper index value in arrays
							// Add the Customer to customer arraylist
							// Increase custGenerationNum for the next customer
							int arrivalForCust = arrivalTimes[custGenerationNum - 1];
							int serviceForCust = serviceTimes[custGenerationNum - 1];
							newCust = new Customer(arrivalForCust, serviceForCust);
							customerList.add(newCust);
							custGenerationNum++;

							// Determine if user prefers full service or self service lane
							laneType = custCreate.laneChoice();
						}

						// assign customer to appropriate lane
						// a zero puts the customer in full service
						// a roll of one puts the customer in the self service queue
						if (newCust != null) {
							if (laneType == 0) {
								// Now let the customer join a queue line
								// If more than one are empty, join in the priority order of A,B,C
								// Otherwise go to the one with the smallest size
								// Set customer's lane field to chosen lane, also set their service type
								CheckoutQueue<Customer> chosenLine = lanes.chooseLine();
								newCust.setLane(chosenLine.getName());
								newCust.setServiceType('f');
								chosenLine.enqueue(newCust);
							} else {
								newCust.setServiceType('s');
								// sets slower service time for picking self service line
								newCust.setServiceTime((int) (newCust.getServiceTime()
										* (1 + 0.01 * (custCreate.getSelfServPenalty()))));
								// add customer to self service lane
								selfServeLanes.getSelfServiceLane().enqueue(newCust);

							}
						}

					}

					// If there is a new customer at the front of any line set their appropriate
					// fields
					// Also check for self checkouts
					lanes.checksIfNew(clockTime);
					selfServeLanes.addToSelfCheckout(clockTime);

					// Check if there are any queues that are not in use, method will increment the
					// notInUse field in the MultipleLanes class if so
					// Also do same for self checkouts
					lanes.trackNoUsageTime();
					selfServeLanes.trackNoUsageTime();

					// If all the queues empty and no more customers to generate then change
					// keepRunning to
					// false so you stop the clock
					if (custGenerationNum > custAmt && lanes.areAllEmpty() && selfServeLanes.areAllEmpty()) {
						keepRunning = false;
					}
				}

				// Do output display for lanes
				// Also for self service
				lanes.displayOutput(clockTime);
				selfServeLanes.displayOutput(clockTime);

				// Increase clock time by 1
				clockTime++;

			}

			// Output stats
			System.out.println();
			lanes.displayStats();
			System.out.println();
			selfServeLanes.displayStats();
			System.out.println();
			customerSatisfaction(customerList);

			// go again?
			System.out.println("\nWould you like to simulate a different scenario? (yes or no)");
			s.nextLine();
			String ans = s.nextLine().toLowerCase();

			boolean valid = false;
			while (!valid) {
				if (!ans.equals("no") && !ans.equals("yes")) {
					System.err.println("That is not a valid option.");
					System.out.println("\nWould you like to simulate a different scenario? (yes or no)");
					ans = s.nextLine().toLowerCase();
				} else {
					valid = true;
					if (ans.equals("no")) {
						test = false;
						System.out.println("Thank you for using the Cardinal Market Simulator! Good-bye.");

					} else {
						Customer.resetID();
						SelfCheckouts.setIdChar('D');
					}
				}
			}

		}
	}

	// Get a customer creator object, let user enter parameters
	public static CustomerCreator createCustomerCreator(int amtCust) {
		Scanner s = new Scanner(System.in);
		// Get parameters for simulation
		// The minimums should be at least 1
		// Make sure they enter integers
		// Catch illegal argument exceptions if the maximums are less than the min

		CustomerCreator cc = null;
		boolean validArgs = false;
		int minArrive = 0;
		int maxArrive = 0;
		while (!validArgs) {
			// Min and max arrival time
			System.out.println("Enter the minimum arrival time between customers in whole minutes (1 or above):");
			boolean validMin = false;
			while (!validMin) {
				boolean integer = false;

				while (!integer) {
					try {
						minArrive = s.nextInt();
						integer = true;
					} catch (InputMismatchException ime) {
						System.err.println("You must type a whole number. Please try again.");
						s.nextLine();
					}
				}

				if (minArrive > 0)
					validMin = true;
				else
					System.err.println("The minimum must be at least 1. Try again.");
			}

			System.out.println("Enter the maximum arrival time between customers in whole minutes:");
			boolean integer = false;
			while (!integer) {
				try {
					maxArrive = s.nextInt();
					integer = true;
				} catch (InputMismatchException ime) {
					System.err.println("You must type a whole number. Please try again.");
					s.nextLine();
				}
			}

			// If the max is less than the min catch the exception right then
			if (maxArrive < minArrive) {

				try {
					cc = new CustomerCreator(minArrive, maxArrive, 1, 1, amtCust);
				} catch (IllegalArgumentException iae) {
					System.err.println("The maximum cannot be less than the minimum. Please try again.");
					// s.nextLine();
				}
			} else
				validArgs = true;

		}

		validArgs = false;
		while (!validArgs) {

			// Min and max service time
			System.out.println("Enter the minimum service time in whole minutes:");
			boolean validMin = false;
			int minServ = 0;
			while (!validMin) {

				boolean integer = false;
				while (!integer) {

					try {
						minServ = s.nextInt();
						integer = true;
					} catch (InputMismatchException ime) {
						System.err.println("You must type a whole number. Please try again.");
						s.nextLine();
					}

				}

				if (minServ > 0)
					validMin = true;
				else
					System.err.println("The minimum must be at least 1. Try again.");
			}

			System.out.println("Enter the maximum service time in whole minutes:");
			int maxServ = 0;
			boolean integer = false;
			while (!integer) {

				try {
					maxServ = s.nextInt();
					integer = true;
				} catch (InputMismatchException ime) {
					System.err.println("You must type a whole number. Please try again.");
					s.nextLine();
				}
			}

			// Make a customer creator object which will make an array of arrival times and
			// service times
			// Based on user ranges
			try {
				cc = new CustomerCreator(minArrive, maxArrive, minServ, maxServ, amtCust);
				validArgs = true;
			} catch (IllegalArgumentException iae) {
				System.err.println("The maximum cannot be less than the minimum. Please try again.");
				s.nextLine();
			}

			System.out.println(
					"How much slower is the self service lane? Please a percentage as a whole number (i.e For 20%, please enter 20.");
			boolean theInteger = false;
			while (!theInteger) {
				try {
					int selfPacePenalty = s.nextInt();
					cc.setSelfServPenalty(selfPacePenalty);
					theInteger = true;
				} catch (InputMismatchException ime) {
					System.err.println("You must type a whole number. Please try again.");
					s.nextLine();
				}
			}
		}
		return cc;
	}

	public static void customerSatisfaction(ArrayList<Customer> custs) {
		// Track number of satisfied (<5 min) and nonsatisfied customers (>= 5 mins)
		int satisfied = 0;
		int notSatisfied = 0;
		for (int i = 0; i < custs.size(); i++) {
			// Base it on wait time
			if (custs.get(i).getWaitTime() < 5) {
				satisfied++;
			} else
				notSatisfied++;
		}

		// Print satisfaction stats
		System.out.println("The number of customers who were satisfied (<5 wait time) is " + satisfied + ".");
		System.out.println("The number of customers who were not satisfied (>=5 wait time) is " + notSatisfied + ".");
	}

}