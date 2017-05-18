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

    public int row(){
	return row;
    }

    public int col(){
	return col;
    }

    public int getDistToStart(){
	return distToStart;
    }

    public int getDistToGoal(){
	return distToGoal;
    }


    //compareTo
    public int compareTo(Location other){
	if(aStar){
	    int priority1 = getDistToGoal() + getDistToStart();
	    int priority2 = other.getDistToGoal() + other.getDistToStart();
	    return priority1 - priority2;
	}
	else{
	    return getDistToGoal() - other.getDistToGoal();
	}
    }

    public static void main(String[] args){
	System.out.println("Hi");
    }
}
    
