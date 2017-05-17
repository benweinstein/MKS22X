public class FrontierPriorityQueue implements Frontier{
    private PriorityQueue locations;

    //need booleans in constructors!!!!!!!!!!!!!!!!!!!!!!!!!
    public void add(Location l){
	locations.add(l);
    }

    public Location next(){
	return locations.remove();
    }

    public static void main(String[] args){
	System.out.println("Hi");
    }
}
