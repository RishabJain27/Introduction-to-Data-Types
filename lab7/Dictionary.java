//Dictionary.java
//Rishab Jain,rjain9,8/15/17, 12M
public class Dictionary implements DictionaryInterface{
	// private inner Node class
	private class Node {
		String key;
		String value;
		Node left;
     		Node right;

		Node(String x, String y){
			key = x;
			value = y;
			left = null;
        		right = null;
     		}

   }
   private Node root;
   private int numPairs;
  
   public Dictionary(){
   	root = null;
   	numPairs  = 0;
   }

   private Node findKey(Node R, String k){
   	if(R==null || k.compareTo(R.key) == 0){
         return R;
      }
      if(k.compareTo(R.key)<0){
         return findKey(R.left,k);
      }
      else
         return findKey(R.right, k);
   }
   // findParent()
   // returns the parent of N in the subtree rooted at R, or returns NULL 
   // if N is equal to R. (pre: R != NULL)
   private Node findParent(Node N, Node R){
      Node P = null;
      if( N!=R ){
      P = R;
      while( P.left!=N && P.right!=N ){
         if(N.key.compareTo(P.key)<0)
            P = P.left;
         else
            P = P.right;
      }
   }
   return P;
   }
   // findLeftmost()
   // returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
   Node findLeftmost(Node R){
   Node L = R;
      if( L!=null) for( ; L.left!=null; L=L.left) ;
         return L;
   }

   // printInOrder()
   // prints the (key, value) pairs belonging to the subtree rooted at R in order
   // of increasing keys to file pointed to by out.
   String printInOrder(Node R){
	String s = "";
      if( R!=null){
        s =  printInOrder(R.left) + R.key + " " + R.value + "\n" +printInOrder(R.right);
      }
      return s;
   }

//public functions------------------------------------------------------------------------
   public int size(){
   	return numPairs;	
   }

   public boolean isEmpty(){
   	return (numPairs == 0);
   }

   public String lookup(String key){
	Node N;
      N = findKey(root,key);
      if(N==null){
         return null;
      }
      return N.value;
   }

   public void insert(String key, String value) throws DuplicateKeyException{
   	Node N, A, B;
   	if(lookup(key) != null){                      //key is already in the list
   		throw new DuplicateKeyException("Insert() trying to add a duplicate key");
   	}
   	N= new Node(key, value);
	B = null;
	A=root;
	while (A!=null){
		B=A;
		if(key.compareTo(A.key)<0) A = A.left;
		else A = A.right;
	}
	if(B==null) root = N;
	else if(key.compareTo(B.key)<0) B.left = N;
	else B.right = N ;
	numPairs++;   
   }

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
   public void delete(String key) throws KeyNotFoundException{
   	Node N, P, S;
   N = findKey(root,key );
   if( N==null ){
      throw new KeyNotFoundException("delete() trying to delte a key that doesn't exist");
   }
   if( N.left==null && N.right==null ){  // case 1 (no children)
      if( N==root ){
         root = null;
      }else{
         P = findParent(N, root);
         if( P.right==N ) P.right = null;
         else P.left = null;
      }
   }else if( N.right==null){  // case 2 (left but no right child)
      if( N==root ){
         root = N.left;
      }else{
         P = findParent(N, root);
         if( P.right==N ) P.right = N.left;
         else P.left = N.left;
      }
   }else if( N.left==null){  // case 2 (right but no left child)
      if( N==root ){
         root = N.right;
      }else{
         P = findParent(N, root);
         if( P.right==N ) P.right = N.right;
         else P.left = N.right;
      }
   }else{                     // case3: (two children: N->left!=NULL && N->right!=NULL)
      S = findLeftmost(N.right);
      N.key = S.key;
      N.value = S.value;
      P = findParent(S, N);
      if( P.right==S ) P.right = S.right;
      else P.left = S.right;
   }
   numPairs--;

   }
   
   public void makeEmpty(){
   	root = null;
   	numPairs = 0;

  }

  // toString()
  // returns a String representation of this Dictionary
  // overrides Object's toString() method
  // pre: none
   public String toString(){
	
   return printInOrder(root);
   }

}
