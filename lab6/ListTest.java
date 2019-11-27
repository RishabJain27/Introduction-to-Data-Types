//ListTest.java
////Rishab Jain, rjain9, 12M, 8/11/17
class ListTest{

 public static void main(String[] args){
List<String> A = new List<String>();

//Test add
A.add(1,"a");
A.add(2,"b");
System.out.println("Add to items: " + A);

//Test isEmpty()
boolean a = false;
a = A.isEmpty();
System.out.println("Test isEmpty(): "+ a);

//Test get()
System.out.println("Test get(), return 1st element in list: " + A.get(1) );

//Test size()
System.out.println("Test size(): " + A.size());

//Test remove()
A.remove(1);
System.out.println("Test remove of element 1: " + A);

//Test removeAll()
A.removeAll();
System.out.println("Empty list: " + A);

}
}