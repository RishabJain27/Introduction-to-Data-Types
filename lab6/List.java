//List.java
//Rishab Jain, rjain9, 12M, 8/11/17
public class List<T> implements ListInterface<T> {
	// private inner Node class
   private class Node {
      T item;
      Node next;

      Node(T x){
         item = x;
         next = null;
      }
   }

   // Fields for the List class
   private Node head;     // reference to first Node in List
   private int numItems;  // number of items in this IntegerList

   // IntegerList()
   // constructor for the IntegerList class
   public List(){
      head = null;
      numItems = 0;
   }

   // private helper function -------------------------------------------------

   // find()
   // returns a reference to the Node at position index in this IntegerList
   private Node find(int index){
      Node N = head;
      for(int i=1; i<index; i++){
         N = N.next;
      }
      return N;
   }


   // ADT operations ----------------------------------------------------------

   // isEmpty()
   // pre: none
   // post: returns true if this List is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // size()
   // pre: none
   // post: returns the number of elements in this List
   public int size() {
      return numItems;
   }

   public T get(int index) throws ListIndexOutOfBoundsException {
      
      if( index<1 || index>numItems ){
         throw new ListIndexOutOfBoundsException(
            "List Error: get() called on invalid index: " + index);
      }
      Node N = find(index);
      return N.item;
   }

    // add()
   // inserts newItem into this IntegerList at position index
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), items to the right of newItem are renumbered
   public void add(int index, T newItem) throws ListIndexOutOfBoundsException{
      
      if( index<1 || index>(numItems+1) ){
         throw new ListIndexOutOfBoundsException(
            "List Error: add() called on invalid index: " + index);
      }
      if(index==1){
         Node N = new Node(newItem);
         N.next = head;
         head = N;
      }else{
         Node P = find(index-1); // at this point index >= 2
         Node C = P.next;
         P.next = new Node(newItem);
         P = P.next;
         P.next = C;
      }
      numItems++;
   }

   // remove()
   // deletes item at position index from this IntegerList
   // pre: 1 <= index <= size()
   // post: items to the right of deleted item are renumbered
   public void remove(int index) throws ListIndexOutOfBoundsException{
      if( index<1 || index>numItems ){
         throw new ListIndexOutOfBoundsException(
            "IntegerList Error: remove() called on invalid index: " + index);
      }
      if(index==1){
         Node N = head;
         head = head.next;
         N.next = null;
      }else{
         Node P = find(index-1);
         Node N = P.next;
         P.next = N.next;
         N.next = null;
      }
      numItems--;
   }

   // removeAll()
   // pre: none
   // post: isEmpty()
   public void removeAll(){
      head = null;
      numItems = 0;
   }

   // toString()
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node N = head;

      for( N=head; N!=null; N=N.next){
         sb.append(N.item).append(" ");
      }
      return new String(sb);
   }
	
}