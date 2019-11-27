//Queue.java
//Rishab Jain, rjain9, 12B, Queue.java
public class Queue implements QueueInterface{
	private class Node{
		Object item;
		Node next;

		Node(Object x){
			item = x;
			next = null;

		}
	}
	private Node front;
	private Node back;
	private int numItems;

	public Queue(){
		front = null;
		back = null;
		numItems = 0;
	}

	// isEmpty()
    // pre: none
    // post: returns true if this Queue is empty, false otherwise
	public boolean isEmpty(){
		if(numItems >0){
			return false;
		}
		else{
			return true;
		}
	}
    // length()
    // pre: none
    // post: returns the length of this Queue.
   	public int length(){
   		Node N;
   		N=front;
   		int length = 1;

   		if(N == null){
   			return 0; 
   		}

   		for(N=front;N.next!=null;N=N.next){
   			length++;
   		}
   		return length; 
   }

    // enqueue()
    // adds newItem to back of this Queue
    // pre: none
    // post: !isEmpty()
    public void enqueue(Object newItem){
    	Node N;       // old back
    	N=back;
    	if(N==null){
    		front = new Node(newItem);
    		back = front;
    		numItems++;
    	}
    	else{
   			back = new Node(newItem);
   			N.next = back;
   			numItems++;
    	}
    }
    // dequeue()
    // deletes and returns item from front of this Queue
    // pre: !isEmpty()
    // post: this Queue will have one fewer element
    public Object dequeue() throws QueueEmptyException{
    	if(numItems == 0){
    		throw new QueueEmptyException("dequeue(): Can't delete and empty Queue");
    	}
    	Node N;
    	N = front;
    	front = N.next;
    	numItems--;
    	if(numItems == 0){
    		back = null;
    	}
    	return N.item;
    }

    // peek()
    // pre: !isEmpty()
    // post: returns item at front of Queue
    public Object peek() throws QueueEmptyException{
    	if(numItems == 0){
    		throw new QueueEmptyException("cannot peek() empty Queue");
    	}
    	return front.item;
    }

    // dequeueAll()
    // sets this Queue to the empty state
    // pre: !isEmpty()
    // post: isEmpty()
    public void dequeueAll() throws QueueEmptyException{
    	if(numItems == 0){
    		throw new QueueEmptyException("cannot dequeueAll() empty queue");
    	}
   
    	while(numItems != 0){
    		dequeue();
    	}
    
    }

    // toString()
    // overrides Object's toString() method
    public String toString(){
    	String s  = "";
    	Node N;
    
    	for(N=front;N!=null;N=N.next){
    		s = s + N.item + " ";
    	}
    	return s;
    }

}
