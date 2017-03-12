import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class USACO{
    //empty default constructor
    public USACO(){

    }
    
    //BRONZE PROBLEM #12: LAKE MAKING
    private int[][] lake;
    private int[][] instructions;
    private int finalElevation;
    private int numInstructions;
    private File bronzeFile;
    private Scanner bronzeScan;

    public int bronze(String filename){
	try{
	    bronzeFile = new File(filename);
	    bronzeScan = new Scanner(bronzeFile);
	}catch(FileNotFoundException e){
	    System.out.println("file not found");
	    System.exit(1);
	}
      	
	int row = Integer.parseInt(bronzeScan.next());
	int col = Integer.parseInt(bronzeScan.next());
	lake = new int[row][col];
	
	finalElevation = Integer.parseInt(bronzeScan.next());
	numInstructions = Integer.parseInt(bronzeScan.next());
	
	int temp = 0;
	while(temp < row){
	    for(int i = 0; i < col; i++){
		lake[temp][i] = Integer.parseInt(bronzeScan.next());
	    }
	    temp++;
	}
	
	instructions = new int[numInstructions][3];
	int temp1 = 0;
	while(temp1 < numInstructions){
	    for(int i = 0; i < 3; i++){
		if(i < 2){
		    instructions[temp1][i] = Integer.parseInt(bronzeScan.next()) - 1;
		}
		else{
		    instructions[temp1][i] = Integer.parseInt(bronzeScan.next());
		}
	    }
	    temp1++;
	}
	
	for(int i = 0;i < instructions.length;i++){
	    toStomp(instructions[i][0],instructions[i][1],instructions[i][2]);
	}
	
	//
	int totalDepth = 0;
	for(int r = 0; r < row; r++){
	    for(int c = 0; c < col; c++){
		lake[r][c] = finalElevation - lake[r][c];
		if(lake[r][c] > 0){
		    totalDepth += lake[r][c];
		}
	    }
	}
	
	return totalDepth * 72 * 72;
    }
    
    private void toStomp(int r, int c, int stomp){
	int largest = 0;
	for(int i = 0; i < 3;i++){
	    for(int k = 0;k < 3;k++){
		try{
		    if(lake[r+i][c+k] > largest){
			largest = lake[r+i][c+k];
		    }
		}catch(ArrayIndexOutOfBoundsException e){
		    //do nothing
		}     
	    }
	}
	//Elevation after largest has been stomped
	int newHighestElevation = largest - stomp;
	if(stomp > largest){
	    newHighestElevation = 0;
	}
	for(int i = 0;i < 3;i++){
	    for(int k = 0;k < 3;k++){
		try{
		    if(lake[r+i][c+k] > newHighestElevation){
			lake[r+i][c+k] = newHighestElevation;
		    }
		}catch(ArrayIndexOutOfBoundsException e){
		    //do nothing
		}	       
	    }
	}	    	    
    }
    
    //
    //
    //
    //
    //
    //
    //SILVER PROBLEM #7: COW TRAVELLING
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
	//printPastures();
	
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
		if(oldAry[r][c] != -1){ //NEEDED THIS!!! So that the -1's don't
		    //ever get changed!!!
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
	}
	//printPastures();
	//recursive call
	//note the switching of newAry with oldAry
	return navigateH(newAry, oldAry, seconds - 1);
    }

    //prints out pastures (for tests)
    private void printPastures(){
	for(int r = 0; r < pasture1.length; r++){
	    for(int c = 0; c < pasture1[r].length; c++){
		if(c == pasture1[r].length - 1){
		    System.out.println(pasture1[r][c] + "," + pasture2[r][c] + "  ");
		}
		else{
		    System.out.print(pasture1[r][c] + "," + pasture2[r][c] + "  ");
		}
	    }
	}
	System.out.println("\n");
    }
    
    
    //OLD METHOD (SLOW)
    /* 
    
    private char[][] pasture;
    private int numPaths; //final answer
    public int silver(String filename){
	try{
	    silverFile = new File(filename);
	    silverScan = new Scanner(silverFile);
	}catch(FileNotFoundException e){
	    System.out.println("file not found");
	    System.exit(1);
	}

	//System.out.println(silverScan.nextInt());
	int row = silverScan.nextInt();
	int col = silverScan.nextInt();

	pasture = new char[row][col];

	//total number of seconds cow should take to get to new place in pasture
	int seconds = silverScan.nextInt();
	//System.out.println(seconds);

	for(int r = 0; r < row; r++){
	    String line = silverScan.next();
	    for(int c = 0; c < col; c++){
		pasture[r][c] = line.charAt(c);
	    }
	}
	
	//what should be left now is the last row, space-separated:
	//R1 C1 R2 C2
	//NOTE: NEED TO MAKE SURE THAT THESE ARE ACTUALLY ON THE BOARD
	int r1 = silverScan.nextInt() - 1;
	int c1 = silverScan.nextInt() - 1;
	int r2 = silverScan.nextInt() - 1;
	int c2 = silverScan.nextInt() - 1;
	
	//System.out.println(r1 + "," + c1 + "," + r2 + "," + c2);

	//call recursive helper
	navigateH(r1, c1, r2, c2, seconds);
	
	return numPaths;
    }

    //recursive helper for silver
    private void navigateH(int r1, int c1, int r2, int c2, int timeLeft){
	//base cases 
	if(timeLeft == 0 && r1 == r2 && c1 == c2){
	    numPaths++;
	    return;
	}
        if(timeLeft == 0){
	    return;
	}
	try{
	    if(pasture[r1][c1] == '*'){
		return;
	    }
	}catch(ArrayIndexOutOfBoundsException e){
	    return;
	}
	
	//recursive calls
	navigateH(r1 + 1, c1, r2, c2, timeLeft - 1);
	navigateH(r1 - 1, c1, r2, c2, timeLeft - 1);
	navigateH(r1, c1 + 1, r2, c2, timeLeft - 1);
	navigateH(r1, c1 - 1, r2, c2, timeLeft - 1);
    }
    */
		
    //TESTS
    public static void main(String[] args){
	USACO m = new USACO();
	try{
	    //System.out.println(m.bronze("bronzetest1.txt"));
	    System.out.println(m.silver(args[0]));
	}catch(ArrayIndexOutOfBoundsException e){
	    System.out.println("Use args to test stuff!!!");
	    System.exit(1);
	}
    }
}
