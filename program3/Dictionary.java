//Rishab Jain,rjain9,7/24/17,12B,Dictionary.java,Main code for program
public class Dictionary implements DictionaryInterface{
	// private inner Node class
	private class Node {
		String key;
		String value;
		Node next;

		Node(String x, String y){
			key = x;
			value = y;
			next = null;
      }
      public String toString(){
      	StringBuffer sb = new StringBuffer();
      	sb.append(key +" " + value).append("\n");
      	return new String(sb);
      }
   }
   private Node head;
   private int numItems;
  
   public Dictionary(){
   	head = null;
   	numItems  = 0;
   }

   private Node findKey(String key){
   	Node N;
   	for(N=head; N!=null; N=N.next){
   		if(N.key.equals(key)){
   			return N;
   		}
   	}
   	return null;
   }

   public int size(){
   	return numItems;	
   }

   public boolean isEmpty(){
   	return (numItems == 0);
   }

   public String lookup(String key){
   	Node N;
   	for(N=head; N!=null; N=N.next){
   		if(N.key.equals(key)){
   			return N.value;
   		}
   	}
   	return null;
   }

   public void insert(String key, String value) throws DuplicateKeyException{
   	if(lookup(key) != null){                      //key is already in the list
   		throw new DuplicateKeyException("Insert() trying to add a duplicate key");
   	}
   	else{
   		Node N;
   		if(head == null){
   			head = new Node(key,value);
   			numItems++;
   		}
   		else{
   			N= head;
   			while(N.next != null){
   				N=N.next;
   			}
   			N.next = new Node(key,value);
   			numItems++;
   		}
   	}
   }

   public void delete(String key) throws KeyNotFoundException{
   	Node P;
   	Node C;
   
   	P=null;
   
   	boolean found = false;
  
   	for(C=head;C !=null;C=C.next){
   		
   		if(C.key.equals(key)){
   			found = true;
   			break;         //break out of for loops if key matches
   		}
   		P=C;
   	}
  
   	if(!found){
   		throw new KeyNotFoundException("Delete() called on invalid key");
   	}
  
   	if(P!=null){
   		P.next = C.next;     //remove element from link list
   	}
  

   	if(head == C){
   		head = C.next;
   	}

   	numItems--;
   }
   
   public void makeEmpty(){
   	head = null;
   	numItems = 0;

   }

   public String toString(){
   	StringBuffer sb = new StringBuffer();
   	Node N;

    for(N=head; N!=null; N=N.next){
        sb.append(N);
     }
    return new String(sb);
   }
}