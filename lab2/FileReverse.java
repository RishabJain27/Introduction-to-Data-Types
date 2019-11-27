//Rishab Jain,rjain9,12M,7/9/17,Program to reverse input of in into new file called out
// FileReverse.java
// Illustrates file IO and tokenization of strings and prints out reverse of in file.
import java.io.*;
import java.util.Scanner;
class FileReverse{
 public static void main(String[] args) throws IOException{
 Scanner in = null;
 PrintWriter out = null;
 String line = null;
 String[] token = null;
 int i, n, lineNumber = 0;
 // check number of command line arguments is at least 2
 if(args.length < 2){
 System.out.println("Usage: FileCopy <input file> <output file>");
 System.exit(1);
 }
 // open files
 in = new Scanner(new File(args[0]));
 out = new PrintWriter(new FileWriter(args[1]));
 // read lines from in, extract and print tokens from each line
 while( in.hasNextLine() ){
 	lineNumber++;
 	// trim leading and trailing spaces, then add one trailing space so
 	// split works on blank lines
 	line = in.nextLine().trim() + " ";
 	// split line around white space
 	token = line.split("\\s+");
 	// print out tokens
 	n = token.length;

 	for(i=0; i<n; i++){
 		out.println(stringReverse(token[i],token[i].length()));
 	}
 }
 // close files
 in.close();
 out.close();
 }
 public static String stringReverse(String s, int n){
 	if(n==1){
 		return s;
 	}
 	else{
 		return stringReverse(s.substring(1),n-1)+s.charAt(0);
 	}
 }

}