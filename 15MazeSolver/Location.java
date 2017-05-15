public class Location implements Comparable<Location>{
    private int row;
    private int col;
    private Location previous;
    private int distToStart;
    private int distToGoal; //stores reference to the goal
    private boolean aStar; //when true, compareTo()  will use previousDistance
                           //+ distanceToGoal. When false, compareTo() will use
                           //distanceToGoal only

    /*
      CONSTRUCTORS
    */

    public Location(int r, int c, Location previous, int distToStart, int distToGoal){

    }

    public Location(int r, int c, Location previous, int distToStart, int distToGoal, boolean aStar){

    }

    /*
      METHODS
    */

    //accessors as needed...

    //compareTo
    public int compareTo(Location other){
	if(aStar){
	    //use both
	}
	else{
	    //only one
	}
	return 0;
    }

    public static void main(String[] args){
	System.out.println("Hi");
    }
}
    
