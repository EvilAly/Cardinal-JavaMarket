package v2;

import java.util.*;

public class MarketSim {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		boolean test = true;
		
		while(test) {
			
			//get parameters for simulation
			System.out.println("Enter the minimum arrival time between customers in whole minutes:");
			int minArrive = s.nextInt();
			System.out.println("Enter the maximum arrival time between customers in whole minutes:");
			int maxArrive = s.nextInt();
			System.out.println("Enter the minimum service time in whole minutes:");
			int minServ = s.nextInt();
			System.out.println("Enter the maximum service time in whole minutes:");
			int maxServ = s.nextInt();
			System.out.println("How many customers do you want to test for?");
			int c = s.nextInt();
			
			//create customers (or maybe as sim progresses?)
			//create checkout lanes
			//start sim
			
			//go again?
			System.out.println("Would you like to simulate a different scenario? (yes or no)");
			String ans = s.nextLine().toLowerCase();
			if (ans.equals("no"))
				test = false;
			
			
		}

	}
	
	//randomizer method to randomize the arrival and service times for customers
	public static int randomizer(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max) + min;
	}

}
