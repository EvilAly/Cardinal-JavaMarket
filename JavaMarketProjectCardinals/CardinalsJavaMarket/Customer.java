package CardinalsJavaMarket;

//This class creates a customer object
public class Customer {
	private int arrivalTime;
	private int serviceTime;
	private int serviceStart;
	private int finishTime;
	private int waitTime;
	private String lane;
	private char serviceType;
	private static int idNum = 1;
	private int id; 
	
	//Empty constructor
	public Customer() {
		id = idNum;
		idNum++;
	}
	
	//Full constructor
	public Customer(int at, int st) {
		arrivalTime = at;
		serviceTime = st;
		finishTime = 0; //This fields need to initialized for later use, it will be set differently later
		id = idNum;
		idNum++;
		
	}
	
	//Calculate wait time
	public void calculateWaitTime() {
		waitTime = serviceStart - arrivalTime;
	}
	
	//Calculate turnaround time
	public int calculateTurnaroundTime() {
		return waitTime+serviceTime;
	}
	
	//To string
	public String toString() {
		String s;
		s = "The customer (Id: " + id + ") arrived at " + arrivalTime + " and was serviced for " + serviceTime;
		s = s + " with their service beginning at " + serviceStart + " ";
		s = s + " in lane " + lane + " which is ";
		if (serviceType == 'f') s = s + "full service.";
		else if (serviceType == 's') s = s + "self service.";
		return s;
	}
	
	//This method allows the customer be named with id
	public String nameReference() {
		return "Customer " + id;
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

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
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
	
}