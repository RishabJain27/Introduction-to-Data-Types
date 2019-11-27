//Rishab Jain, rjain9, 12B, 7/11/17, Determines if a word is in a file and which line in the file it is in
import java.io.*;
import java.util.Scanner;
class Search{
	public static void main(String[] args) throws IOException{
      // check number of command line arguments
      if(args.length <= 1){
         System.err.println("Usage: LineCount file");
         System.exit(1);
      }
      
      // count the number of lines in file
      Scanner in = new Scanner(new File(args[0]));
      int lineCount = 0;
      while( in.hasNextLine() ){
         in.nextLine();
         lineCount++;
      }
      //Array for all the words in the file
      String[] words = new String[lineCount];
      //Array for number of lines
      int[] lineNumber = new int[lineCount];
      //Scanner for the file
      Scanner inWords = new Scanner(new File(args[0]));
      
      int wordNumber = 0;
      int lineNumbers=1;
      //Determines array for words and lines
      while(inWords.hasNextLine()){
      	words[wordNumber] = inWords.nextLine();
      	lineNumber[wordNumber] = lineNumbers; 
      	wordNumber++;
      	lineNumbers++;
      }
      in.close();

      int temp = 0;
      int n = args.length;
      //Keeps reading line from user until reads all the words on the input line
      for( int i = 1; i<n ; i++){
 
      mergeSort(words,lineNumber,0,wordNumber-1);
      temp = binarySearch(words,0,wordNumber-1,args[i]);
      
      if(temp >= 0){
  	  System.out.println(args[i] + " found on line " + lineNumber[temp]);
  	  }
  	  else{
  	  	System.out.println(args[i]+ " not found");
  	  }
  	}
   }
   //Determines where target word is in the String[] A array
   static int binarySearch(String[] A, int p, int r,  String target){
      int q;
      if(p > r) {
         return -1;
      }else{
         q = (p+r)/2;
         if(target.compareTo(A[q]) == 0){
            return q;
         }else if(target.compareTo(A[q])<0){
            return binarySearch(A, p, q-1, target);
         }else{ // target > A[q]
            return binarySearch(A, q+1, r, target);
         }
      }
   }
   // mergeSort()
   // sort subarray A[p...r]
   public static void mergeSort(String[] A,int[] lineNumber, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
 
         mergeSort(A, lineNumber, p, q);
         mergeSort(A, lineNumber, q+1, r);
         merge(A, lineNumber, p, q, r);
      }
   }

   // merge()
   // merges sorted subarrays A[p..q] and A[q+1..r]
   public static void merge(String[] A,int[] lineNumber, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      String[] L = new String[n1];
      String[] R = new String[n2];
      //Copies same format for String but now does it for int
      int[] nL = new int[n1];
      int[] nR = new int[n2];
      
      int i, j, k;

      for(i=0; i<n1; i++){
         L[i] = A[p+i];
         nL[i] = lineNumber[p+i];
      }
      for(j=0; j<n2; j++){ 
         R[j] = A[q+j+1];
         nR[j] = lineNumber[q+j+1];
      }

      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L[i].compareTo(R[j])<0 ){
               A[k] = L[i];
               lineNumber[k] = nL[i];
               i++;
            }else{
               A[k] = R[j];
               lineNumber[k] = nR[j];
               j++;
            }
         }else if( i<n1 ){
            A[k] = L[i];
            lineNumber[k] = nL[i];
            i++;
         }else{ // j<n2
            A[k] = R[j];
            lineNumber[k] = nR[j];
            j++;
         }
      }
   }
}