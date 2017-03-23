import java.util.Random;

public class Quick{

    /**
     *Wrapper function for quicksort.
     *Calls qsh (helper function)
     */
    public static void quicksort(int[] data){
	qsh(data, 0, data.length - 1);
    }

    //recursive helper for quicksort (NEW--DUTCH FLAG VERSION)
    private static void qsh(int[] data, int left, int right){
	if(left < right){
	    Random rand = new Random();
	    int index = rand.nextInt(right - left + 1) + left;
	    //System.out.println("rand: " + index);

	    swap(data, index, left);
	    
	    int gt = right;
	    int i = left + 1;
	    int lt = left + 1;
	    
	    while(i <= gt){
		if(data[i] < data[left]){
		    swap(data, i, lt);
		    i++;
		    lt++;
		}
		else if(data[i] == data[left]){
		    i++;
		}
		else{
		    swap(data, i, gt);
		    gt--;
		}
	    }
	    
	    swap(data, left, lt - 1);

	    //printAry(data);
	    
	    //recursive calls (remember that they're inclusive):
	    qsh(data, i, right);
	    qsh(data, left, lt - 1);
	}
    }

    /*
    //recursive helper for quicksort (OLD)
    private static void qsh(int[] data, int left, int right){
	if(left < right){
	    int p = part(data, left, right);
	    qsh(data, p + 1, right);
	    qsh(data, left, p - 1);
	}
    }
    */

    //NEW QUICKSELECT (DUTCH FLAG):
    public static int quickselect(int[] data, int k){
	return selectH(data, k, 0, data.length - 1);
    }

    //recursive helper for quickselect (NEW)
    private static int selectH(int[] data, int k, int left, int right){
	//base case (NEEDED THIS FOR WHEN ARRAY GETS TO SIZE OF ONE):
	if(left == right){
	    return data[left];
	}
	
	Random rand = new Random();
	int index = rand.nextInt(right - left + 1) + left;
	//System.out.println("rand: " + index);
	
	swap(data, index, left);

	int gt = right;
	int i = left + 1;
	int lt = left + 1;
	
	while(i <= gt){
	    if(data[i] < data[left]){
		swap(data, i, lt);
		i++;
		lt++;
	    }
	    else if(data[i] == data[left]){
		i++;
	    }
	    else{
		swap(data, i, gt);
		gt--;
	    }
	}
	
	swap(data, left, lt - 1);
	//printAry(data);
	
	//if it's in the range of clustered equals, then you're done:
	if(k <= gt && k >= lt){
	    return data[k];
	}
       
	//if not, then you go to the recursive calls:
        if(k < lt){ //if on the left
	    return selectH(data, k, left, lt - 1);
	}
	
	return selectH(data, k, i, right); //if on the right	
    }


	
    /*OLD 
    public static int quickselect(int[] data, int k){
	int start = 0;
	int end = data.length - 1;
	
	int ansIndex = data.length; //so that the boolean in the while loop 
	//below will always trigger the body of the loop AT LEAST once!!!
	while(ansIndex != k){	    
	    if(ansIndex != data.length){
		//set new start, end values
		if(ansIndex > k){
		    end -= (end - ansIndex);
		}
		else{
		    start += (ansIndex - start);
		}
	    }
	    //else: no need, start and end are pre-set to run correctly at 
	    //initial outset (first run).

	    ansIndex = part(data, start, end);
	}

	return data[ansIndex];
    }
    */
    
    //helper part method (OLD)
    private static int part(int[] data, int start, int end){
	Random rand = new Random();
	int index = rand.nextInt(end - start + 1) + start;
	//System.out.println(index + "\n");
	
	//switch the 'to be partitioned' element to the end
	swap(data, index, end);

	//now consider the array from start to end - 1 like a blank array:
	int back = end;
	for(int front = start; front < end && front < back; front++){
	    //if current, front element > to-be-partitioned element:
	    if(data[front] > data[end]){
		//switch with 'back' spot
		swap(data, front, back - 1);

		/*
		//for tests:
		for(int i = 0; i < data.length; i++){
		    System.out.print(data[i] + ", ");
		}
		System.out.println("\n");
		*/

		front--; //so we go back over
		back--; //so we move this variable left on the array
	    }
	    //else: do nothing (let 'front' increment by 1)
	}
	
	//switch the to-be-partitioned element with the current 'back' element
	swap(data, back, end);
	
	/*
	//for tests: 
	for(int i = 0; i < data.length; i++){
	    System.out.print(data[i] + ", ");
	}
	System.out.println("\n");
	*/

	return back; //returns final position of partitioned element
    }

    //swap helper method:
    public static void swap(int[] ary, int x, int y){
	int helper = ary[x];
	ary[x] = ary[y];
	ary[y] = helper;
    }
    
    //for test
    public static void printAry(int[] ary){
	for(int i = 0; i < ary.length; i++){
	    System.out.print("" +  ary[i] + " ");
	}
	System.out.println();
    }

    //TESTS
    public static void main(String[] args){
	/*int[] a = {11, 19, 12, 14, 13, 18, 10, 15, 17, 16};
	System.out.println(quickselect(a, 0)); //should be 10
	System.out.println(quickselect(a, 1)); //11
	System.out.println(quickselect(a, 2)); //12
	System.out.println(quickselect(a, 3)); //13
	System.out.println(quickselect(a, 4)); //14
	System.out.println(quickselect(a, 5)); //15
	System.out.println(quickselect(a, 6)); //16
	System.out.println(quickselect(a, 7)); //17
	System.out.println(quickselect(a, 8)); //18
	System.out.println(quickselect(a, 9)); //19
	
	//first tests work!!!

	//for quicksort!!!
	printAry(a);
	quicksort(a);
	printAry(a);
	//finally work!!!
	*/

	//more with duplicates
	int[] b = {999, 999, 999, 4, 1, 0, 3, 2, 999, 999, 999};
	System.out.println(quickselect(b, 3));
	System.out.println(quickselect(b, 7));
	System.out.println(quickselect(b, 0));
	printAry(b);
	quicksort(b);
	printAry(b);
	
	/*
	//more tests for part()!!! On duplicates:
	printAry(b);
	System.out.println(part(b, 0, b.length - 1));
	printAry(b);

	//more!!! On a subset of an array:
	printAry(b);
	System.out.println(part(b, 3, 7));
	printAry(b);

	quicksort(a);
	printAry(a);

	quicksort(b);
	printAry(b);
	//both were successes!
	*/
    }
}
