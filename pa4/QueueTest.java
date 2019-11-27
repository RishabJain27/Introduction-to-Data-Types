//QueueTest.java
//Rishab Jain, rjain9, 12B, TEst for Queue class
class QueueTest {
	public static void main(String [] args) {
		Queue D = new Queue();
		D.enqueue("a");
		D.enqueue("b");
		System.out.println(D);
		D.dequeue();
		System.out.println(D);
		System.out.println(D.peek());//prints out where object is stored
		System.out.println(D.length());
		System.out.println(D.isEmpty());
		D.dequeueAll();
		System.out.println(D);
	}
}
