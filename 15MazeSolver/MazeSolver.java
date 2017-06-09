import java.util.ArrayList;

public class MazeSolver{
    private Maze board;
    private Frontier front;
    private boolean animate;
    
    public MazeSolver(String filename){
	this(filename, false);
    }
    
    public MazeSolver(String filename, boolean animate){
	board = new Maze(filename);
	this.animate = animate;
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
	    front = new FrontierPriorityQueue();
	}
	if(style == 3){ //a-star 
	    front = new FrontierPriorityQueue();
	    //gonna have to be separate though,,, for the creation of Locations
	} 

	//add start node to Frontier
	front.add(board.getStart());

	Location current = null;
	
	//while NOT at end AND NOT empty
	while(front.size() != 0){
	    //get next node: 
	    current = front.next();
	    
	    //wipe current off the frontier:
	    board.set(current.row(), current.col(), '.');

	    
	    //if at end, end it 
	    if(isAtEnd(current)){
		retracePath(current, true);
		if(animate) System.out.println(board.toString(13));
		return;
	    }

	    //otherwise...
	
	    //process that node:
	    ArrayList<Location> ary = null;
	    
	    if(style == 3){ //for astar
		ary = findNext(current, true);
	    }
	    else{
		ary = findNext(current, false);
	    }
	    
	    for(int i = 0; i < ary.size(); i++){
		front.add(ary.get(i));
	    }

	}
    }

    //helper method: 'Am I done?'
    private boolean isAtEnd(Location current){
	int endR = board.getEnd().row();
	int endC = board.getEnd().col();

	int r = current.row();
	int c = current.col();

	return endR == r && endC == c;
    }
	
    //helper method that figures out where the next frontier spots are
    private ArrayList<Location> findNext(Location l, boolean astar){
	ArrayList<Location> ans = new ArrayList<Location>();
	int[][] possiblePaths = {
	    {1, 0},
	    {-1, 0},
	    {0, 1},
	    {0, -1}
	};
	
	for(int i = 0; i < 4; i++){
	    int newR = l.row() + possiblePaths[i][0];
	    int newC = l.col() + possiblePaths[i][1];
	    
	    if(board.get(newR, newC) == ' '){
		//add the new location to ans
		if(astar){ //astar version
		    ans.add(new Location(newR, newC, l,
					 fromStart(newR, newC),
					 toEnd(newR, newC), true));
		}			    				  
		else{
		    ans.add(new Location(newR, newC, l,
					 fromStart(newR, newC),
					 toEnd(newR, newC)));
		}
		//set to frontier:
		board.set(newR, newC, '?');
		
		if(animate) System.out.println(board.toString(13));
	    }

	}

	return ans;
    }

    //helper methods for calculating Manhattan distances:

    //for best-first and aStar:
    private int fromStart(int r, int c){
	int startR = board.getStart().row();
	int startC = board.getStart().col();

	return Math.abs(r - startR) + Math.abs(c - startC);
    }

    //for aStar:
    private int toEnd(int r, int c){
	int endR = board.getEnd().row();
	int endC = board.getEnd().col();
	
	return Math.abs(r - endR) + Math.abs(c - endC);
    }

    //to retrace your path once you're done solving the maze
    private void retracePath(Location l, boolean firstTime){
	//set the end to 'E'
	if(firstTime){
	    board.set(l.row(), l.col(), 'E');
	    if(animate) System.out.println(board.toString(13));
	    retracePath(l.prev(), false);
	}
	else if(l.prev() == null){
	    board.set(l.row(), l.col(), 'S');
	    if(animate) System.out.println(board.toString(13));
	    return;
	}
	else{
	    board.set(l.row(), l.col(), '@');
	    if(animate) System.out.println(board.toString(13));
	    retracePath(l.prev(), false);
	}
    }

    //toString(int)
    public String toString(int n){
	return board.toString(13);
    }
    
    //toString()
    public String toString(){
	return board.toString();
    }
    
    public static void main(String[] args){
	String f = args[0];
	MazeSolver m = new MazeSolver(f, false);

	int solveStyle = Integer.parseInt(args[1]);
	m.solve(solveStyle);
	System.out.println(m);
    }


}
