package CardinalsA;

//This class creates a customer object
public class Customer {
	private int arrivalTime;
	private int serviceTime;
	private int finishTime;
	private char lane;
	private char serviceType;
	
	//Empty constructor
	public Customer() {
		
	}
	
	//Full constructor
	public Customer(int at, int st, int ft, char l, char servt) {
		arrivalTime = at;
		serviceTime = st;
		finishTime = ft;
		lane = l;
		serviceType = servt;
		waitTime();
	}
	
	//Wait time
	public int waitTime() {
		return finishTime - serviceTime;
	}
	
	//To string
	public String toString() {
		String s;
		s = "The customer arrived at " + arrivalTime + " and began service at " + serviceTime;
		s = s + " in lane " + lane + " which is ";
		if (serviceType == 'f') s = s + "full service.";
		else if (serviceType == 's') s = s + "self service.";
		return s;
	}

	//Getters and setters
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

	public void setLane(char lane) {
		this.lane = lane;
	}

	public char getServiceType() {
		return serviceType;
	}

	public void setServiceType(char serviceType) {
		this.serviceType = serviceType;
	}
	
}
