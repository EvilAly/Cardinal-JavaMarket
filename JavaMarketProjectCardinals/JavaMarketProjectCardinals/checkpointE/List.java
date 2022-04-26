package checkpointE;

import java.util.NoSuchElementException;

//class to represent one node in a list
class ListNode<E extends Comparable<E>> {
	// package access members; List can access these directly
	E data; // data for this node
	ListNode<E> nextNode; // reference to the next node in the list

	// constructor creates a ListNode that refers to object
	ListNode(E object) {
		this(object, null);
	}

	// constructor creates ListNode that refers to the specified
	// object and to the next ListNode
	ListNode(E object, ListNode<E> node) {
		data = object;
		nextNode = node;
	}

	// return reference to data in node
	E getData() {
		return data;
	}

	// return reference to next node in list
	ListNode<E> getNext() {
		return nextNode;
	}
}

//class List definition
public class List<E extends Comparable<E>> {
	private ListNode<E> firstNode;
	private ListNode<E> lastNode;
	private char name; // string like "list" used in printing
	private boolean justRemoved;

	// constructor creates empty List with 'l' as the name
	public List() {
		this('l');
		justRemoved = false;
	}

	// constructor creates an empty List with a name
	public List(char string) {
		name = string;
		firstNode = lastNode = null;
		justRemoved = false;
	}

	// This method returns the size of the List
	public int size() {

		// If empty return 0
		if (isEmpty()) {
			return 0;
		}

		// Count the elements
		ListNode<E> p = firstNode;
		int count = 0;
		while (p != null) {
			count++;
			p = p.nextNode;
		}

		return count;
	}

	// insert item at front of List
	public void insertAtFront(E insertItem) {
		if (isEmpty()) { // firstNode and lastNode refer to same object
			firstNode = lastNode = new ListNode<E>(insertItem);
		} else { // firstNode refers to new node
			firstNode = new ListNode<E>(insertItem, firstNode);
		}
	}

	// insert item at end of List
	public void insertAtBack(E insertItem) {
		if (isEmpty()) { // firstNode and lastNode refer to same object
			firstNode = lastNode = new ListNode<E>(insertItem);
		} else { // lastNode's nextNode refers to new node
			lastNode = lastNode.nextNode = new ListNode<E>(insertItem);
		}
	}

	// remove first node from List
	public E removeFromFront() throws NoSuchElementException {
		if (isEmpty()) { // throw exception if List is empty
			throw new NoSuchElementException(name + " is empty");
		}

		E removedItem = firstNode.data; // retrieve data being removed

		// update references firstNode and lastNode
		if (firstNode == lastNode) {
			firstNode = lastNode = null;
		} else {
			firstNode = firstNode.nextNode;
		}

		// Element was just removed so make this field true
		justRemoved = true;

		return removedItem; // return removed node data
	}

	// remove last node from List
	public E removeFromBack() throws NoSuchElementException {
		if (isEmpty()) { // throw exception if List is empty
			throw new NoSuchElementException(name + " is empty");
		}

		E removedItem = lastNode.data; // retrieve data being removed

		// update references firstNode and lastNode
		if (firstNode == lastNode) {
			firstNode = lastNode = null;
		} else { // locate new last node
			ListNode<E> current = firstNode;

			// loop while current node does not refer to lastNode
			while (current.nextNode != lastNode) {
				current = current.nextNode;
			}

			lastNode = current; // current is new lastNode
			current.nextNode = null;
		}

		// Element was just removed so make this field true
		justRemoved = true;

		return removedItem; // return removed node data
	}

	// determine whether list is empty; returns true if so
	public boolean isEmpty() {
		return firstNode == null;
	}

	// output list contents
	public void print() {
		if (isEmpty()) {
			System.out.printf("Empty %s%n", name);
			return;
		}

		System.out.printf("The %s is: ", name);
		ListNode<E> current = firstNode;

		// while not at end of list, output current node's data
		while (current != null) {
			System.out.printf("%s ", current.data);
			current = current.nextNode;
		}

		System.out.println();
	}

	// This method returns an element at a given index
	public E getElement(int index) {
		// If the index is out of bounds throw an exception
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		// Traverse through loop until you get to index
		ListNode<E> p = firstNode;
		int indexCount = 0;
		while (p != null) {
			if (index == indexCount) {
				return p.data;
			}
			indexCount++;
			p = p.nextNode;
		}

		return null;
	}

	/**
	 * This method returns true if an element has just been removed
	 * 
	 * @return The truth value of an element just being removed.
	 */
	public boolean getJustRemoved() {
		return justRemoved;
	}

	/**
	 * This method sets the value to true if an element has just been removed or
	 * false if not
	 * 
	 * @param True or false depending on if an element has just been removed
	 */
	public void setJustRemoved(boolean b) {
		justRemoved = b;
	}

	/**
	 * This method gets the name of the list
	 * 
	 * @return The name of the list.
	 */
	public char getName() {
		return name;
	}

	/**
	 * This method returns the first node in the list
	 * 
	 * @return The first node in the list
	 */
	public ListNode<E> getFirstNode() {
		return firstNode;
	}

	/**
	 * This method returns the last node in the list
	 * 
	 * @return The last node in the list
	 */
	public ListNode<E> getLastNode() {
		return lastNode;
	}

	/**
	 * This method returns the first value in the list
	 * 
	 * @return The first node's value in the list
	 */
	public E getFirstValue() {
		return firstNode.data;
	}

	/**
	 * This method returns the last value in the list
	 * 
	 * @return The last node's value in the list
	 */
	public E getLastValue() {
		return lastNode.data;
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
