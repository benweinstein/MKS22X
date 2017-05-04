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
	System.out.println(this.toString()); //FOR TESTING

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
	if(data.get(index).compareTo(getChildR(index)) * minMaxConstant >= 0 &&
	   data.get(index).compareTo(getChildL(index)) * minMaxConstant >= 0){
	    return;
	}
	//Remember: you go down whichever side is smaller
	//if not, try to see if you can go down the left branch first
	else if(getChildL(index).compareTo(getChildR(index)) * minMaxConstant <= 0){
	    swap(index, index * 2); 
	    pushDown(index * 2);
	}
	//otherwise, go down the right side
	else{
	    swap(index, index * 2 + 1);
	    pushDown(index * 2 + 1);
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
    
    //should these return Strings or ints (the indeces of the child//parent)???
    private String getChildR(int index){
	return data.get(index * 2 + 1);
    }

    private String getChildL(int index){
	return data.get(index * 2);
    }

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
    }
}
