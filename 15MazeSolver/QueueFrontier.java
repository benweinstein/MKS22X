import java.util.LinkedList;

public class QueueFrontier implements Frontier{
    private LinkedList<Location> data;
    private int size;

    public QueueFrontier(){
	data = new LinkedList<Location>();
    }

    public void add(Location l){
	data.add(l);
	size++;
    }

    public Location next(){
	size--;
	return data.poll();
    }

    public int size(){
	return size;
    }
}
