import java.util.ArrayList;

public class MyHeap{
    private int size;
    private int minMaxConstant;//EITHER -1 or 1, ALWAYS
    private ArrayList<String> data;
    
    public MyHeap(){
	minMaxConstant = 1; //maxHeap
	data = new ArrayList<String>();
	data.add("DO NOT PRINT ME");
    }

    //if true: maxHeap; if false: minHeap
    public MyHeap(boolean minOrMax){
	if(minOrMax){
	    minMaxConstant = 1;
	}
	else{
	    minMaxConstant = -1;
	}

	data = new ArrayList<String>();
	data.add("DO NOT PRINT ME"); 
    }

    public String peek(){
	return data.get(1);
    }

    public String remove(){
	String holder = data.get(size);

	data.set(1, data.remove(size));

	size--;
	pushDown(1);


	return holder;
    }

    public void add(String s){
	size++;
	data.add(s); 
	pushUp(size);
    }

    //NEED TO ADD BOOLEANS FOR IF THERE IS A RIGHT//LEFT CHILD. IN ORDER!!!
    private void pushDown(int index){
	//base case
	if(childR(index) == -1 && childL(index) == -1){
	    return;
	}

	//other cases
        if(childR(index) == -1){
	    if(data.get(index).compareTo(data.get(childL(index))) * minMaxConstant < 0){
		swap(index, childL(index));
		pushDown(childL(index));	       
	    }
	    else{
		return;
	    }
	}

	//more:
	else if(data.get(childL(index)).compareTo(data.get(childR(index))) * minMaxConstant >= 0){
	    swap(index, childL(index));
	    pushDown(childL(index));
	}
	else{
	    swap(index, childR(index));
	    pushDown(childR(index));
	}
    }

    private void pushUp(int index){
	//base cases
	if(index == 1){
	    return;
	}
	if(data.get(index).compareTo(getParent(index)) * minMaxConstant <= 0){
	    return;
	}
	//otherwise...
	swap(index, index / 2);
	pushUp(index / 2);
    }

    //returns index of right child, -1 if nonexistent
    private int childR(int index){
	if(index * 2 + 1 > size){
	    return -1;
	}
	return index * 2 + 1;
    }

    //returns index of left child, -1 if nonexistent
    private int childL(int index){
	if(index * 2 > size){
	    return -1;
	}
	return index * 2;
    }

    //returns parent String
    private String getParent(int index){
	return data.get(index / 2);
    }

    public String toString(){
	String ans = "";
	for(int i = 1; i < data.size(); i++){
	    ans += data.get(i) + ", ";
	}
	return ans;
    }
    
    private void swap(int j, int k){
	String holder = data.get(j);
	data.set(j, data.get(k));
	data.set(k, holder);
    }

    public static void main(String[] args){
	MyHeap h = new MyHeap();
	h.add("a");
	h.add("b");
	h.add("bb");
	System.out.println(h); //bb, a, b

	h.remove();
	System.out.println(h.peek());
	System.out.println(h.remove());
    }
}
