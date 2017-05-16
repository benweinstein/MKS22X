public class FrontierPriorityQueue{
    private PriorityQueue locations;

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
