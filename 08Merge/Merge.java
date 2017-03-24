public class Merge{

    //wrapper and recursive function
    public static void mergesort(int[] ary){
	//base case:
	if(ary.length == 1){
	    return;
	}

	//recursive calls:
	else{
	    //int[] left = copy of the left side;
	    //int[] right = copy of the right side;
	    //mergesort(left);
	    //mergesort(right);
	    //mergeTheTwoHalvesIntoTheOriginalArray; //aka call merge()
	}
    }

    //helper function that's actually responsible for the merging
    //assumes that a and b are already sorted
    //assumes that destination.length == a.length + b.length
    private void merge(int[] a, int[] b, int[] destination){


    }

    //TESTS
    public static void main(String[] args){
	System.out.println("Hi");
    }
}
    
