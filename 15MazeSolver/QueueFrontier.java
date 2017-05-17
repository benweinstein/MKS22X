import java.util.LinkedList;

public class QueueFrontier implements Frontier{
    private LinkedList<Location> data;

    public QueueFrontier(){
	data = new LinkedList<Location>();
    }

    public void add(Location l){
	data.add(l);
    }

    public Location next(){
	return data.poll();
    }
}
