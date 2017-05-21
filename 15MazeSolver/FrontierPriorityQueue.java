public class FrontierPriorityQueue implements Frontier{
    private PriorityQueue locations;
    private int size;

    public FrontierPriorityQueue(){
	locations = new PriorityQueue();
    }
   
    public void add(Location l){
	locations.add(l);
	size++;
    }

    public Location next(){
	size--;
	return locations.remove();
    }

    public int size(){
	return size;
    }

    public static void main(String[] args){
	System.out.println("Hi");
    }
}
