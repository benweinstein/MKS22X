public class PriorityQueue{
    //uses MyHeap
    MyHeap data;
    
    public PriorityQueue(){
	data = new MyHeap(false); //need a min heap
    }

    public Location remove(){
	return data.remove();
    }
    
    public void add(Location l){
	data.add(l);
    }

    public static void main(String[] args){
	System.out.println("Hi");
    }
}
