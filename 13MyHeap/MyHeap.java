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

	size--;
    }

    public void add(String s){

	size++;
    }

    private void pushDown(){

    }

    private void pushUp(){

    }
    
    public String toString(){
	String ans = "";
	for(int i = 1; i < data.size(); i++){
	    ans += data.get(i) + ", ";
	}
	return ans;
    }
    
    public static void main(String[] args){
	System.out.println("Hi");
    }
}
