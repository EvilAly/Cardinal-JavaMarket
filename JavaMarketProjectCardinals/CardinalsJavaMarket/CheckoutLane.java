package CardinalsJavaMarket;

/**
   The CheckoutLane class implements a doubly 
   Linked list. 
*/

class CheckoutLane<E>
{
    /**
       The Node class stores a list element
       and a reference to the next node.
    */
    private class Node
    {
        E value;   // Value of a list element
        Node next;      // Next node in the list
        Node prev;      // Previous element in the list
		  
        /**
           Constructor.            
           @param val The element to be stored in the node.
           @param n The reference to the successor node.
           @param p The reference to the predecessor node.
        */
		  
        Node(E val, Node n, Node p)
        {
            value = val;
            next = n;
            prev = p;
        }
		   
        /**
           Constructor. 
           @param val The element to be stored in the node.
        */
		  
        Node(E val)
        {
           // Just call the other (sister) constructor
           this(val, null, null);            
        }
    }
    
    private Node first;   // Head of the list
    private Node last;    // Last element on the list 
    private String name;  // Name of the list
    private boolean justRemoved;
    
    /** 
       Constructor.
    */
    
    public CheckoutLane()
    {
        first = null;
        last = null;    
        name = "line";
    }
    
    /**
     * Full constructor
     * @param n The name of the list
     */
    public CheckoutLane(String n) {
    	first = null;
    	last = null;
    	name = n;
    	justRemoved = false;
    }
   
    /**
       The isEmpty method checks to see if the list
       is empty.
       @return true if list is empty, false otherwise.
    */
    
    public boolean isEmpty()
    {        
        return first == null;
    }
    
    /**
       The size method returns the length of the list.
       @return The number of elements in the list.
    */
    
    public int size()
    {
       int count = 0;
       Node p = first;   
       while (p != null)
       {
           // There is an element at p
           count ++;
           p = p.next;
       }
       return count;
    }
    
    /**
       The add method adds to the end of the list.
       @param e The value to add.       
    */
    
    public void add(E e)
    {
      if (isEmpty()) 
      {
          last = new Node(e);
          first = last;
      }
      else
      {
          // Add to end of existing list
          last.next = new Node(e, null, last);
          last = last.next;         
      }      
    }
    
    /**
       This add method adds an element at an index.
       @param e The element to add to the list.
       @param index The index at which to add.
       @exception IndexOutOfBoundsException 
		 When the index is out of bounds.
    */
    
    public void add(int index, E e)
    {
         if (index < 0  || index > size()) 
         {
             String message = String.valueOf(index);
             throw new IndexOutOfBoundsException(message);
         }
         
         // Index is at least 0
         if (index == 0)
         {
             // New element goes at beginning
             Node p = first;            // Old first
             first = new Node(e, p, null);
             if (p != null)
                 p.prev = first;             
             if (last == null)
                 last = first;
             return;
         }
         
         // pred will point to the predecessor
			// of the new node.
         Node pred = first;       
         for (int k = 1; k <= index - 1; k++)        
         {
            pred = pred.next;           
         }
         
         // Splice in a node with the new element
         // We want to go from  pred-- succ to 
         // pred--middle--succ
         Node succ = pred.next;
         Node middle = new Node(e, succ, pred);
         pred.next = middle;  
         if (succ == null)             
             last = middle;       
         else            
             succ.prev = middle;                     
    }
    
    /**
       The toString method computes the string
       representation of the list.
       @return The string representation of the
       linked list.
    */
    
    public String toString()
    {
      StringBuilder strBuilder = new StringBuilder();
      
      // Use p to walk down the linked list
      Node p = first;
      while (p != null)
      {
         strBuilder.append(p.value.toString() + "\n"); 
         p = p.next;
      }      
      return strBuilder.toString(); 
    }
    
    /**
       The remove method removes the element
		 at a given position.
       @param index The position of the element 
		 to remove. 
       @return The element removed.   
       @exception IndexOutOfBoundsException When		 
		 index is out of bounds.  
    */
    
public E remove(int index)
    {
       if (index < 0 || index >= size())  
       {
          String message = String.valueOf(index);
          throw new IndexOutOfBoundsException(message);
       }            
      
       // Locate the node targeted for removal
       Node target = first;  
       for (int k = 1; k <= index; k++)
           target = target.next;
       
       E element = target.value;  // Element to return
       Node pred = target.prev;        // Node before the target
       Node succ = target.next;        // Node after the target
       
       // Route forward and back pointers around
       // the node to be removed
       if (pred == null)       
           first = succ;
       else
           pred.next = succ;
       
       if (succ == null)
           last = pred;
       else
           succ.prev = pred;
       
       //Someone was just removed so make this field true
       justRemoved = true;
       
       return element;        
    }
   
    /**
       The remove method removes an element from the list.
       @param element The element to remove.
       @return true if the element was removed, false otherwise.
    */
    
  public boolean remove(E element)
    {
       if (isEmpty()) 
           return false;      
      
       // Locate the node targeted for removal
       Node target = first;  
       while (target != null 
               && !element.equals(target.value))
           target = target.next;
       
       if (target == null)
           return false;       
      
       Node pred = target.prev;        // Node before the target
       Node succ = target.next;        // Node after the target
       
       // Route forward and back pointers around
       // the node to be removed
       if (pred == null)       
           first = succ;
       else
           pred.next = succ;
       
       if (succ == null)
           last = pred;
       else
           succ.prev = pred;      
    
        return true;       
    }
  
  /**
   * This method gets the name of the list
   * @return The name of the list.
   */
  public String getName() {
	  return name;
  }
  
  /**
   * This method returns the first node in the list
   * @return The first node in the list
   */
  public Node getFirstNode() {
	  return first;
  }
  
  /** 
   * This method returns the last node in the list
   * @return The last node in the list
   */
  public Node getLastNode() {
	  return last;
  }
  
  /**
   * This method returns the first value in the list
   * @return The first node's value in the list
   */
  public E getFirstValue() {
	  return first.value;
  }
  
  /**
   * This method returns the last value in the list
   * @return The last node's value in the list
   */
  public E getLastValue() {
	  return last.value;
  }
  
  /**
   * This method returns true if an element has just been removed
   * @return The truth value of an element just being removed.
   */
  public boolean getJustRemoved() {
	  return justRemoved;
  }
  
  /** 
   * This method sets the value to true if an element has just been removed or false if not
   * @param True or false depending on if an element has just been removed
   */
  public void setJustRemoved(boolean b) {
	  justRemoved = b;
  }
    
}