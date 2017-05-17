public class MazeSolver{
    private Maze board;
    private Frontier front;

    public MazeSolver(String filename){
	this(filename, false);
    }
    
    public MazeSolver(String filename, boolean animate){
	board = new Maze(filename);
	//something with animate
    }

    public void solve(){
	solve(1);
    }

    /**
     *Style is 0-3, where 0 = Depth; 1 = Breadth; 2 = Best; 3 = A*
     *Method will initiate the Frontier based on which style was chosen
     *It will then add the starting location of the maze to the Frontier
     *Finally it will process each subsequent element of the Frontier until
     *the end is found.
    */
    public void solve(int style){
	if(style == 0){ //depth-first search
	    front = new StackFrontier(); 
	}
	if(style == 1){ //breadth-first search
	    front = new QueueFrontier();
	}
	if(style == 2){ //best-first search
	    front = new PriorityQueueFrontier();
	}
	if(style == 3){ //a-star 
	    front = new PriorityQueueFrontier();
	    //gonna have to be separate though,,, for the creation of Locations
	} 

	

    }

    //helper method that figures out where the next frontier spots are
    private Location[] findNext(Location l, boolean astar){

    }
    
    public void toString(){
	return board;
    }
   
    public static void main(String[] args){
	System.out.println("Hi");
    }


}
