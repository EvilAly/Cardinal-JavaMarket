package v1;

import java.util.ArrayList;

public class CheckoutLanes {
	
	private ArrayList<Customer> cust;
	private int timeInUse;
	
	// Empty Constructor
	public CheckoutLanes() {
		
	}
	
	// Full Constructor
	public CheckoutLanes(int time, ArrayList<Customer> c) {
		
		timeInUse = time;
		cust = c;
	}
	
	public String toString() {
		String display = "";
		for (int i = 0; i < cust.size(); i++) {
			display = "This customer was assigned to lane " + cust.get(i).getLane();
		}
		return display;
	}
	
	public void laneAssignment() {
		char laneRow;
		//cust.set(cust.get(i).getLane(), laneRow);
	}

	// Getters and Setters
	public ArrayList<Customer> getCust() {
		return cust;
	}

	public void setCust(ArrayList<Customer> cust) {
		this.cust = cust;
	}

	public int getTimeInUse() {
		return timeInUse;
	}

	public void setTimeInUse(int timeInUse) {
		this.timeInUse = timeInUse;
	}

}
