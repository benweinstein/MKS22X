import java.util.Stack;

public class StackFrontier implements Frontier{
    private Stack<Location> data; 

    public StackFrontier(){
	data = new Stack<Location>();
    }

    public void add(Location l){
	data.push(l);
    }

    public Location next(){
	return data.pop();
    }
}
