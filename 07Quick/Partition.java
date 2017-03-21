import java.util.Random;

public class Partition{
    //main function. for Quick Select
    public static int part(int[] data, int start, int end){
	Random rand = new Random();
	int index = rand.nextInt(end - start + 1) + start;
	System.out.println(index + "\n");
	
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

		//for tests:
		for(int i = 0; i < data.length; i++){
		    System.out.print(data[i] + ", ");
		}
		System.out.println("\n");
		
		front--; //so we go back over
		back--; //so we move this variable left on the array
	    }
	    //else: do nothing (let 'front' increment by 1)
	}

	//switch the to-be-partitioned element with the current 'back' element
	temp = data[back];
	data[back] = data[end];
	data[end] = temp;

	//for tests: 
	for(int i = 0; i < data.length; i++){
	    System.out.print(data[i] + ", ");
	}
	System.out.println("\n");

	return back; //returns final position of partitioned element
    }


    //basic idea 
    public static void partition(int[] ary, int index){
	//switch the 'to be partitioned' element to the end
	int temp = ary[index];
	ary[index] = ary[ary.length - 1];
	ary[ary.length - 1] = temp;
	
	//now consider the rest of the array - 1 like a blank array:
	int back = ary.length - 1;
	for(int front = 0; front < ary.length - 1 && front < back; front++){
	    //if current, front element > to-be-partioned element:
	    if(ary[front] > ary[ary.length - 1]){
		//switch with 'back' spot
		temp = ary[front];
		ary[front] = ary[back - 1];
		ary[back - 1] = temp;

		//for tests (super helpful!!!):
		/*
		for(int i = 0; i < ary.length; i++){
		    System.out.print(ary[i] + ", ");
		}
		System.out.println("\n");
		*/
		
		front--; //so we go back over 
		back--; //so we move this variable left on the array
	    }
	    //else: do nothing (let 'front' increment by 1) 
	}

	//switch the to-be-partitioned element with the current 'back' element
	temp = ary[back];
	ary[back] = ary[ary.length - 1];
	ary[ary.length - 1] = temp;
	
	//for tests:
	/*
	for(int i = 0; i < ary.length; i++){
	    System.out.print(ary[i] + ", ");
	}
	System.out.println("\n");
	*/
    }
	
    //TESTS
    public static void main(String[] args){
	//int[] ary = {11, 14, 12, 13, 17, 19, 16, 15, 10, 18};
	//partition(ary, 0);

	int[] data = {11, 14, 12, 13, 17, 19, 16, 15, 10, 18};
	System.out.println(part(data, 1, 9)); 
	System.out.println(Quick.newPart(data, 1, 9));
    }
}
