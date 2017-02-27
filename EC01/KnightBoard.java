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
    public void solveImproved(){
	newSolveH();
    }
    
    private boolean newSolveH(int row, int col, int numKnight){
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
	
	//SORTING HERE 
	//PLAN:
	//create third array of valid num potential moves for each of the eight
	//moves (movesRow[i], movesCol[i])
	//Then, run a sort (copied from one of my old sorts) on the third array.
	//BUT, have it be modified, so that it mimics whatever actions it takes
	//to sort the third array with the arrays movesRow, movesCol.
	//This way, they correspond correctly
	
	return false;
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
	//what I've noticed:
	//8x9 takes a while, but 9x8 takes considerably less time
	//9x9 takes less time (it takes about 3 seconds) than both 8x9 and 9x8
	//10x10 takes more than a couple minutes (I gave up on waiting for it)
	KnightBoard b = new KnightBoard(9, 9);
	System.out.println(b);
	b.solve();
	System.out.println(b);
    }
}
