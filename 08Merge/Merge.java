public class Merge{

    //wrapper and recursive function
    public static void mergesort(int[] ary){
	//base case:
	if(ary.length == 1){
	    return;
	}

	//recursive calls:
	else{
	    int[] left = new int[ary.length / 2];
	    int[] right = new int[ary.length - (ary.length / 2)];

	    //helper variable to keep track of right side's index:
	    int rightCounter = 0;

	    //making copies of left and right sides of ary
	    for(int i = 0; i < ary.length; i++){
		if(i < ary.length / 2){
		    left[i] = ary[i];
		}
		else{
		    right[rightCounter] = ary[i];
		    rightCounter++;
		}
	    }

	    //recursion:
	    mergesort(left);
	    mergesort(right);

	    //and then, at the end, merge them all together again
	    merge(left, right, ary);
	}
    }

    //helper function that's actually responsible for the merging
    //assumes that a and b are already sorted
    //assumes that destination.length == a.length + b.length
    private static void merge(int[] a, int[] b, int[] destination){
	int indexA = 0;
	int indexB = 0;

	for(int indexDest = 0; indexDest < destination.length; indexDest++){
	    //note to self: I tried to combine these into two statements
	    //instead of four, but I couldn't get it to work. Huh. 
	    if(indexA == a.length){ //if it reaches the end of ary a
		destination[indexDest] = b[indexB];
		indexB++;
	    }
	    else if(indexB == b.length){ //if it reaches the end of ary b
		destination[indexDest] = a[indexA];
		indexA++;
	    }
	    else if(a[indexA] < b[indexB]){
		destination[indexDest] = a[indexA];
		indexA++;
	    }
	    else{
		destination[indexDest] = b[indexB];
		indexB++;
	    }
	}
    }

    //for tests
    public static void printAry(int[] ary){
	for(int i = 0; i < ary.length; i++){
	    System.out.print(ary[i] + " ");
	}
	System.out.println();
    }
	    
    //TESTS
    public static void main(String[] args){
	int[] a = {1, 3, 5, 3, 4, 2, 5, 7, 2};

      	printAry(a);
	mergesort(a);
	printAry(a);

	//tests for private merge method:
	int[] dest = new int[8];
	int[] b = {8, 8, 8, 8};
	int[] c = {0, 1, 4, 6};

	merge(b, c, dest);
	printAry(dest);
	
	
    }
}
    
