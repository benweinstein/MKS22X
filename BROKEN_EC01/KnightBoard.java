public class KnightBoard{
    private int[][] board;

    //It wasn't working when I used these private static int[]'s
    //for some reason, so I just decided to instantiate each time 
    // private static int[] movesRow;
    // private static int[] movesCol;

    public KnightBoard(int startingRows, int startingCols){
	board = new int[startingRows][startingCols];
    }

  
    //NEW (FOR EXTRA CREDIT)
    public void solveFast(){
	fasterSolveH(0, 0, 1);
    }
    
    private boolean fasterSolveH(int row, int col, int numKnight){
	//base case
	if(numKnight > board.length * board[0].length){
	    return true;
	}

	//check to make sure you're in a valid spot 
	if(!isValidSpot(row, col)){
	    return false;
	}
	
	placeKnightHere(row, col, numKnight);

	//generate where the next knight could be placed
	int[] movesRow = generateRows(row);
	int[] movesCol = generateCols(col);
	
	//PLAN for optimization:
	//create third array of valid num potential moves for each of the eight
	//moves (movesRow[i], movesCol[i])
	//Then, run a sort (copied from one of my old sorts) on the third array.
	//BUT, have it be modified, so that it mimics whatever actions it takes
	//to sort the third array with the arrays movesRow, movesCol.
	//This way, they correspond correctly!!!

	//generate num moves of each array, putting them into a third array
	int[] numMoves = new int[8];
	for(int i = 0; i < numMoves.length; i++){
	    int r = movesRow[i];
	    int c = movesCol[i];
	    int[] rAry = generateRows(r);
	    int[] cAry = generateRows(c);
	    
	    for(int j = 0; j < rAry.length; j++){
		if(isValidSpot(rAry[j], cAry[j])){
		    numMoves[i] = numMoves[i] + 1;
		}
	    }
	}
	
	//using my selection sort:
	for(int i = 0; i < numMoves.length; i++){
	    int currentMinIndex = i;
	    for(int j = i + 1; j < numMoves.length; j++){
		if(numMoves[i] > numMoves[j]){
		    currentMinIndex = j;
		}
	    }
	    int helperN = numMoves[i];
	    int helperR = movesRow[i];
	    int helperC = movesCol[i];
	    
	    numMoves[i] = numMoves[currentMinIndex];
	    movesRow[i] = movesRow[currentMinIndex];
	    movesCol[i] = movesCol[currentMinIndex];
    
	    numMoves[currentMinIndex] = helperN;
	    movesRow[currentMinIndex] = helperR;
	    movesCol[currentMinIndex] = helperC;
	}

	//now, movesRow and movesCol should be in the correct order (lowest
	//number of moves first), so we can run the recursive calls now:
	if(!(fasterSolveH(movesRow[0], movesCol[0], numKnight + 1) ||
	     fasterSolveH(movesRow[1], movesCol[1], numKnight + 1) ||
	     fasterSolveH(movesRow[2], movesCol[2], numKnight + 1) ||
	     fasterSolveH(movesRow[3], movesCol[3], numKnight + 1) ||
	     fasterSolveH(movesRow[4], movesCol[4], numKnight + 1) ||
	     fasterSolveH(movesRow[5], movesCol[5], numKnight + 1) ||
	     fasterSolveH(movesRow[6], movesCol[6], numKnight + 1) ||
	     fasterSolveH(movesRow[7], movesCol[7], numKnight + 1))){
	    deleteKnightHere(row, col);
	    return false;
	}
	return true;	
    }  

    //OLD
    public void solve(){
	solveH(0, 0, 1);
    }

    private boolean solveH(int row, int col, int numKnight){
	//base case
	if(numKnight > board.length * board[0].length){
	    return true;
	}
	
	if(!isValidSpot(row, col)){
	    return false;
	}
	
	placeKnightHere(row, col, numKnight); //place one
	
	//generate where the next knight could be placed 
        int[] movesRow = generateRows(row);
	int[] movesCol = generateCols(col);
	
	//try out these spots
	if(!(solveH(movesRow[0], movesCol[0], numKnight + 1) ||
	     solveH(movesRow[1], movesCol[1], numKnight + 1) ||
	     solveH(movesRow[2], movesCol[2], numKnight + 1) ||
	     solveH(movesRow[3], movesCol[3], numKnight + 1) ||
	     solveH(movesRow[4], movesCol[4], numKnight + 1) ||
	     solveH(movesRow[5], movesCol[5], numKnight + 1) ||
	     solveH(movesRow[6], movesCol[6], numKnight + 1) ||
	     solveH(movesRow[7], movesCol[7], numKnight + 1))){
	    deleteKnightHere(row, col);
	    return false;
	}
	return true;	
    }
    
    //HELPERS for the helper
    private boolean isValidSpot(int row, int col){
	try{
	    return (board[row][col] == 0);
	}catch(ArrayIndexOutOfBoundsException e){
	    return false;
	}
    }

    private int[] generateRows(int row){
	int[] ans = {row - 2, row - 2, row - 1, row - 1,
		     row + 1, row + 1, row + 2, row + 2};
	return ans;
    }
    
    private int[] generateCols(int col){
	int[] ans = {col - 1, col + 1, col - 2, col + 2,
		     col - 2, col + 2, col - 1, col + 1};
	return ans;
    }

    private void placeKnightHere(int row, int col, int numKnight){
	board[row][col] = numKnight;
    }

    private void deleteKnightHere(int row, int col){
	board[row][col] = 0;
    }

    public String toString(){
	String ans = "";
	for(int r = 0; r < board.length; r++){
	    for(int c = 0; c < board[r].length; c++){
		if(board[r][c] == 0){
		    ans += "   ";
		}
		else if(board[r][c] < 10){
		    ans += " " + board[r][c] + " ";
		}
		else{
		    ans += board[r][c] + " ";
		}

		//end of the row
		if(c == board[r].length - 1){
		    ans += "\n";
		}
	    }
	}	
	return ans;
    }

    //TESTS
    public static void main(String[] args){
	KnightBoard b = new KnightBoard(5,5);
       	//System.out.println(b);
       	b.solveFast();
	System.out.println(b);
    }
}
