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
    
    //SILVER PROBLEM #7: COW TRAVELLING
    private File silverFile;
    private Scanner silverScan;

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
	int rows = silverScan.nextInt();
	int cols = silverScan.nextInt();

	pasture = new char[rows][cols];

	//total number of seconds cow should take to get to new place in pasture
	int seconds = silverScan.nextInt();

	for(int r = 0; r < rows; r++){
	    for(int c = 0; c < cols; c++){
		pasture[r][c] = silverScan.next().charAt(0);
	    }
	}
	
	return numPaths;
    }

    //TESTS
    public static void main(String[] args){
	USACO m = new USACO();
	//System.out.println(m.bronze("bronzetest1.txt"));
	System.out.println(m.silver("silverTest1.txt"));
    }
}
