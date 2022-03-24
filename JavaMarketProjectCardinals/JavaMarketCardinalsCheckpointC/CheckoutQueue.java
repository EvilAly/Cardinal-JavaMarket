// Fig. 21.11: Queue.java
// Queue uses class List.
package JavaMarketCardinalsCheckpointC;

import java.util.NoSuchElementException;

public class CheckoutQueue<E extends Comparable<E>> {
	private List<E> queueList;

	// constructors
	public CheckoutQueue() {
		queueList = new List<E>("queue");
	}

	public CheckoutQueue(String n) {
		queueList = new List<E>(n);
	}

	// This method returns the size of the queue
	public int size() {
		return queueList.size();
	}

	// add object to queue
	public void enqueue(E object) {
		queueList.insertAtBack(object);
	}

	// remove object from queue
	public E dequeue() throws NoSuchElementException {
		return queueList.removeFromFront();
	}

	// determine if queue is empty
	public boolean isEmpty() {
		return queueList.isEmpty();
	}
	
	// get an element at a specified index
	public E getElement(int index) {
		return queueList.getElement(index);
	}

	// output queue contents
	public void print() {
		queueList.print();
	}

	/**
	 * This method returns true if an element has just been removed
	 * 
	 * @return The truth value of an element just being removed.
	 */
	public boolean getJustRemoved() {
		return queueList.getJustRemoved();
	}

	/**
	 * This method sets the value to true if an element has just been removed or
	 * false if not
	 * 
	 * @param True or false depending on if an element has just been removed
	 */
	public void setJustRemoved(boolean b) {
		queueList.setJustRemoved(b);
	}

	/**
	 * This method gets the name of the list
	 * 
	 * @return The name of the list.
	 */
	public String getName() {
		return queueList.getName();
	}

	/**
	 * This method returns the first node in the list
	 * 
	 * @return The first node in the list
	 */
	public ListNode<E> getFirstNode() {
		return queueList.getFirstNode();
	}

	/**
	 * This method returns the last node in the list
	 * 
	 * @return The last node in the list
	 */
	public ListNode<E> getLastNode() {
		return queueList.getLastNode();
	}

	/**
	 * This method returns the first value in the list
	 * 
	 * @return The first node's value in the list
	 */
	public E getFirstValue() {
		return queueList.getFirstValue();
	}

	/**
	 * This method returns the last value in the list
	 * 
	 * @return The last node's value in the list
	 */
	public E getLastValue() {
		return queueList.getLastValue();
	}
}

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/
