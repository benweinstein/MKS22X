import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Silver{
    public Silver(){}
 
    private File silverFile;
    private Scanner silverScan;

    private int[][] pasture1;
    private int[][] pasture2;

    private int goalR;
    private int goalC;

    //BETTER METHOD -- IDEA FROM CLASS ON FRIDAY, 3/10/17
    public int silver(String filename){
	try{
	    silverFile = new File(filename);
	    silverScan = new Scanner(silverFile);
	}catch(FileNotFoundException e){
	    System.out.println("file not found");
	    System.exit(1);
	}

	int row = silverScan.nextInt();
	int col = silverScan.nextInt();

	pasture1 = new int[row][col];
	pasture2 = new int[row][col];

	//total number of seconds cow should take to get to new place in pasture
	int seconds = silverScan.nextInt();

	for(int r = 0; r < row; r++){
	    String line = silverScan.next();
	    for(int c = 0; c < col; c++){
		char spot = line.charAt(c);
		if(spot == '*'){
		    pasture1[r][c] = -1;
		    pasture2[r][c] = -1;
		}//everything else stays at 0
	    }
	}

	int r1 = silverScan.nextInt() - 1;
	int c1 = silverScan.nextInt() - 1;
	goalR = silverScan.nextInt() - 1;
	goalC = silverScan.nextInt() - 1;
	
	try{
	    pasture1[r1][c1] = 1;
	}catch(ArrayIndexOutOfBoundsException e){
	    System.out.println("invalid R1, C1");
	    System.exit(1);
	}
	
	return navigateH(pasture1, pasture2, seconds);  
    }

    //recursive helper for silver
    private int navigateH(int[][] oldAry, int[][] newAry, int seconds){
	//base case
	if(seconds == 0){
	    return oldAry[goalR][goalC];
	}

	//updating newAry
	for(int r = 0; r < newAry.length; r++){
	    for(int c = 0; c < newAry[r].length; c++){
		int newVal = 0;
		try{
		    if(oldAry[r + 1][c] != -1){
			newVal += oldAry[r + 1][c];
		    }
		}catch(ArrayIndexOutOfBoundsException e){}

		try{
		    if(oldAry[r - 1][c] != -1){		
			newVal += oldAry[r - 1][c];
		    }
		}catch(ArrayIndexOutOfBoundsException e){}

		try{
		    if(oldAry[r][c + 1] != -1){
			newVal += oldAry[r][c + 1];
		    }
		}catch(ArrayIndexOutOfBoundsException e){}

		try{
		    if(oldAry[r][c - 1] != -1){			
			newVal += oldAry[r][c - 1];
		    }
		}catch(ArrayIndexOutOfBoundsException e){}

		newAry[r][c] = newVal;
	    }
	}

	//recursive call
	//note the switching of newAry with oldAry
	return navigateH(newAry, oldAry, seconds - 1);
    }

    public static void main(String[] args){
	Silver m = new Silver();
	System.out.println(m.silver("silverTest1.txt"));
    }
}
			   
	
		    
	
	

    
