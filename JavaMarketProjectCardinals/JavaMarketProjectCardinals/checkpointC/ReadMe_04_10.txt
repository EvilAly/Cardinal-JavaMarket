Changed Classes:

- Edited MarketSim
	- This class now asks the user for the amount of full-service and self-service lanes that they wish to use.

- SelfCheckouts and MultipleLanes were updated to accept the amount of lanes for full-service amd for self-service.

- The output now shows if the program recommneds the user to add/remove lanes from either the Full-Service lanes, Self-Service lanes, or a general amount of lanes due to the current customer satisfaction.

- Input verification has also been tested and updated

Java Market Sample Data

Data used for each run in trial 1:

	Number of Full-Service lanes: 1
	Number of Self-Service lanes: 1
	Minimum Arrival Time: 1
	Maximum Arrival Time: 6
	Minimum Service Time: 5
	Maximum Service Time: 10
	Penalty % for Self-Checkout: 20

Trial #1: Run #1: Number of Customers: 20
Full-Service
Average Wait Time: 0.83 minutes
Time that Checkout Lanes are not in use: 73 minutes

Self-Service
Average Wait Time: 15.57 minutes
Time that Checkout Lanes are not in use: 9 minutes

Number of Satisfied customers: 10
Number of Unsatisfied customers: 10

Recommendation: Add 2 Full-Service lanes and 5 Self-Service lanes

Trial #1: Run #2: Number of Customers: 50
Full-Service
Average Wait Time: 38.59 minutes
Time that Checkout Lanes are not in use: 321 minutes

Self-Service
Average Wait Time: 21.91 minutes
Time that Checkout Lanes are not in use: 38 minutes

Number of Satisfied customers: 7
Number of Unsatisfied customers: 43

Recommendation: Add 4 Full-Service lanes and 8 Self-Service lanes

Trial #1: Run #3: Number of Customers: 100
Full-Service
Average Wait Time: 33.79 minutes
Time that Checkout Lanes are not in use: 90 minutes

Self-Service
Average Wait Time: 56.4 minutes
Time that Checkout Lanes are not in use: 6 minutes

Number of Satisfied customers: 4
Number of Unsatisfied customers: 96

Recommendation: Add 4 Full-Service lanes and 9 Self-Service lanes


Data used for each run in trial 2:

	Number of Full-Service lanes: 2
	Number of Self-Service lanes: 2
	Minimum Arrival Time: 1
	Maximum Arrival Time: 6
	Minimum Service Time: 5
	Maximum Service Time: 10
	Penalty % for Self-Checkout: 20

Trial #2: Run #1: Number of Customers: 20
Full-Service
Average Wait Time: 0 minutes
Time that Checkout Lanes are not in use: 44 minutes

Self-Service
Average Wait Time: 1.17 minutes
Time that Checkout Lanes are not in use: 27 minutes

Number of Satisfied customers: 20
Number of Unsatisfied customers: 0

Recommendation: No recommendation

Trial #2: Run #2: Number of Customers: 50
Full-Service
Average Wait Time: 1.41 minutes
Time that Checkout Lanes are not in use: 92 minutes

Self-Service
Average Wait Time: 2.48 minutes
Time that Checkout Lanes are not in use: 113 minutes

Number of Satisfied customers: 42
Number of Unsatisfied customers: 8

Recommendation: No recommendation

Trial #2: Run #3: Number of Customers: 100
Full-Service
Average Wait Time: 0.69 minutes
Time that Checkout Lanes are not in use: 246 minutes

Self-Service
Average Wait Time: 1.51 minutes
Time that Checkout Lanes are not in use: 225 minutes

Number of Satisfied customers: 91
Number of Unsatisfied customers: 9

Recommendation: Add 1 Full-Service lane and 1 Self-Service Lane


Data used for each run in trial 3:

	Number of Full-Service lanes: 5
	Number of Self-Service lanes: 3
	Minimum Arrival Time: 1
	Maximum Arrival Time: 6
	Minimum Service Time: 5
	Maximum Service Time: 10
	Penalty % for Self-Checkout: 20

Trial #3: Run #1: Number of Customers: 20
Full-Service
Average Wait Time: 0 minutes
Time that Checkout Lanes are not in use: 76 minutes

Self-Service
Average Wait Time: 0 minutes
Time that Checkout Lanes are not in use: 75 minutes

Number of Stisfied customers: 20
Number of Unsatisfied customers: 0

Recommendation: Remove 4 Full-Service lanes and 2 Self-Service lanes

Trial #3: Run #2: Number of Customers: 50
Full-Service
Average Wait Time: 0 minutes
Time that Checkout Lanes are not in use: 178 minutes

Self-Service
Average Wait Time: 0.26 minutes
Time that Checkout Lanes are not in use: 147 minutes

Number of Satisfied customers: 49
Number of Unsatisfied customers: 1

Recommendation: Remove 4 Full-Service lanes and 2 Self-Service lanes

Trial #3: Run #3: Number of Customers: 100
Full-Service
Average Wait Time: 0 minutes
Time that Checkout Lanes are not in use: 365 minutes

Self-Service
Average Wait Time: 0.09 minutes
Time that Checkout Lanes are not in use: 331 minutes

Number of Satisfied customers: 100
Number of Unsatisfied customers: 0

Recommendation: Remove 4 Full-Service lanes and 2 Self-Service lanes
