import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze{
    private char[][] maze;
    private boolean animate = false;

    /*Constructor loads a maze text file, and sets animate to false by default.
      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - locations that cannot be moved onto
      ' ' - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)
      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!
      3. When the file is not found OR there is no E or S then: print an error and exit the program.
    */
    public Maze(String filename){
	String mazeString = "";
	int numRows = 0;
	
	try{
	    File f = new File(filename);
	    Scanner mazeScan = new Scanner(f);
	    
	    while(mazeScan.hasNextLine()){
		mazeString += mazeScan.nextLine();
		mazeString += "\n";
		numRows++;
	    }
	}catch(FileNotFoundException e){
	    System.exit(1);
	}
	//System.out.println(mazeString);

	//finding length of a row (numCols)
	int numCols = 0;
	
	while(mazeString.charAt(numCols) != '\n'){
	    numCols++;
	}
	System.out.println("r = " + numRows + ", c = " + numCols);
	//now we can initialize the maze char[][]!!!
	maze = new char[numRows][numCols];


	try{
	    File ff = new File(filename);
	    Scanner mazeLayer = new Scanner(ff);

	    int r = 0, c = 0;
	    while(mazeLayer.hasNext() && r < numRows){
		char temp = mazeLayer.next().charAt(0);//b/c .next() returns a
		//String, not a char
		if(temp == '\n'){
		    c = 0;
		    r++;
		}
		else{
		    maze[r][c] = temp;
		    c++;
		}
	    }
	}catch(FileNotFoundException e){
	    System.exit(1);
	}
	
	/*
	int i = 0; //for mazeString.charAt(i)
	for(int r = 0; r < numRows; r++){
	    for(int c = 0; c < numCols; c++){
		if(mazeString.charAt(i) != '\n'){
		    maze[r][c] = mazeString.charAt(i);
		    System.out.println(printMaze(maze));
		}
		else{
		    i++;
		    maze[r][c] = mazeString.charAt(i);
		    System.out.println(printMaze(maze));
		}
		i++;
	    }
	}
	*/
    }

    public void setAnimate(boolean b){
        animate = b;
    }


    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }



    /*Wrapper Solve Function
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public boolean solve(){
            int startr=-1, startc=-1;
            //Initialize starting row and startint col with the location of the S. 
	    for(int r = 0; r < maze.length; r++){
		for(int c = 0; c < maze[r].length; c++){
		    if(maze[r][c] == 'S'){
			startr = r;
			startc = c;
		    }
		}
	    }

            maze[startr][startc] = ' ';//erase the S, and start solving!
            return solve(startr,startc);
    }

    /*
      Recursive Solve function:
      A solved maze has a path marked with '@' from S to E.
      Returns true when the maze is solved,
      Returns false when the maze has no solution.

      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
    */
    private boolean solve(int row, int col){
	if(animate){
            System.out.println("\033[2J\033[1;1H"+this);

            wait(20);
	}
	
        //COMPLETE SOLVE

        return false; //so it compiles
    }

    //WAIT
    private void wait(int millis){
	try{
	    Thread.sleep(millis);
	}
	catch(InterruptedException e){

	}
    }

    //toString()
    public String toString(){
	String ans = "";
	
	for(int r = 0; r < maze.length; r++){
	    for(int c = 0; c < maze.length; c++){
		ans += maze[r][c];
	    }
	    ans += '\n';
	}

	return ans;
    }
	    
    //TESTS
    public static void main(String[] args){
	//3x3 is working
	//Maze1 is not...
	Maze m = new Maze("3x3.txt"); 
       	System.out.println(m);
    }
}


