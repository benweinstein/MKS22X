import java.util.ArrayList;

public class MyHeap{
    private int size;
    private int minMaxConstant; //EITHER -1 or 1, ALWAYS
    private ArrayList<Location> data;

    public MyHeap(){
	minMaxConstant = 1; //this is a maxHeap
	data = new ArrayList<Location>();
	data.add(null); //null bc will never be accessed
    }

    //if true: maxHeap; if false: minHeap
    public MyHeap(boolean minOrMax){
	if(minOrMax){
	    minMaxConstant = 1;
	}
	else{
	    minMaxConstant = -1;
	}

	data = new ArrayList<Location>();
	data.add(null); //null bc will never be accessed
    }

    //in order to make RunningMedians much easier
    public int size(){
	return size;
    }

    public Location peek(){
	return data.get(1);
    }

    public Location remove(){
	//exception case:
	if(size == 1){
	    size--;
	    return data.remove(1);
	}
	//any other case:
	else{	
	    Location holder = data.get(1);

	    data.set(1, data.remove(size));
	    
	    size--;
	    pushDown(1);

	    return holder;
	}
    }

    public void add(Location n){
	size++;
	data.add(n);
	pushUp(size);
    }

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

    //precondition: not already at root (top parent)
    //returns parent Integer
    private Location getParent(int index){
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
	Location holder = data.get(j);
	data.set(j, data.get(k));
	data.set(k, holder);
    }

}

