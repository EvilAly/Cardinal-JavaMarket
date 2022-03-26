Added/Changed Classes:

- SelfCheckouts
	- This class adds a customer to a single queue and then sorts the customer into an avaliable checkout slot or remains in the queue until one becomes avaliable.
	- This class works in a similar manner in which the class 'MultipleLanes' functions. The main difference between these two classes is their math when regarding checkout times. When a customer goes through self-checkout their checkout time is going to be a certain percentage slower, (which is determined by a user input), than a customer that goes through full-service checkout.

- CheckoutLane has been swapped out for CheckoutQueues. This allows the program to run mainly on queues rather than nodes and linkedlists.

- MultipleLanes now uses queues to opperate instead of nodes and lists.

- CustomerCreator now have a self-service penalty variable that stores a user input


Java Market Sample Data

Data used for each trial:
	Number of Customers: 15
	Minimum Arrival Time: 1
	Maximum Arrival Time: 9
	Minimum Service Time: 10
	Maximum Service Time: 20
	Penalty % for Self-Checkout: 20

Trial #1:

Service-Checkout
Average Wait Time: 0 minutes
Time that Checkout Lanes are not in use: 93 minutes

Self-Checkout
Average Wait Time: 1.33 minutes
Time that Checkout Lanes are not in use: 76 minutes

Number of satisfied customers: 14
Number of Unsatisfied customers: 1

Trial #2:

Service-Checkout
Average Wait Time: 0.62 minutes
Time that Checkout Lanes are not in use: 62 minutes

Self-Checkout
Average Wait Time: 6.29 minutes
Time that Checkout Lanes are not in use: 27 minutes

Number of satisfied customers: 11
Number of Unsatisfied customers: 4

Trial #3:

Service-Checkout
Average Wait Time: 0 minutes
Time that Checkout Lanes are not in use: 78 minutes

Self-Checkout
Average Wait Time: 1.6 minutes
Time that Checkout Lanes are not in use: 62 minutes

Number of satisfied customers: 14
Number of Unsatisfied customers: 1