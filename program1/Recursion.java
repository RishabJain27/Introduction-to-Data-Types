class Recursion{
    public static void main(String[] args){
     int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
     int[] B = new int[A.length];
     int[] C = new int[A.length];
     int minIndex = minArrayIndex(A, 0, A.length-1);
     int maxIndex = maxArrayIndex(A, 0, A.length-1);

     for(int x: A) System.out.print(x+" ");
     System.out.println();

     System.out.println( "minIndex = " + minIndex );
     System.out.println( "maxIndex = " + maxIndex );
    
     reverseArray1(A, A.length, B);
     for(int x: B) System.out.print(x+" ");
     System.out.println();

     reverseArray2(A, A.length, C);
     for(int x: C) System.out.print(x+" ");
     System.out.println();

     reverseArray3(A, 0, A.length-1);
     for(int x: A) System.out.print(x+" ");
     System.out.println();
     }
     //Reverses array from leftmost to rightmost elements
     static void reverseArray1(int[] X, int n, int[] Y){
        if(n>0){
            Y[n-1] = X[X.length-n];
            reverseArray1(X,n-1,Y);
        }
     }
     //Reverses array from rightmost to leftmost elements
     static void reverseArray2(int[] X, int n, int[] Y){
        if(n>0){
            reverseArray2(X,n-1,Y);
            Y[X.length-n] = X[n-1];
        }
     }
     //Reverses array on a given interval 
     static void reverseArray3(int[] X, int i, int j){
        int k = (X.length -1)/2;
        if(i<k){
            int temp = X[i];
            X[i] = X[j];
            X[j] = temp;
            reverseArray3(X,i+1,j-1);
        }
    }

    //Returns max Index of Array
    static int maxArrayIndex(int[] X, int p, int r){           
        //base case
        if(p==r){
            return p;
        }
        //halfway point of array
        int k = (p+r)/2;

        //Max of left and right side
        int leftMax = maxArrayIndex(X,p,k);
        int rightMax = maxArrayIndex(X,k+1,r);

        //return index of max number
        if(X[leftMax]>X[rightMax]){
            return leftMax;
        }
        else
            return rightMax;      
                
    }

    //Returns min Index of Array
    static int minArrayIndex(int[] X, int p, int r){
        //base case
        if(p==r){
            return p;
        }

        //halfway point of array
        int k = (p+r)/2;

        //Min of left and right side
        int leftMin = minArrayIndex(X,p,k);
        int rightMin = minArrayIndex(X,k+1,r);

        //compares left and right side and returns index of smallest number
        if(X[leftMin]<X[rightMin]){
            return leftMin;
         }
        else{
            return rightMin; 
        } 
     }
}
