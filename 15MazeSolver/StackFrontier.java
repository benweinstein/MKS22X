import java.util.Stack;

public class StackFrontier implements Frontier{
    private Stack<Location> data;
    private int size;

    public StackFrontier(){
	data = new Stack<Location>();
    }

    public void add(Location l){
	data.push(l);
	size++;
    }

    public Location next(){
	size--;
	return data.pop();
    }

    public int size(){
	return size;
    }
}
