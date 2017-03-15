import java.util.Random;

public class Quick{
    
    /**
     *Uses part method as a helper (copied over from Partition.java
     *just to have it all in one place).
     *@return the value that is the kth smallest value of the array.
     */
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

    //helper part method
    public static int part(int[] data, int start, int end){
	Random rand = new Random();
	int index = rand.nextInt(end - start + 1) + start;
	//System.out.println(index + "\n");
	
	//switch the 'to be partitioned' element to the end
	int temp = data[index];
	data[index] = data[end];
	data[end] = temp;

	//now consider the array from start to end - 1 like a blank array:
	int back = end;
	for(int front = start; front < end && front < back; front++){
	    //if current, front element > to-be-partitioned element:
	    if(data[front] > data[end]){
		//switch with 'back' spot
		temp = data[front];
		data[front] = data[back - 1];
		data[back - 1] = temp;

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
	temp = data[back];
	data[back] = data[end];
	data[end] = temp;
	
	/*
	//for tests: 
	for(int i = 0; i < data.length; i++){
	    System.out.print(data[i] + ", ");
	}
	System.out.println("\n");
	*/

	return back; //returns final position of partitioned element
    }

    //TESTS
    public static void main(String[] args){
	int[] a = {11, 19, 12, 14, 13, 18, 10, 15, 17, 16};
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
    }
}
